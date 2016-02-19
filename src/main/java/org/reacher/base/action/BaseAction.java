/**
 * 
 */
package org.reacher.base.action;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.result.HTTPStatus;
import org.reacher.base.bean.result.ResultNode;
import org.reacher.base.bean.view.BaseView;
import org.reacher.base.service.IService;
import org.reacher.common.pagination.RPageBounds;

/**
 * @author reacher
 *
 */
public abstract class BaseAction <L extends BaseView, D extends BaseView, S extends IService<L, D>, C extends SearchCriteria> extends BaseActionSupport{

	private static final long serialVersionUID = 1L;
	
	protected D view;
	
	protected List<L> views;
	
	protected List<Integer> ids;
	
	protected String location;
	
	protected RPageBounds page;
	
	protected C criteria;
	
	protected abstract S getService();
	
	protected abstract C getSearchCriteria();
	
	protected abstract boolean enablePagination();
	
	public String list() {
		ResultNode<L> result = null;
		if(this.enablePagination()) {
			if(null == this.page) {
				this.page = new RPageBounds();
			}
			result = this.getService().find(this.getSearchCriteria(), this.page);
		} else {
			this.page = null;
			result = this.getService().find(this.getSearchCriteria());
		}
		if(result.isSuccess()) {
			this.views = result.getDatas();
		}
		return result.toString();
	}
	
	public String listAll() {
		ResultNode<L> result = this.getService().find(this.getSearchCriteria());
		if(result.isSuccess()) {
			this.views = result.getDatas();
		}
		return result.toString();
	}
	
	public String detail() {
		if(null == this.view || this.view.isNew()) {
			return ResultNode.create(HTTPStatus.BadRequest).toString();
		}
		ResultNode<D> result = this.getService().findById(this.view.getId());
		if(result.isSuccess()) {
			this.view = result.getData();
		}
		return result.toString();
	}
	
	public String save() {
		if(null == this.view) {
			return ResultNode.create(HTTPStatus.BadRequest).toString();
		}
		String message = this.view.validate();
		if(null != message) {
			return ResultNode.create(HTTPStatus.BadRequest, message).toString();
		}
		ResultNode<D> result = null;
		if(!this.view.isNew()) {
			result = this.getService().update(this.view);
		} else {
			result = this.getService().save(this.view);
		}
		return result.toString();
	}
	
	public String delete() {
		if(null == this.view || this.view.isNew()) {
			return ResultNode.create(HTTPStatus.BadRequest).toString();
		}
		return this.getService().delete(this.view.getId()).toString();
	}
	
	public D getView() {
		return view;
	}

	public void setView(D view) {
		this.view = view;
	}

	public List<L> getViews() {
		return views;
	}

	public void setViews(List<L> views) {
		this.views = views;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public RPageBounds getPage() {
		return page;
	}

	public void setPage(RPageBounds page) {
		this.page = page;
	}

	public C getCriteria() {
		return criteria;
	}

	public void setCriteria(C criteria) {
		this.criteria = criteria;
	}

}
