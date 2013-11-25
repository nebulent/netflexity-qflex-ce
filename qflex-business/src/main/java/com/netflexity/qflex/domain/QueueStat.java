package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

/**
 * QueueStat generated by hbm2java
 */
public class QueueStat implements java.io.Serializable {

	private long queueStatId;
	private Queue queue;
	private int msgIn;
	private int msgOut;
	private int highDepth;
	private long fetchStartTm;
	private long fetchEndTm;
	private long timeSinceReset;
	private Long modifiedTm;
	private String modifiedBy;

	public QueueStat() {
	}

	public QueueStat(long queueStatId, Queue queue, int msgIn, int msgOut,
			int highDepth, long fetchStartTm, long fetchEndTm,
			long timeSinceReset) {
		this.queueStatId = queueStatId;
		this.queue = queue;
		this.msgIn = msgIn;
		this.msgOut = msgOut;
		this.highDepth = highDepth;
		this.fetchStartTm = fetchStartTm;
		this.fetchEndTm = fetchEndTm;
		this.timeSinceReset = timeSinceReset;
	}

	public QueueStat(long queueStatId, Queue queue, int msgIn, int msgOut,
			int highDepth, long fetchStartTm, long fetchEndTm,
			long timeSinceReset, Long modifiedTm, String modifiedBy) {
		this.queueStatId = queueStatId;
		this.queue = queue;
		this.msgIn = msgIn;
		this.msgOut = msgOut;
		this.highDepth = highDepth;
		this.fetchStartTm = fetchStartTm;
		this.fetchEndTm = fetchEndTm;
		this.timeSinceReset = timeSinceReset;
		this.modifiedTm = modifiedTm;
		this.modifiedBy = modifiedBy;
	}

	public long getQueueStatId() {
		return this.queueStatId;
	}

	public void setQueueStatId(long queueStatId) {
		this.queueStatId = queueStatId;
	}

	public Queue getQueue() {
		return this.queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public int getMsgIn() {
		return this.msgIn;
	}

	public void setMsgIn(int msgIn) {
		this.msgIn = msgIn;
	}

	public int getMsgOut() {
		return this.msgOut;
	}

	public void setMsgOut(int msgOut) {
		this.msgOut = msgOut;
	}

	public int getHighDepth() {
		return this.highDepth;
	}

	public void setHighDepth(int highDepth) {
		this.highDepth = highDepth;
	}

	public long getFetchStartTm() {
		return this.fetchStartTm;
	}

	public void setFetchStartTm(long fetchStartTm) {
		this.fetchStartTm = fetchStartTm;
	}

	public long getFetchEndTm() {
		return this.fetchEndTm;
	}

	public void setFetchEndTm(long fetchEndTm) {
		this.fetchEndTm = fetchEndTm;
	}

	public long getTimeSinceReset() {
		return this.timeSinceReset;
	}

	public void setTimeSinceReset(long timeSinceReset) {
		this.timeSinceReset = timeSinceReset;
	}

	public Long getModifiedTm() {
		return this.modifiedTm;
	}

	public void setModifiedTm(Long modifiedTm) {
		this.modifiedTm = modifiedTm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
