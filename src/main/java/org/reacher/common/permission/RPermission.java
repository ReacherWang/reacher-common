/**
 * 
 */
package org.reacher.common.permission;

import java.util.ArrayList;
import java.util.List;

import org.reacher.common.permission.RPermissionEntity;

/**
 * @author reacher
 *
 */
public class RPermission {
	
	private RPermissionEntity permission;
	
	public RPermission(String permissions) {
		this.permission = new RPermissionEntity(permissions);
	}
	
	public boolean hasPermission(List<Integer> permissionIds) {
		return this.permission.hasPermission(permissionIds);
	}
	
	public boolean hasPermission(int permissionId) {
		return this.permission.hasPermission(permissionId);
	}
	
	public boolean addPermission(List<Integer> permissionIds) {
		return this.permission.addPermission(permissionIds);
	}
	
	public boolean addPermission(int permissionId) {
		return this.permission.addPermission(permissionId);
	}
	
	public boolean removePermission(List<Integer> permissionIds) {
		return this.permission.removePermission(permissionIds);
	}
	
	public boolean removePermission(int permissionId) {
		return this.permission.removePermission(permissionId);
	}
	
	public List<Integer> getPermissionIds() {
		String permissions = this.permission.getPermissions();
		if(null == permissions || permissions.isEmpty()) {
			return null;
		}
		List<Integer> permissionIds = new ArrayList<Integer>();
		for(int i = 0; i < permissions.length(); ++i) {
			if(permissions.charAt(i) == '1') {
				permissionIds.add(i + 1);
			}
		}
		return permissionIds;
	}
	
	public String getPermissions() {
		return this.permission.getPermissions();
	}

}
