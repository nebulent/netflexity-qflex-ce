/**
 * 
 */
package com.netflexity.qflex.business.dao.beans;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class ApplicationAlertSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private String applicationNm;
	private Long failures, problems, warninigs, infos;
	
	/**
	 * @param applicationId
	 * @param applicationNm
	 */
	public ApplicationAlertSummary(Long applicationId, String applicationNm) {
		this.applicationId = applicationId;
		this.applicationNm = applicationNm;
	}

	/**
	 * @param applicationId
	 * @param applicationNm
	 * @param failures
	 * @param problems
	 * @param warninigs
	 * @param infos
	 */
	public ApplicationAlertSummary(Long applicationId, String applicationNm,
			Long failures, Long problems, Long warninigs, Long infos) {
		this(applicationId, applicationNm);
		this.failures = failures;
		this.problems = problems;
		this.warninigs = warninigs;
		this.infos = infos;
	}
	
	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
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
	 * @return the failures
	 */
	public Long getFailures() {
		return failures;
	}
	/**
	 * @param failures the failures to set
	 */
	public void setFailures(Long failures) {
		this.failures = failures;
	}
	/**
	 * @return the problems
	 */
	public Long getProblems() {
		return problems;
	}
	/**
	 * @param problems the problems to set
	 */
	public void setProblems(Long problems) {
		this.problems = problems;
	}
	/**
	 * @return the warninigs
	 */
	public Long getWarninigs() {
		return warninigs;
	}
	/**
	 * @param warninigs the warninigs to set
	 */
	public void setWarninigs(Long warninigs) {
		this.warninigs = warninigs;
	}
	/**
	 * @return the infos
	 */
	public Long getInfos() {
		return infos;
	}
	/**
	 * @param infos the infos to set
	 */
	public void setInfos(Long infos) {
		this.infos = infos;
	}

        public void addStatistics(long[] row){
            if(row != null && row.length == 4){
		this.failures += row[0];
		this.problems += row[1];
		this.warninigs += row[2];
		this.infos += row[3];
            }
        }
}
