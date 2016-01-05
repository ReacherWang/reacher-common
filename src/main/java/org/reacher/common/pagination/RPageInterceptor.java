/**
 * 
 */
package org.reacher.common.pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;


/**
 * @author reacher
 *
 *	PaginationInterceptor
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class RPageInterceptor implements Interceptor {
	
	private static final Log LOG = LogFactory.getLog(RPageInterceptor.class);
	
	private Class<?> clazz = null;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if(null == this.clazz) {
			LOG.error("Not found has been initialized database dialect!");
			return invocation.proceed();
		}
		final RDialect dialect = RDialectFactory.getDialect(this.clazz);
		if(null == dialect) {
			LOG.error(this.clazz.getName() + " initialized failed!");
			return invocation.proceed();
		}
		final StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		final MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		final RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		RPageBounds pageBounds = null;
		if (rowBounds instanceof RPageBounds) {
			pageBounds = (RPageBounds) rowBounds;
		}
		if(null == pageBounds || 0 >= pageBounds.getSize() || 0 >= pageBounds.getNumber()) {
			return invocation.proceed();
		}
		final MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		final BoundSql boundSql = statementHandler.getBoundSql();
		final Object parameterObject = boundSql.getParameterObject();
		final Connection connection = (Connection) invocation.getArgs()[0];
		pageBounds.setCount(this.getCount(mappedStatement, connection, parameterObject, dialect));
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitSql(boundSql.getSql(), pageBounds.getSize() * (pageBounds.getNumber() - 1), pageBounds.getSize()));
        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String dialectName = properties.getProperty("dialect");
		if(null == dialectName) {
			LOG.error("Property dialectName isn't set!");
			return;
		}
		try {
			this.clazz = Class.forName(dialectName);
		} catch (ClassNotFoundException e) {
			LOG.error(e);
		}
	}
	
	private int getCount(final MappedStatement mappedStatement, final Connection connection, final Object parameterObject, final RDialect dialect) {
		final BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
		final String countSql = dialect.getCountSql(boundSql.getSql());
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(countSql);
			final ParameterHandler handler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
			handler.setParameters(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
			}
		}
		return count;
	}

}
