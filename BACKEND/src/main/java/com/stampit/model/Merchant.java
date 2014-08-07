package com.stampit.model;

// Generated 1-ago-2014 11.47.35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Merchants generated by hbm2java
 */
@Entity
@Table(name = "merchants", catalog = "stampit")
public class Merchant implements java.io.Serializable {

	private Long idMerchant;
	private MerchantCategory merchantCategories;
	private String username;
	private String password;
	private String name;
	private String vat;
	private String phone;
	private String address;
	private String city;
	private String country;
	private String website;
	private String closingDay;
	private Date openingTime;
	private Date closingTime;
	private byte[] gpsCoordinates;
	private String email;
	@JsonManagedReference
	private Set<MerchantFeedback> merchantFeedbackses = new HashSet<MerchantFeedback>(0);
	@JsonBackReference
	private Set<Card> cardses = new HashSet<Card>(0);

	public Merchant() {
	}

	public Merchant(MerchantCategory merchantCategories, String username,
			String password, String name, String vat, String phone,
			String address, String city, String country, String email) {
		this.merchantCategories = merchantCategories;
		this.username = username;
		this.password = password;
		this.name = name;
		this.vat = vat;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.email = email;
	}

	public Merchant(MerchantCategory merchantCategories, String username,
			String password, String name, String vat, String phone,
			String address, String city, String country, String website,
			String closingDay, Date openingTime, Date closingTime,
			byte[] gpsCoordinates, String email, Set merchantFeedbackses,
			Set cardses) {
		this.merchantCategories = merchantCategories;
		this.username = username;
		this.password = password;
		this.name = name;
		this.vat = vat;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.website = website;
		this.closingDay = closingDay;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.gpsCoordinates = gpsCoordinates;
		this.email = email;
		this.merchantFeedbackses = merchantFeedbackses;
		this.cardses = cardses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_MERCHANT", unique = true, nullable = false)
	public Long getIdMerchant() {
		return this.idMerchant;
	}

	public void setIdMerchant(Long idMerchant) {
		this.idMerchant = idMerchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_CATEGORY", nullable = false)
	public MerchantCategory getMerchantCategories() {
		return this.merchantCategories;
	}

	public void setMerchantCategories(MerchantCategory merchantCategories) {
		this.merchantCategories = merchantCategories;
	}

	@Column(name = "USERNAME", nullable = false, length = 16)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 12)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VAT", nullable = false, length = 45)
	public String getVat() {
		return this.vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	@Column(name = "PHONE", nullable = false, length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ADDRESS", nullable = false, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CITY", nullable = false, length = 45)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTRY", nullable = false, length = 45)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "WEBSITE", length = 100)
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "CLOSING_DAY", length = 45)
	public String getClosingDay() {
		return this.closingDay;
	}

	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "OPENING_TIME", length = 8)
	public Date getOpeningTime() {
		return this.openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "CLOSING_TIME", length = 8)
	public Date getClosingTime() {
		return this.closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	@Column(name = "GPS_COORDINATES")
	public byte[] getGpsCoordinates() {
		return this.gpsCoordinates;
	}

	public void setGpsCoordinates(byte[] gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

	@Column(name = "EMAIL", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchants")
	public Set<MerchantFeedback> getMerchantFeedbackses() {
		return this.merchantFeedbackses;
	}

	public void setMerchantFeedbackses(Set<MerchantFeedback> merchantFeedbackses) {
		this.merchantFeedbackses = merchantFeedbackses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchants")
	public Set<Card> getCardses() {
		return this.cardses;
	}

	public void setCardses(Set<Card> cardses) {
		this.cardses = cardses;
	}

}
