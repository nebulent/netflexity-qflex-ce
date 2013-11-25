package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * AlertStatusTypes generated by hbm2java
 */
public class AlertStatusTypes implements java.io.Serializable {

	private long alertStatusTypeId;
	private int displaySeqNo;
	private String description;

	public AlertStatusTypes() {
	}

	public AlertStatusTypes(long alertStatusTypeId, int displaySeqNo,
			String description) {
		this.alertStatusTypeId = alertStatusTypeId;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getAlertStatusTypeId() {
		return this.alertStatusTypeId;
	}

	public void setAlertStatusTypeId(long alertStatusTypeId) {
		this.alertStatusTypeId = alertStatusTypeId;
	}

	public int getDisplaySeqNo() {
		return this.displaySeqNo;
	}

	public void setDisplaySeqNo(int displaySeqNo) {
		this.displaySeqNo = displaySeqNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
