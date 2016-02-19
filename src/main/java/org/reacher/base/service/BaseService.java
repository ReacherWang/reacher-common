/**
 * 
 */
package org.reacher.base.service;

import java.util.ArrayList;
import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.entity.BaseEntity;
import org.reacher.base.bean.result.HTTPStatus;
import org.reacher.base.bean.result.ResultNode;
import org.reacher.base.bean.view.BaseView;
import org.reacher.base.dao.BaseDao;
import org.reacher.common.converter.BeanConverter;
import org.reacher.common.pagination.RPageBounds;
import org.reacher.common.utils.ClassUtil;
import org.reacher.common.utils.CollectionUtil;

/**
 * @author reacher
 *
 */
public abstract class BaseService<L extends BaseView, D extends BaseView> implements IService<L, D> {
	
	@SuppressWarnings("unchecked")
	private Class<L> listClass = (Class<L>) ClassUtil.getParameterizedTypeClass(getClass(), 0);
	
	@SuppressWarnings("unchecked")
	private Class<D> detailClass = (Class<D>) ClassUtil.getParameterizedTypeClass(getClass(), 1);
	
	protected static final BeanConverter beanConverter = BeanConverter.getInstance();
	
	protected abstract BaseDao<BaseEntity> getDao();

	@Override
	public ResultNode<D> save(D view) {
		if(null == view) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		view.setId(this.getDao().save(beanConverter.convertToEntity(view)));
		if(0 >= view.getId()) {
			return ResultNode.create(HTTPStatus.ServerError);
		}
		return ResultNode.create(HTTPStatus.Ok);
	}
	
	@Override
	public ResultNode<D> update(D view) {
		if(null == view || view.isNew()) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		if(!this.getDao().update(beanConverter.convertToEntity(view))) {
			return ResultNode.create(HTTPStatus.ServerError);
		}
		return ResultNode.create(HTTPStatus.Ok);
	}

	@Override
	public ResultNode<D> delete(long id) {
		if(0 >= id) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		if(!this.getDao().delete(id)) {
			return ResultNode.create(HTTPStatus.ServerError);
		}
		return ResultNode.create(HTTPStatus.Ok);
	}

	@Override
	public ResultNode<L> batchDelete(List<Integer> ids) {
		if(null == ids || 0 >= ids.size()) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		if(!this.getDao().batchDelete(ids)) {
			return ResultNode.create(HTTPStatus.ServerError);
		}
		return ResultNode.create(HTTPStatus.Ok);
	}

	@Override
	public ResultNode<D> findById(long id) {
		if(0 >= id) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		BaseEntity entity = this.getDao().findById(id);
		if(null == entity) {
			return ResultNode.create(HTTPStatus.ServerError);
		}
		D data = beanConverter.convertFromEntity(entity, this.detailClass);
		return ResultNode.create(HTTPStatus.Ok, data);
	}

	@Override
	public ResultNode<L> findAll() {
		List<BaseEntity> entities = this.getDao().findAll();
		if(CollectionUtil.isEmpty(entities)) {
			return ResultNode.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return ResultNode.create(HTTPStatus.Ok, datas);
	}

	@Override
	public ResultNode<L> findAll(RPageBounds page) {
		if(null == page || 0 >= page.getSize() || 0 >= page.getNumber()) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		List<BaseEntity> entities = this.getDao().findAll(page);
		if(CollectionUtil.isEmpty(entities)) {
			return ResultNode.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return ResultNode.create(HTTPStatus.Ok, datas);
	}

	@Override
	public ResultNode<L> find(SearchCriteria criteria) {
		List<BaseEntity> entities = this.getDao().find(criteria);
		if(CollectionUtil.isEmpty(entities)) {
			return ResultNode.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return ResultNode.create(HTTPStatus.Ok, datas);
	}

	@Override
	public ResultNode<L> find(SearchCriteria criteria, RPageBounds page) {
		if(null == page || 0 >= page.getSize() || 0 >= page.getNumber()) {
			return ResultNode.create(HTTPStatus.BadRequest);
		}
		List<BaseEntity> entities = this.getDao().find(criteria, page);
		if(CollectionUtil.isEmpty(entities)) {
			return ResultNode.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return ResultNode.create(HTTPStatus.Ok, datas);
	}

}
