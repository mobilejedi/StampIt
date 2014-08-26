package com.stampit.model;

// Generated 1-ago-2014 11.47.35 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * ActiveCards generated by hbm2java
 */
@Entity
@Table(name = "ACTIVE_CARDS", catalog = "STAMPIT")
public class ActiveCard implements java.io.Serializable {

	private Long idActiveCard;
	private Card card;
	private Customer customer;
	private int stampsNumber;
	private Integer rating;	
	private Set<Log> logses = new HashSet<Log>();	
	private Set<AchievedBonus> achievedBonuseses = new HashSet<AchievedBonus>();

	public ActiveCard() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_ACTIVE_CARD", unique = true, nullable = false)
	public Long getIdActiveCard() {
		return this.idActiveCard;
	}

	public void setIdActiveCard(Long idActiveCard) {
		this.idActiveCard = idActiveCard;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CARD", nullable = false)
	@RestResource(path = "card")
	public Card getCard() {
		return this.card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUSTOMER", nullable = false)	
	@RestResource(path = "customer")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "STAMPS_NUMBER", nullable = false)
	public int getStampsNumber() {
		return this.stampsNumber;
	}

	public void setStampsNumber(int stampsNumber) {
		this.stampsNumber = stampsNumber;
	}

	@Column(name = "RATING")
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activeCard")
	@RestResource(path="logs", rel="logs")
	public Set<Log> getLogses() {
		return this.logses;
	}

	public void setLogses(Set<Log> logses) {
		this.logses = logses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activeCard")
	@RestResource(path="achievedBonuses",rel="achievedBonuses")
	public Set<AchievedBonus> getAchievedBonuseses() {
		return this.achievedBonuseses;
	}

	public void setAchievedBonuseses(Set<AchievedBonus> achievedBonuseses) {
		this.achievedBonuseses = achievedBonuseses;
	}

}
