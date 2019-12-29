package com.codigo.aplios.domain.model.common;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.catalog.EntityLifeState;

public class ManagedEntityListener {
	
	@PrePersist
	public void onEntitySave(Object o) {
		
		if (o instanceof ManagedEntityModel) {
			ManagedEntityModel audit = (ManagedEntityModel) o;
			
			final LocalDate localDate = LocalDate.now(TimeZone.getTimeZone("UTC")
				.toZoneId());
			final LocalTime localTime = LocalTime.now(TimeZone.getTimeZone("UTC")
				.toZoneId());
			
			EntityDateTime entityDtTm = new EntityDateTime();
			final EntityCreatedInfo entityCreatedInfo = new EntityCreatedInfo();
			entityDtTm.setEntityCreatedInfo(entityCreatedInfo);
			entityCreatedInfo.setCreatedDateUtc(Date.valueOf(localDate));
			entityCreatedInfo.setCreatedTimeUtc(Time.valueOf(localTime));
						
			var hj = new EntityLifeState();
			hj.setRowState(EntityRowStates.ACTIVE);
			hj.setRecordInfo(EntityLifeStates.PENDING);
			audit.setEntityDateTime(entityDtTm);
			audit.setEntityLifeState(hj);
		}
	}
	
	@PreUpdate
	public void onEntityUpdate(Object o) {
		
		if (o instanceof ManagedEntityModel) {
			ManagedEntityModel audit = (ManagedEntityModel) o;
			
			LocalDate localNow = LocalDate.now(TimeZone.getTimeZone("UTC")
				.toZoneId());
			LocalTime localTime = LocalTime.now(TimeZone.getTimeZone("UTC")
				.toZoneId());
			
			EntityDateTime entityDtTm = audit.getEntityDateTime();
			final EntityUpdatedInfo entityUpdatedInfo = new EntityUpdatedInfo();
			entityDtTm.setEntityUpdatedInfo(entityUpdatedInfo);
			entityUpdatedInfo.setUpdatedDateUtc(Date.valueOf(localNow));
			entityUpdatedInfo.setUpdatedTimeUtc(Time.valueOf(localTime));
			
			audit.setEntityDateTime(entityDtTm);
		}
	}
}
