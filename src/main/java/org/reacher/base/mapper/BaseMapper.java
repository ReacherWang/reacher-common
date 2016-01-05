/**
 * 
 */
package org.reacher.base.mapper;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.entity.BaseEntity;
import org.reacher.common.pagination.RPageBounds;

/**
 * @author reacher
 *
 */
public interface BaseMapper <E extends BaseEntity>{

	public boolean save(E entity);
	
	public boolean update(E entity);
	
	public boolean delete(long id);
	
	public boolean batchDelete(List<Integer> ids);
	
	public E findById(long id);
	
	public List<E> findAll();
	
	public List<E> findAll(RPageBounds page);
	
	public List<E> find(SearchCriteria criteria);
	
	public List<E> find(SearchCriteria criteria, RPageBounds page);
}
