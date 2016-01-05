/**
 * 
 */
package org.reacher.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author reacher
 *
 */
public final class MybatisUtil {
	
	private static final Log LOG = LogFactory.getLog(MybatisUtil.class);
	
	private static final String MYBATIS_CONFIG = "mybatis-configuration.xml";
	
	private static ThreadLocal<SqlSession> sqlSession = new ThreadLocal<SqlSession>();
	
	private static class SqlSessionFactoryHolder {
		
		private static final SqlSessionFactory sqlSessionFactory = getInitialInstance();
		
		private static SqlSessionFactory getInitialInstance() {
			try {
				return new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(MYBATIS_CONFIG));
			} catch (Exception e) {
				LOG.error(e);
				return null;
			}
		}
	}
	
	private static SqlSession getSqlSession() {
		return SqlSessionFactoryHolder.sqlSessionFactory.openSession(false);
	}
	
	public static SqlSession getCurrentThreadSqlSession() {
		SqlSession currentThreadSqlSession = sqlSession.get();
		if (currentThreadSqlSession == null) {
			currentThreadSqlSession = getSqlSession();
			sqlSession.set(currentThreadSqlSession);
			currentThreadSqlSession = null;
		}
		return sqlSession.get();
	}
	
	public static void clearCurrentThreadSqlSession() {
		SqlSession currentThreadSqlSession = sqlSession.get();
		if (currentThreadSqlSession != null) {
			currentThreadSqlSession.close();
			currentThreadSqlSession = null;
		}
		sqlSession.remove();
	}
	
	public static void sqlcommit(boolean success) {
		SqlSession currentThreadSqlSession = sqlSession.get();
		if (currentThreadSqlSession != null) {
			if (success) {
				currentThreadSqlSession.commit(true);
			} else {
				currentThreadSqlSession.rollback(true);
			}
		}
	}
	
	public static <T> T getMapper(Class<T> mapperClass) {
		T mapper = getCurrentThreadSqlSession().getMapper(mapperClass);
		return mapper;
	}

}
