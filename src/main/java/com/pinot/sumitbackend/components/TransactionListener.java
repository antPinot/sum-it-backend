/**
 * 
 */
package com.pinot.sumitbackend.components;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 
 */
@Component
public class TransactionListener {
	
	public void runAfterCommit(Runnable action) {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

				@Override
				public void afterCommit() {
					action.run();
				}
			});
		} else {
			action.run();
		}
		
	}

}
