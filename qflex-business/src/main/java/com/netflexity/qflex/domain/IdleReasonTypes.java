package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * IdleReasonTypes generated by hbm2java
 */
public class IdleReasonTypes implements java.io.Serializable {

	private long idleReasonTypeId;
	private int displaySeqNo;
	private String description;

	public IdleReasonTypes() {
	}

	public IdleReasonTypes(long idleReasonTypeId, int displaySeqNo,
			String description) {
		this.idleReasonTypeId = idleReasonTypeId;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getIdleReasonTypeId() {
		return this.idleReasonTypeId;
	}

	public void setIdleReasonTypeId(long idleReasonTypeId) {
		this.idleReasonTypeId = idleReasonTypeId;
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
