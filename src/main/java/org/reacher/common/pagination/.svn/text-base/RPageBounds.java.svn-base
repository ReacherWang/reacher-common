package org.reacher.common.pagination;

import org.apache.ibatis.session.RowBounds;

/**
 * @author reacher
 *
 */
public class RPageBounds extends RowBounds {
	
	private int size;//页面大小

	private int number;//当前页数

	private int count;//数据总条数
	
	private int total;//总页数

	public RPageBounds() {
		this.number = 1;
		this.size = 10;
	}

	public RPageBounds(int pageSize) {
		this.number = 1;
		this.size = pageSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		this.total = this.count / this.size + (this.count % this.size > 0 ? 1 : 0); 
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
