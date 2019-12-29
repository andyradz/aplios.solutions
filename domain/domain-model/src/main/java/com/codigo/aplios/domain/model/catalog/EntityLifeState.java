package com.codigo.aplios.domain.model.catalog;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.common.EntityLifeStates;
import com.codigo.aplios.domain.model.common.EntityRowStates;

@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class EntityLifeState {

	@Enumerated(EnumType.STRING)
	@Column(name = "RecordState", nullable = false)
	private EntityRowStates recordState = EntityRowStates.ACTIVE;

	@Enumerated(EnumType.STRING)
	@Column(name = "RecordLifeState", nullable = false)
	private EntityLifeStates recordLifeState = EntityLifeStates.PENDING;

	public EntityRowStates getRowState() {
		return this.recordState;
	}

	public void setRowState(EntityRowStates rowState) {
		this.recordState = rowState;
	}

	public EntityLifeStates getRecordInfo() {
		return this.recordLifeState;
	}

	public void setRecordInfo(EntityLifeStates recordInfo) {
		this.recordLifeState = recordInfo;
	}
}
