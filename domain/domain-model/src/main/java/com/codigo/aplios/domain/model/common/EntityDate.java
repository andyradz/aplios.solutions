package com.codigo.aplios.domain.model.common;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public final class EntityDate {
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public Date getCreatedDateUtc() {
		return date;
	}
	
	void setCreatedDateUtc(Date createdDateUtc) {
		this.date = createdDateUtc;
	}
	
	@Override
	public String toString() {
		return "EntityDate [date="
				+ date
				+ "]";
	}
}
