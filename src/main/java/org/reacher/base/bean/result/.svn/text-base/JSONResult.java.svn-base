/**
 * 
 */
package org.reacher.base.bean.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reacher.common.utils.ArrayUtil;
import org.reacher.common.utils.BeanUtil;
import org.reacher.common.utils.CollectionUtil;

/**
 * @author reacher
 *
 */
public class JSONResult extends BaseResult {

	private static final long serialVersionUID = 1L;
	
	private transient Object value;
	
	private transient List<String> fieldNames;
	
	private Object data;
	
	@Override
	public void prepare() {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("success", this.isSuccess());
		root.put("message", this.getMessage());
		if(null != this.value) {
			if(this.value instanceof Collection) {
				this.data = BeanUtil.getFieldValueMap((Collection<?>) this.value, fieldNames);
			} else {
				this.data = BeanUtil.getFieldValueMap(this.value, fieldNames);
			}
		}
		root.put("data", this.data);
		this.data = root;
	}
	
	@Override
	public String string() {
		return null;
	}
	
	public void setFieldNames(String... names) {
		if (ArrayUtil.isNotEmpty(names)) {
			if (fieldNames == null) {
				fieldNames = new ArrayList<String>();
			}
			CollectionUtil.add(fieldNames, names);
		}
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}
	
	public List<String> getFieldNames() {
		return fieldNames;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
