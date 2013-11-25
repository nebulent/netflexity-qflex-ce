package com.netflexity.qflex.business.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflexity.qflex.domain.Company;

public class CompanyDaoImpl extends AbstractGenericsDao<Company, Long> {
    
	protected static final Log log = LogFactory.getLog(CompanyDaoImpl.class);
    
    // property constants
	public static final String COMPANY_ID = "companyId";
	public static final String COMPANY_NM = "companyNm";
	public static final String SERIALNO = "serialNo";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String MODIFIED_TM = "modifiedTm";
	public static final String MODIFIED_BY = "modifiedBy";
	
    /**
     * @param obj
     */
    public Company insertCompany(Company obj){
        return save(obj);
    }

    /**
     * @return
     * 
     */
    @SuppressWarnings ("unchecked")
    public List<Company> findAllCompanies(){
        return findAll(COMPANY_NM);
    }
}