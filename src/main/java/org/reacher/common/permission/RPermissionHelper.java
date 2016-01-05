
package org.reacher.common.permission;


/**
 * @author reacher
 *
 */
public class RPermissionHelper {
	
	private RPermissionHelper() {
		
	}
	
	private static class PermissionHelperHolder {
		private static RPermissionHelper permissionHelper = new RPermissionHelper();
	}
	
	public static RPermissionHelper getInstance() {
		return PermissionHelperHolder.permissionHelper;
	}
	
	public boolean hasPermission(int permissionId, String permissions) {
		if(0 >= permissionId) {
			return false;
		}
		if(null == permissions || permissions.isEmpty()) {
			return false;
		}
		if(RPermissionUtil.BIT != permissions.length()) {
			return false;
		}
		if(!RPermissionUtil.regexBinary(permissions)) {
			return false;
		}
		int offset = (permissionId - 1) % RPermissionUtil.BIT;
		int permission = RPermissionUtil.binaryToInt(permissions) & (1 << offset);
		return 0 != permission ? true : false;
	}
	
	public String addPermission(int permissionId, String permissions) {
		if(0 >= permissionId) {
			return permissions;
		}
		if(null == permissions || permissions.isEmpty()) {
			permissions = RPermissionUtil.intToBinary(0);
		}
		if(RPermissionUtil.BIT != permissions.length()) {
			return permissions;
		}
		if(!RPermissionUtil.regexBinary(permissions)) {
			return permissions;
		}
		int offset = (permissionId - 1) % RPermissionUtil.BIT;
		int permission = RPermissionUtil.binaryToInt(permissions) | (1 << offset);
		return RPermissionUtil.intToBinary(permission);
	}
	
	public String removePermission(int permissionId, String permissions) {
		if(0 >= permissionId) {
			return permissions;
		}
		if(null == permissions || permissions.isEmpty()) {
			return permissions;
		}
		if(RPermissionUtil.BIT != permissions.length()) {
			return permissions;
		}
		if(!RPermissionUtil.regexBinary(permissions)) {
			return permissions;
		}
		int offset = (permissionId - 1) % RPermissionUtil.BIT;
		int permission = ~(~RPermissionUtil.binaryToInt(permissions) | (1 << offset));
		return RPermissionUtil.intToBinary(permission);
	}
	
}
