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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * StampParameters generated by hbm2java
 */
@Entity
@Table(name = "stamp_parameters", catalog = "stampit")
public class StampParameter implements java.io.Serializable {

	private Long idStampParams;
	private String measureUnit;
	private float value;
	private int stampsNum;
	@JsonBackReference
	private Set<Code> codeses = new HashSet<Code>(0);
	@JsonManagedReference
	private Set<MerchantCategory> merchantCategorieses = new HashSet<MerchantCategory>(0);

	public StampParameter() {
	}

	public StampParameter(String measureUnit, float value, int stampsNum) {
		this.measureUnit = measureUnit;
		this.value = value;
		this.stampsNum = stampsNum;
	}

	public StampParameter(String measureUnit, float value, int stampsNum,
			Set codeses, Set merchantCategorieses) {
		this.measureUnit = measureUnit;
		this.value = value;
		this.stampsNum = stampsNum;
		this.codeses = codeses;
		this.merchantCategorieses = merchantCategorieses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_STAMP_PARAMS", unique = true, nullable = false)
	public Long getIdStampParams() {
		return this.idStampParams;
	}

	public void setIdStampParams(Long idStampParams) {
		this.idStampParams = idStampParams;
	}

	@Column(name = "MEASURE_UNIT", nullable = false, length = 100)
	public String getMeasureUnit() {
		return this.measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	@Column(name = "VALUE", nullable = false, precision = 12, scale = 0)
	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Column(name = "STAMPS_NUM", nullable = false)
	public int getStampsNum() {
		return this.stampsNum;
	}

	public void setStampsNum(int stampsNum) {
		this.stampsNum = stampsNum;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stampParameters")
	public Set<Code> getCodeses() {
		return this.codeses;
	}

	public void setCodeses(Set<Code> codeses) {
		this.codeses = codeses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "categories_params", catalog = "stampit", joinColumns = { @JoinColumn(name = "ID_PARAM", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_CATEGORY", nullable = false, updatable = false) })
	public Set<MerchantCategory> getMerchantCategorieses() {
		return this.merchantCategorieses;
	}

	public void setMerchantCategorieses(Set<MerchantCategory> merchantCategorieses) {
		this.merchantCategorieses = merchantCategorieses;
	}

}