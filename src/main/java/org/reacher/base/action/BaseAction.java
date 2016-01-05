/**
 * 
 */
package org.reacher.base.action;

import java.util.List;

import org.reacher.base.bean.criteria.SearchCriteria;
import org.reacher.base.bean.result.BaseResult;
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
		if(this.enablePagination()) {
			if(null == this.page) {
				this.page = new RPageBounds();
			}
			this.views = this.getService().find(this.getSearchCriteria(), this.page);
		} else {
			this.page = null;
			this.views = this.getService().find(this.getSearchCriteria());
		}
		return BaseResult.result(200);
	}
	
	public String listAll() {
		this.views = this.getService().find(this.getSearchCriteria());
		return BaseResult.result(200);
	}
	
	public String detail() {
		if(null == this.view || this.view.isNew()) {
			return BaseResult.result(400);
		}
		this.view = this.getService().findById(this.view.getId());
		if(null == this.view) {
			return BaseResult.result(404);
		}
		return BaseResult.result(200);
	}
	
	public String save() {
		if(null == this.view) {
			return BaseResult.result(400);
		}
		String result = this.view.validate();
		if(null != result) {
			return BaseResult.result(400, result);
		}
		if(!this.view.isNew()) {
			if(!this.getService().update(this.view)) {
				return BaseResult.result(500);
			}
		} else {
			if(!this.getService().save(this.view)) {
				return BaseResult.result(500);
			}
		}
		return BaseResult.result(200);
	}
	
	public String delete() {
		if(null == this.view || this.view.isNew()) {
			return BaseResult.result(400);
		}
		if(!this.getService().delete(this.view.getId())) {
			return BaseResult.result(500);
		}
		return BaseResult.result(200);
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
