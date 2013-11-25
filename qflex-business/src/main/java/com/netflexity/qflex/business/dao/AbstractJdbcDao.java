/**
 * 
 */
package com.netflexity.qflex.business.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Implements basic data access operations
 * 
 * @author FedorovM
 *
 */
public class AbstractJdbcDao extends JdbcDaoSupport{

	protected Log logger = LogFactory.getLog(this.getClass());
	
}
