/**
 * 
 */
package org.reacher.common.permission;

import java.util.LinkedList;
import java.util.List;

/**
 * @author reacher
 *
 */
public class RPermissionEntity {
	
	private List<String> permissions;
	
	public RPermissionEntity(String permissions) {
		this.permissions = new LinkedList<String>();
		if(null == permissions) {
			return;
		}
		if(0 != (permissions.length() % RPermissionUtil.BIT)) {
			return;
		}
		int repeats = permissions.length() / RPermissionUtil.BIT;
		for(int i = 0; i < repeats; ++i) {
			int start = i * RPermissionUtil.BIT;
			int end = (i + 1) * RPermissionUtil.BIT;
			this.permissions.add(permissions.substring(start, end));
		}
	}
	
	public boolean hasPermission(List<Integer> permissionIds) {
		if(null == permissionIds || 0 == permissionIds.size()) {
			return false;
		}
		for(Integer permissionId: permissionIds) {
			if(!this.hasPermission(permissionId)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasPermission(int permissionId) {
		if(0 >= permissionId) {
			return false;
		}
		int current = (permissionId - 1) / RPermissionUtil.BIT;
		if(current >= this.permissions.size()) {
			return false;
		}
		
		String permission = this.permissions.get(current);
		permission = RPermissionUtil.reverse(permission);
		
		return RPermissionHelper.getInstance().hasPermission(permissionId, permission);
	}
	
	public boolean addPermission(List<Integer> permissionIds) {
		if(null == permissionIds || 0 == permissionIds.size()) {
			return false;
		}
		for(Integer permissionId: permissionIds) {
			if(!this.addPermission(permissionId)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addPermission(int permissionId) {
		if(0 >= permissionId) {
			return false;
		}
		int current = (permissionId - 1) / RPermissionUtil.BIT;
		for(int i = this.permissions.size(); i <= current; ++i) {
			this.permissions.add(RPermissionUtil.intToBinary(0));
		}
		
		String permission = this.permissions.get(current);
		permission = RPermissionUtil.reverse(permission);
		permission = RPermissionHelper.getInstance().addPermission(permissionId, permission);
		permission = RPermissionUtil.reverse(permission);
		this.permissions.set(current, permission);
		
		return true;
	}
	
	public boolean removePermission(List<Integer> permissionIds) {
		if(null == permissionIds || 0 == permissionIds.size()) {
			return false;
		}
		for(Integer permissionId: permissionIds) {
			if(!this.removePermission(permissionId)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean removePermission(int permissionId) {
		if(0 >= permissionId) {
			return false;
		}
		int current = (permissionId - 1) / RPermissionUtil.BIT;
		if(current >= this.permissions.size()) {
			return false;
		}
		String permission = this.permissions.get(current);
		permission = RPermissionUtil.reverse(permission);
		permission = RPermissionHelper.getInstance().removePermission(permissionId, permission);
		permission = RPermissionUtil.reverse(permission);
		return true;
	}
	
	public String getPermissions() {
		StringBuffer permissions = new StringBuffer();
		for(int i = 0; i < this.permissions.size(); ++i) {
			permissions.append(this.permissions.get(i));
		}
		return permissions.toString();
	}
}
