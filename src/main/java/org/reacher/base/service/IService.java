/**
 * 
 */
package org.reacher.base.service;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.result.Result;
import org.reacher.base.bean.view.BaseView;
import org.reacher.common.pagination.RPageBounds;

/**
 * @author reacher
 *
 */
public interface IService<L extends BaseView, D extends BaseView> {
	
	public Result<D> save(D view);
	
	public Result<D> update(D view);
	
	public Result<D> delete(long id);
	
	public Result<L> batchDelete(List<Integer> ids);
	
	public Result<D> findById(long id);
	
	public Result<L> findAll();
	
	public Result<L> findAll(RPageBounds page);
	
	public Result<L> find(SearchCriteria criteria);
	
	public Result<L> find(SearchCriteria criteria, RPageBounds page);
	
}
