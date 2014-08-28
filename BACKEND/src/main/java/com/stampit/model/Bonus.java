package com.stampit.model;

// Generated 1-ago-2014 11.47.35 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bonus generated by hbm2java
 */
@Entity
@Table(name = "BONUSES", catalog = "STAMPIT")
public class Bonus implements java.io.Serializable {

	private long idBonus;
	private Card card;	
	private Prize prizes;
	private int slotPos;	
	private Set<AchievedBonus> achievedBonuseses = new HashSet<AchievedBonus>();

	public Bonus() {
	}

	@Id
	@SequenceGenerator(name="BONUSES_ID_BONUS_seq",
		sequenceName="BONUSES_ID_BONUS_seq",
		allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator="BONUSES_ID_BONUS_seq")
	@Column(name = "ID_BONUS", unique = true, nullable = false)
	public long getIdBonus() {
		return this.idBonus;
	}

	public void setIdBonus(long idBonus) {
		this.idBonus = idBonus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CARD", nullable = false)
	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PREMIO")
	public Prize getPrizes() {
		return this.prizes;
	}

	public void setPrizes(Prize prizes) {
		this.prizes = prizes;
	}

	@Column(name = "SLOT_POS", nullable = false)
	public int getSlotPos() {
		return this.slotPos;
	}

	public void setSlotPos(int slotPos) {
		this.slotPos = slotPos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bonus")
	public Set<AchievedBonus> getAchievedBonuseses() {
		return this.achievedBonuseses;
	}

	public void setAchievedBonuseses(Set<AchievedBonus> achievedBonuseses) {
		this.achievedBonuseses = achievedBonuseses;
	}

}
