package com.netflexity.qflex.business.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.netflexity.qflex.domain.Principals;

public class PrincipalDaoImpl extends AbstractGenericsDao<Principals, Long> {
    
	protected static final Log log = LogFactory.getLog(PrincipalDaoImpl.class);
    
    // property constants
	public static final String PRINCIPAL_ID = "principalId";
	public static final String COMPANY_ID = "company.companyId";
	public static final String PRINCIPAL_NM = "principalNm";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";
	
    /**
     * @param obj
     */
    public Principals insertPrincipal(Principals obj){
        return save(obj);
    }
    
    /**
     * @return
     * 
     */
    @SuppressWarnings ("unchecked")
    public List<Principals> findAllPrincipals(){
        return findAll(PRINCIPAL_NM);
    }
    
    /**
     * @param username
     * @return
     */
    public List<Principals> findPrincipalBy(String username){
        return findByCriteria(Restrictions.eq(USERNAME, username));
    }
    
    /**
     * @param username
     * @param password - MD5 digested
     * @return
     */
    public List<Principals> findPrincipalBy(String username, String password){
        return findByCriteria(new Order[]{Order.asc(PRINCIPAL_NM)}, Restrictions.eq(USERNAME, username), Restrictions.eq(PASSWORD, password));
    }
}