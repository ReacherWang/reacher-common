/**
 * 
 */
package org.reacher.base.dao;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.entity.BaseEntity;
import org.reacher.base.mapper.BaseMapper;
import org.reacher.common.annotation.RMapper;
import org.reacher.common.pagination.RPageBounds;
import org.reacher.common.utils.MybatisUtil;

/**
 * @author reacher
 * 
 */
public abstract class  BaseDao<E extends BaseEntity>{
	
	@SuppressWarnings("unchecked")
	protected <T extends BaseMapper<E>> T getMapper() {
		RMapper mapper = this.getClass().getAnnotation(RMapper.class);
		if(null == mapper) {
			return null;
		}
		Class<T> mapperClass = (Class<T>)mapper.mapper();
		if(null == mapperClass) {
			return null;
		}
		return (T)MybatisUtil.getMapper(mapperClass);
	}
	
	public long save(E entity) {
		if(null == entity) {
			return 0;
		}
		if(!this.getMapper().save(entity)) {
			return 0;
		}
		return entity.getId();
	}
	
	public boolean update(E entity) {
		if(null == entity || entity.isNew()) {
			return false;
		}
		return this.getMapper().update(entity);
	}
	
	public boolean delete(long id) {
		if(0 >= id) {
			return false;
		}
		return this.getMapper().delete(id);
	}
	
	public boolean batchDelete(List<Integer> ids) {
		if(null == ids || 0 >= ids.size()) {
			return false;
		}
		return this.getMapper().batchDelete(ids);
	}
	
	public E findById(long id) {
		if(0 >= id) {
			return null;
		}
		return this.getMapper().findById(id);
	}
	
	public List<E> findAll() {
		List<E> entities = this.getMapper().findAll();
		if(null == entities || 0 >= entities.size()) {
			return null;
		}
		return entities;
	}
	
	public List<E> findAll(RPageBounds page) {
		List<E> entities = this.getMapper().findAll(page);
		if(null == entities || 0 >= entities.size()) {
			return null;
		}
		return entities;
	}
	
	public List<E> find(SearchCriteria criteria) {
		List<E> entities = this.getMapper().find(criteria);
		if(null == entities || 0 >= entities.size()) {
			return null;
		}
		return entities;
	}
	
	public List<E> find(SearchCriteria criteria, RPageBounds page) {
		List<E> entities = this.getMapper().find(criteria, page);
		if(null == entities || 0 >= entities.size()) {
			return null;
		}
		return entities;
	}
	
}
