package com.codigo.aplios.domain.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public final class EntityTimestamp {
	
	@Column(name = "Timestamp", nullable = false, insertable = false, updatable = false, columnDefinition = "datetime2")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	public Date getTimestamp() {
		return timestamp;
	}
			
	@Override
	public String toString() {
		return "EntityTimestamp [timestamp="
				+ timestamp
				+ "]";
	}
}
