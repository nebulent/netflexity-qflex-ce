package com.netflexity.qflex.domain;

// Generated Jun 6, 2008 1:43:53 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Queue generated by hbm2java
 */
public class Queue implements java.io.Serializable {

	private long queueId;
	private Qmanager qmanager;
	private String queueNm;
	private int queueTp;
	private int status;
	private Long modifiedTm;
	private String modifiedBy;
	private Set<ApplicationDestination> applicationDestinations = new HashSet<ApplicationDestination>(
			0);
	private Set<QueueStat> queueStats = new HashSet<QueueStat>(0);
	private Set<Monitor> monitors = new HashSet<Monitor>(0);

	public Queue() {
	}

	public Queue(long queueId, Qmanager qmanager, String queueNm, int queueTp,
			int status) {
		this.queueId = queueId;
		this.qmanager = qmanager;
		this.queueNm = queueNm;
		this.queueTp = queueTp;
		this.status = status;
	}

	public Queue(long queueId, Qmanager qmanager, String queueNm, int queueTp,
			int status, Long modifiedTm, String modifiedBy,
			Set<ApplicationDestination> applicationDestinations,
			Set<QueueStat> queueStats, Set<Monitor> monitors) {
		this.queueId = queueId;
		this.qmanager = qmanager;
		this.queueNm = queueNm;
		this.queueTp = queueTp;
		this.status = status;
		this.modifiedTm = modifiedTm;
		this.modifiedBy = modifiedBy;
		this.applicationDestinations = applicationDestinations;
		this.queueStats = queueStats;
		this.monitors = monitors;
	}

	public long getQueueId() {
		return this.queueId;
	}

	public void setQueueId(long queueId) {
		this.queueId = queueId;
	}

	public Qmanager getQmanager() {
		return this.qmanager;
	}

	public void setQmanager(Qmanager qmanager) {
		this.qmanager = qmanager;
	}

	public String getQueueNm() {
		return this.queueNm;
	}

	public void setQueueNm(String queueNm) {
		this.queueNm = queueNm;
	}

	public int getQueueTp() {
		return this.queueTp;
	}

	public void setQueueTp(int queueTp) {
		this.queueTp = queueTp;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Set<ApplicationDestination> getApplicationDestinations() {
		return this.applicationDestinations;
	}

	public void setApplicationDestinations(
			Set<ApplicationDestination> applicationDestinations) {
		this.applicationDestinations = applicationDestinations;
	}

	public Set<QueueStat> getQueueStats() {
		return this.queueStats;
	}

	public void setQueueStats(Set<QueueStat> queueStats) {
		this.queueStats = queueStats;
	}

	public Set<Monitor> getMonitors() {
		return this.monitors;
	}

	public void setMonitors(Set<Monitor> monitors) {
		this.monitors = monitors;
	}

}
