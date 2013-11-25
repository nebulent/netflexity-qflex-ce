package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * RecoveryTypes generated by hbm2java
 */
public class RecoveryTypes implements java.io.Serializable {

	private long recoveryTypeId;
	private long trigConditionTypeId;
	private int displaySeqNo;
	private String description;

	public RecoveryTypes() {
	}

	public RecoveryTypes(long recoveryTypeId, long trigConditionTypeId,
			int displaySeqNo, String description) {
		this.recoveryTypeId = recoveryTypeId;
		this.trigConditionTypeId = trigConditionTypeId;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getRecoveryTypeId() {
		return this.recoveryTypeId;
	}

	public void setRecoveryTypeId(long recoveryTypeId) {
		this.recoveryTypeId = recoveryTypeId;
	}

	public long getTrigConditionTypeId() {
		return this.trigConditionTypeId;
	}

	public void setTrigConditionTypeId(long trigConditionTypeId) {
		this.trigConditionTypeId = trigConditionTypeId;
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
