package com.codigo.aplios.group.database.models.company;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.group.database.models.location.EntityModel;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contractor extends EntityModel {

	private static final long serialVersionUID = -6567414523367989925L;

	@Column(name = "EligibleDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date eligibleDate;

	@Column(name = "ShortName", nullable = true)
	private String shortName;

	@Column(name = "FullName", nullable = false)
	private String fullName;

	@Embedded
	private final RecordInfo recordInfo = new RecordInfo();

	@PrePersist
	private void onPersist() {

		this.recordInfo.setCreatedTimestamp(Date.from(Instant.now()));
		this.recordInfo.setAcceptedTimestamp(Date.from(Instant.now()));
	}

	@PreUpdate
	private void onUpdate() {

		this.recordInfo.setModifiedTimestamp(Date.from(Instant.now()));
	}

	@PreRemove
	private void onRemove() {

		this.recordInfo.setCanceledTimestamp(Date.from(Instant.now()));
	}

	/**
	 * @return the eligibleDate
	 */
	public Date getEligibleDate() {

		return eligibleDate;
	}

	/**
	 * @param eligibleDate the eligibleDate to set
	 */
	public void setEligibleDate(final Date eligibleDate) {

		this.eligibleDate = eligibleDate;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {

		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(final String shortName) {

		this.shortName = shortName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {

		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(final String fullName) {

		this.fullName = fullName;
	}

	public RecordInfo getRecordInfo() {

		return recordInfo;
	}
}
