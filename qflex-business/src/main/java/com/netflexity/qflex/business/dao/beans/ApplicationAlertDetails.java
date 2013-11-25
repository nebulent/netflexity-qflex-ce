/**
 * 
 */
package com.netflexity.qflex.business.dao.beans;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class ApplicationAlertDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long applicationId;
	private String applicationNm;
	private long criticalityTypeId;
	private long total;
	
	/**
	 * @param applicationId
	 * @param applicationNm
	 * @param criticalityTypeId
	 * @param total
	 */
	public ApplicationAlertDetails(long applicationId, String applicationNm, long criticalityTypeId, long total) {
		this.applicationId = applicationId;
		this.applicationNm = applicationNm;
		this.criticalityTypeId = criticalityTypeId;
		this.total = total;
	}

	/**
	 * @return the applicationId
	 */
	public long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the applicationNm
	 */
	public String getApplicationNm() {
		return applicationNm;
	}

	/**
	 * @param applicationNm the applicationNm to set
	 */
	public void setApplicationNm(String applicationNm) {
		this.applicationNm = applicationNm;
	}

	/**
	 * @return the criticalityTypeId
	 */
	public long getCriticalityTypeId() {
		return criticalityTypeId;
	}

	/**
	 * @param criticalityTypeId the criticalityTypeId to set
	 */
	public void setCriticalityTypeId(long criticalityTypeId) {
		this.criticalityTypeId = criticalityTypeId;
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
}
