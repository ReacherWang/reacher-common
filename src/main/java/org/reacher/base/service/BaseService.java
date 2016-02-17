/**
 * 
 */
package org.reacher.base.service;

import java.util.ArrayList;
import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.entity.BaseEntity;
import org.reacher.base.bean.result.HTTPStatus;
import org.reacher.base.bean.result.Result;
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
	public Result<D> save(D view) {
		if(null == view) {
			return Result.create(HTTPStatus.BadRequest);
		}
		view.setId(this.getDao().save(beanConverter.convertToEntity(view)));
		if(0 >= view.getId()) {
			return Result.create(HTTPStatus.ServerError);
		}
		return Result.create(HTTPStatus.Ok);
	}
	
	@Override
	public Result<D> update(D view) {
		if(null == view || view.isNew()) {
			return Result.create(HTTPStatus.BadRequest);
		}
		if(!this.getDao().update(beanConverter.convertToEntity(view))) {
			return Result.create(HTTPStatus.ServerError);
		}
		return Result.create(HTTPStatus.Ok);
	}

	@Override
	public Result<D> delete(long id) {
		if(0 >= id) {
			return Result.create(HTTPStatus.BadRequest);
		}
		if(!this.getDao().delete(id)) {
			return Result.create(HTTPStatus.ServerError);
		}
		return Result.create(HTTPStatus.Ok);
	}

	@Override
	public Result<L> batchDelete(List<Integer> ids) {
		if(null == ids || 0 >= ids.size()) {
			return Result.create(HTTPStatus.BadRequest);
		}
		if(!this.getDao().batchDelete(ids)) {
			return Result.create(HTTPStatus.ServerError);
		}
		return Result.create(HTTPStatus.Ok);
	}

	@Override
	public Result<D> findById(long id) {
		if(0 >= id) {
			return Result.create(HTTPStatus.BadRequest);
		}
		BaseEntity entity = this.getDao().findById(id);
		if(null == entity) {
			return Result.create(HTTPStatus.ServerError);
		}
		D data = beanConverter.convertFromEntity(entity, this.detailClass);
		return Result.create(HTTPStatus.Ok, data);
	}

	@Override
	public Result<L> findAll() {
		List<BaseEntity> entities = this.getDao().findAll();
		if(CollectionUtil.isEmpty(entities)) {
			return Result.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return Result.create(HTTPStatus.Ok, datas);
	}

	@Override
	public Result<L> findAll(RPageBounds page) {
		if(null == page || 0 >= page.getSize() || 0 >= page.getNumber()) {
			return Result.create(HTTPStatus.BadRequest);
		}
		List<BaseEntity> entities = this.getDao().findAll(page);
		if(CollectionUtil.isEmpty(entities)) {
			return Result.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return Result.create(HTTPStatus.Ok, datas);
	}

	@Override
	public Result<L> find(SearchCriteria criteria) {
		List<BaseEntity> entities = this.getDao().find(criteria);
		if(CollectionUtil.isEmpty(entities)) {
			return Result.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return Result.create(HTTPStatus.Ok, datas);
	}

	@Override
	public Result<L> find(SearchCriteria criteria, RPageBounds page) {
		if(null == page || 0 >= page.getSize() || 0 >= page.getNumber()) {
			return Result.create(HTTPStatus.BadRequest);
		}
		List<BaseEntity> entities = this.getDao().find(criteria, page);
		if(CollectionUtil.isEmpty(entities)) {
			return Result.create(HTTPStatus.Ok, new ArrayList<L>());
		}
		List<L> datas = beanConverter.convertFromEntities(entities, this.listClass);
		return Result.create(HTTPStatus.Ok, datas);
	}

}
