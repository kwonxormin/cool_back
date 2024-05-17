/**
 * 
 */
package com.cool.utills;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author KwonTaekMin
 *
 */
@Service("myBatisSupport")
public class MyBatisSupport {
	@Autowired(required = false)
	@Qualifier("sqlSession")
	protected SqlSessionTemplate sqlSession;
	
	@Autowired(required = false)
	@Qualifier("sqlSessionBatch")
	protected SqlSessionTemplate sqlSessionBatch;

	@Autowired
	ApplicationContext applicationContext;

	public MyBatisTransactionManager getTransactionManager() {
		return applicationContext.getBean(MyBatisTransactionManager.class);
	}
	
	public MyBatisTransactionManagerBatch getTransactionManagerBatch() {
		return applicationContext.getBean(MyBatisTransactionManagerBatch.class);
	}
}
