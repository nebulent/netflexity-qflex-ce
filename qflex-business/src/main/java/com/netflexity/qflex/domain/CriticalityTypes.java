package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * CriticalityTypes generated by hbm2java
 */
public class CriticalityTypes implements java.io.Serializable {

	private long criticalityTypeId;
	private int displaySeqNo;
	private String description;

	public CriticalityTypes() {
	}

	public CriticalityTypes(long criticalityTypeId, int displaySeqNo,
			String description) {
		this.criticalityTypeId = criticalityTypeId;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getCriticalityTypeId() {
		return this.criticalityTypeId;
	}

	public void setCriticalityTypeId(long criticalityTypeId) {
		this.criticalityTypeId = criticalityTypeId;
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
