/**
 * 
 */
package org.reacher.base.bean.view;

import org.reacher.common.permission.RPermission;


/**
 * @author reacher
 *
 */
public abstract class RUserView extends BaseView {

	private static final long serialVersionUID = 1L;
	
	protected String permissions;
	
	protected RPermission permission;

	public String getPermissions() {
		return this.getPermission().getPermissions();
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public RPermission getPermission() {
		if(null == this.permission) {
			this.permission = new RPermission(this.permissions);
		}
		return permission;
	}

	public void setPermission(RPermission permission) {
		this.permission = permission;
	}
	
}
