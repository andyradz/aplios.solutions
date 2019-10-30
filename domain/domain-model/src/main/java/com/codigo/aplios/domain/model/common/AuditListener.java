package com.codigo.aplios.domain.model.common;

import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.locale.Dictionary;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TimeZone;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

	@PrePersist
	public void onEntitySave(Object o) {

		if (o instanceof Dictionary) {
			Dictionary audit = (Dictionary) o;

			LocalDate localDate = LocalDate.now(TimeZone.getTimeZone("UTC").toZoneId());
			LocalTime localTime = LocalTime.now(TimeZone.getTimeZone("UTC").toZoneId());

			EntityDateTime entityDtTm = new EntityDateTime();
			final EntityCreatedInfo entityCreatedInfo = new EntityCreatedInfo();
			entityDtTm.setEntityCreatedInfo(entityCreatedInfo);
			entityCreatedInfo.setCreatedDateUtc(Date.valueOf(localDate));
			entityCreatedInfo.setCreatedTimeUtc(Time.valueOf(localTime));

			audit.setEntityDateTime(entityDtTm);
		}
	}

	@PreUpdate	
	public void onEntityUpdate(Object o) {

		if (o instanceof Dictionary) {
			Dictionary audit = (Dictionary) o;

			LocalDate localNow = LocalDate.now(TimeZone.getTimeZone("UTC").toZoneId());
			LocalTime localTime = LocalTime.now(TimeZone.getTimeZone("UTC").toZoneId());

			EntityDateTime entityDtTm = audit.getEntityDateTime();
			final EntityUpdatedInfo entityUpdatedInfo = new EntityUpdatedInfo();
			entityDtTm.setEntityUpdatedInfo(entityUpdatedInfo);
			entityUpdatedInfo.setUpdatedDateUtc(Date.valueOf(localNow));
			entityUpdatedInfo.setUpdatedTimeUtc(Time.valueOf(localTime));

			audit.setEntityDateTime(entityDtTm);
		}
	}

}
