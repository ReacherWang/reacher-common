/**
 * 
 */
package org.reacher.common.pagination.database;

import org.reacher.common.pagination.RDialect;

/**
 * @author reacher
 *
 */
public class MySQLDialect implements RDialect {
	
	@Override
	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public String getCountSql(String sql) {
		return "SELECT COUNT(1) FROM (" + sql.replaceAll(";", "") + ") temp";
	}

	@Override
	public String getLimitSql(String sql, long offset, long limit) {
		StringBuffer stringBuffer = new StringBuffer(sql.replaceAll(";", ""));
		stringBuffer.append(" LIMIT ").append(offset).append(", ").append(limit);
		return stringBuffer.toString();
	}

}
