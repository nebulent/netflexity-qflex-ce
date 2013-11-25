package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * TrigConditionTypes generated by hbm2java
 */
public class TrigConditionTypes implements java.io.Serializable {

	private long trigConditionTypeId;
	private long monitorTypeId;
	private String trigConditionTypeNm;
	private char requiresParam;
	private int displaySeqNo;
	private String description;

	public TrigConditionTypes() {
	}

	public TrigConditionTypes(long trigConditionTypeId, long monitorTypeId,
			String trigConditionTypeNm, char requiresParam, int displaySeqNo,
			String description) {
		this.trigConditionTypeId = trigConditionTypeId;
		this.monitorTypeId = monitorTypeId;
		this.trigConditionTypeNm = trigConditionTypeNm;
		this.requiresParam = requiresParam;
		this.displaySeqNo = displaySeqNo;
		this.description = description;
	}

	public long getTrigConditionTypeId() {
		return this.trigConditionTypeId;
	}

	public void setTrigConditionTypeId(long trigConditionTypeId) {
		this.trigConditionTypeId = trigConditionTypeId;
	}

	public long getMonitorTypeId() {
		return this.monitorTypeId;
	}

	public void setMonitorTypeId(long monitorTypeId) {
		this.monitorTypeId = monitorTypeId;
	}

	public String getTrigConditionTypeNm() {
		return this.trigConditionTypeNm;
	}

	public void setTrigConditionTypeNm(String trigConditionTypeNm) {
		this.trigConditionTypeNm = trigConditionTypeNm;
	}

	public char getRequiresParam() {
		return this.requiresParam;
	}

	public void setRequiresParam(char requiresParam) {
		this.requiresParam = requiresParam;
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