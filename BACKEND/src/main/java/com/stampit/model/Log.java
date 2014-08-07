package com.stampit.model;

// Generated 1-ago-2014 11.47.35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Logs generated by hbm2java
 */
@Entity
@Table(name = "logs", catalog = "stampit")
public class Log implements java.io.Serializable {

	private long idLog;	
	private ActiveCard activeCards;
	private Date insDate;
	private String location;
	private Integer stampsNum;

	public Log() {
	}

//	public Log(long idLog, Date insDate) {
//		this.idLog = idLog;
//		this.insDate = insDate;
//	}
//
//	public Log(long idLog, ActiveCard activeCards, Date insDate,
//			String location, Integer stampsNum) {
//		this.idLog = idLog;
//		this.activeCards = activeCards;
//		this.insDate = insDate;
//		this.location = location;
//		this.stampsNum = stampsNum;
//	}

	@Id
	@Column(name = "ID_LOG", unique = true, nullable = false)
	public long getIdLog() {
		return this.idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ACTIVE_CARD")
	public ActiveCard getActiveCards() {
		return this.activeCards;
	}

	public void setActiveCards(ActiveCard activeCards) {
		this.activeCards = activeCards;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INS_DATE", nullable = false, length = 19)
	public Date getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	@Column(name = "LOCATION", length = 100)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "STAMPS_NUM")
	public Integer getStampsNum() {
		return this.stampsNum;
	}

	public void setStampsNum(Integer stampsNum) {
		this.stampsNum = stampsNum;
	}

}