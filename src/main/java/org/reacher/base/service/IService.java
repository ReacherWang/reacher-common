/**
 * 
 */
package org.reacher.base.service;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.result.ResultNode;
import org.reacher.base.bean.view.BaseView;
import org.reacher.common.pagination.RPageBounds;

/**
 * @author reacher
 *
 */
public interface IService<L extends BaseView, D extends BaseView> {
	
	public ResultNode<D> save(D view);
	
	public ResultNode<D> update(D view);
	
	public ResultNode<D> delete(long id);
	
	public ResultNode<L> batchDelete(List<Integer> ids);
	
	public ResultNode<D> findById(long id);
	
	public ResultNode<L> findAll();
	
	public ResultNode<L> findAll(RPageBounds page);
	
	public ResultNode<L> find(SearchCriteria criteria);
	
	public ResultNode<L> find(SearchCriteria criteria, RPageBounds page);
	
}
