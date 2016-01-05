/**
 * 
 */
package org.reacher.base.bean.entity;

import org.reacher.base.bean.Bean;

/**
 * @author reacher
 *
 */
public abstract class BaseEntity implements Bean {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public boolean isNew() {
		if(0 >= id) {
			return true;
		}
		return false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
