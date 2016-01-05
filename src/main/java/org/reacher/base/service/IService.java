/**
 * 
 */
package org.reacher.base.service;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.view.BaseView;
import org.reacher.common.pagination.RPageBounds;

/**
 * @author reacher
 *
 */
public interface IService<L extends BaseView, D extends BaseView> {
	
	public boolean save(D view);
	
	public boolean update(D view);
	
	public boolean delete(long id);
	
	public boolean batchDelete(List<Integer> ids);
	
	public D findById(long id);
	
	public List<L> findAll();
	
	public List<L> findAll(RPageBounds page);
	
	public List<L> find(SearchCriteria criteria);
	
	public List<L> find(SearchCriteria criteria, RPageBounds page);
	
}
