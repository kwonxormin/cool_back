/**
 * 
 */
package com.cool.utills;

import org.apache.ibatis.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author KwonTaekMin
 *
 */
@Service
@Scope("prototype")
public class MyBatisTransactionManagerBatch extends DefaultTransactionDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -99556394330319213L;
	
	@Autowired
	@Qualifier("transactionManagerBatch")
	PlatformTransactionManager transactionManagerBatch;

	TransactionStatus statusBatch;

	public void startBatch() throws TransactionException {
		statusBatch = transactionManagerBatch.getTransaction(this);
	}

	public void commitBatch() throws TransactionException {
		if (!statusBatch.isCompleted()) {
			transactionManagerBatch.commit(statusBatch);
		}
	}

	public void rollbackBatch() throws TransactionException {
		if (!statusBatch.isCompleted()) {
			transactionManagerBatch.rollback(statusBatch);
		}
	}

	public void endBatch() throws TransactionException {
		rollbackBatch();
	}

}
