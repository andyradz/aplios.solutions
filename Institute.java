package com.codigo.aplios.group.database.models.company;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Institutes")
@Table(name = "Institutes")
public class Institute extends Contractor {

	private static final long serialVersionUID = -1922004372527703013L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "institutes_generator")
	@TableGenerator(name = "institutes_generator", allocationSize = 50, initialValue = 0)
	private Long id;

	@Column(name = "MemberId", nullable = true)
	private Long memberId;

	@Column(name = "Code", length = 4, nullable = false)
	private String code;

	@Enumerated(EnumType.STRING)
	@Column(name = "ActivityType", nullable = false)
	private InstituteActivityType activityType;

	@Column(name = "StartCalculatePeriod", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date startCalculatePeriod;

	@Column(name = "EndCalculatePeriod", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date endCalculatePeriod;

	@Column(name = "EStatmentExchange", nullable = true)
	private Boolean eStatmentExchange;

	/**
	 * @return the code
	 */
	public String getCode() {

		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {

		this.code = code;
	}

	/**
	 * @return the type
	 */
	public InstituteActivityType getActivityType() {

		return activityType;
	}

	/**
	 * @param type the type to set
	 */
	public void setActivityType(final InstituteActivityType activityType) {

		this.activityType = activityType;
	}

	/**
	 * @return the startCalculatePeriod
	 */
	public Date getStartCalculatePeriod() {

		return startCalculatePeriod;
	}

	/**
	 * @param startCalculatePeriod the startCalculatePeriod to set
	 */
	public void setStartCalculatePeriod(final Date startCalculatePeriod) {

		this.startCalculatePeriod = startCalculatePeriod;
	}

	/**
	 * @return the eStatmentExchange
	 */
	public Boolean geteStatmentExchange() {

		return eStatmentExchange;
	}

	/**
	 * @param eStatmentExchange the eStatmentExchange to set
	 */
	public void seteStatmentExchange(final Boolean eStatmentExchange) {

		this.eStatmentExchange = eStatmentExchange;
	}

	public Date getEndCalculatePeriod() {

		return endCalculatePeriod;
	}

	@Override
	public int hashCode() {

		return Objects
			.hash(activityType, code, eStatmentExchange, endCalculatePeriod, id, memberId, startCalculatePeriod);
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Institute))
			return false;

		final Institute other = (Institute) obj;

		return (activityType == other.activityType) && Objects.equals(code, other.code)
				&& Objects.equals(eStatmentExchange, other.eStatmentExchange)
				&& Objects.equals(endCalculatePeriod, other.endCalculatePeriod)
				&& Objects.equals(id, other.id)
				&& Objects.equals(memberId, other.memberId)
				&& Objects.equals(startCalculatePeriod, other.startCalculatePeriod);
	}

	@Override
	public String toString() {

		return "Institute [id="
				+ id
				+ ", memberId="
				+ memberId
				+ ", code="
				+ code
				+ ", activityType="
				+ activityType
				+ ", startCalculatePeriod="
				+ startCalculatePeriod
				+ ", endCalculatePeriod="
				+ endCalculatePeriod
				+ ", eStatmentExchange="
				+ eStatmentExchange
				+ "]";
	}

	public void setEndCalculatePeriod(final Date endCalculatePeriod) {

		this.endCalculatePeriod = endCalculatePeriod;
	}
}