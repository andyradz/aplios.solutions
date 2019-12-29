package com.codigo.aplios.domain.model.common;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public final class EntityTime {

	@Temporal(TemporalType.TIME)
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "EntityTime [time="
				+ time
				+ "]";
	}	
}
