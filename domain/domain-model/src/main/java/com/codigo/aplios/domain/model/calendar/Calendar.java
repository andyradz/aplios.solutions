package com.codigo.aplios.domain.model.calendar;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;

@Entity
@Table(name = "Calendar")
public class Calendar implements Serializable {
	
	private static final long serialVersionUID = 7149137451110949342L;
	
	@EmbeddedId
	private CalendarPrimaryKey calendarPrimaryKey;
	
	@Column(name = "Name", length = 50, nullable = false, unique = true)
	@ColumnPosition(position = 1)
	private String name;
	
	@ColumnPosition(position = 2)
	@Column(name = "CalendarDate", nullable = false, unique = true)
	@Temporal(TemporalType.DATE)
	private Date calendareDate;
	
	public Calendar() {
		
	}
	
	public Calendar(final CalendarPrimaryKey calendarPrimaryKey) {
		
		this.calendarPrimaryKey = calendarPrimaryKey;
		
		this.calendareDate =
				Date.from((LocalDate.of(calendarPrimaryKey.getYearNumber(), calendarPrimaryKey.getMonthNumber(),
						calendarPrimaryKey.getDayNumber())).atStartOfDay(ZoneId.systemDefault())
							.toInstant());
	}
	
	@OneToOne(optional = false, mappedBy = "calendar", cascade = CascadeType.PERSIST)
	private CalendarDay calendarDay;
	
	public String getName() {
		
		return this.name;
	}
	
	public void setName(final String name) {
		
		this.name = name;
	}
	
	public CalendarDay getCalendarDay() {
		
		return this.calendarDay;
	}
	
	public void setCalendarDay(final CalendarDay calendarDay) {
		
		this.calendarDay = calendarDay;
	}
	
	public Date getCalendarDate() {
		return this.calendareDate;
	}
	
	@Override
	public String toString() {
		
		return "Calendar [calendarPrimaryKey="
				+ this.calendarPrimaryKey
				+ ", name="
				+ this.name
				+ ", calendarDay="
				+ this.calendarDay
				+ "]";
	}
}
