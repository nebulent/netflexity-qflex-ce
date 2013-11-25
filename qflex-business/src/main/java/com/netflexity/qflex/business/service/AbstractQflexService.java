/**
 * 
 */
package com.netflexity.qflex.business.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author FedorovM
 *
 */
public class AbstractQflexService {

    protected Log logger = LogFactory.getLog(this.getClass());
    private TransactionTemplate transactionTemplate;

    /**
     * @return the transactionTemplate
     */
    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    /**
     * @param transactionTemplate the transactionTemplate to set
     */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * @param transactionManager
     */
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }
}
