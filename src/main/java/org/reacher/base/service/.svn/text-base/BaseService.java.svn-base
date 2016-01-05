/**
 * 
 */
package org.reacher.base.service;

import java.util.ArrayList;
import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.entity.BaseEntity;
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
	public boolean save(D view) {
		if(null == view) {
			return false;
		}
		view.setId(this.getDao().save(beanConverter.convertToEntity(view)));
		return 0 >= view.getId() ? false : true;
	}

	@Override
	public boolean update(D view) {
		if(null == view || view.isNew()) {
			return false;
		}
		return this.getDao().update(beanConverter.convertToEntity(view));
	}

	@Override
	public boolean delete(long id) {
		if(0 >= id) {
			return false;
		}
		return this.getDao().delete(id);
	}

	@Override
	public boolean batchDelete(List<Integer> ids) {
		if(null == ids || 0 >= ids.size()) {
			return false;
		}
		return this.getDao().batchDelete(ids);
	}

	@Override
	public D findById(long id) {
		if(0 >= id) {
			return null;
		}
		BaseEntity entity = this.getDao().findById(id);
		if(null == entity) {
			return null;
		}
		return beanConverter.convertFromEntity(entity, this.detailClass);
	}

	@Override
	public List<L> findAll() {
		List<BaseEntity> entities = this.getDao().findAll();
		if(CollectionUtil.isEmpty(entities)) {
			return new ArrayList<L>();
		}
		return beanConverter.convertFromEntities(entities, this.listClass);
	}

	@Override
	public List<L> findAll(RPageBounds page) {
		if(null == page || 0 >= page.getSize() || 0 >= page.getNumber()) {
			return null;
		}
		List<BaseEntity> entities = this.getDao().findAll(page);
		if(CollectionUtil.isEmpty(entities)) {
			return new ArrayList<L>();
		}
		return beanConverter.convertFromEntities(entities, this.listClass);
	}

	@Override
	public List<L> find(SearchCriteria criteria) {
		List<BaseEntity> entities = this.getDao().find(criteria);
		if(CollectionUtil.isEmpty(entities)) {
			return new ArrayList<L>();
		}
		return beanConverter.convertFromEntities(entities, this.listClass);
	}

	@Override
	public List<L> find(SearchCriteria criteria, RPageBounds page) {
		if(null == page || 0 >= page.getSize() || 0 >= page.getNumber()) {
			return null;
		}
		List<BaseEntity> entities = this.getDao().find(criteria, page);
		if(CollectionUtil.isEmpty(entities)) {
			return new ArrayList<L>();
		}
		return beanConverter.convertFromEntities(entities, this.listClass);
	}

}
