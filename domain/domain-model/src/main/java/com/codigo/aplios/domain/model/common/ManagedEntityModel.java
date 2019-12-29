package com.codigo.aplios.domain.model.common;

import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.catalog.EntityLifeState;

@Table(name = "ManagedEtityModel")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(value = ManagedEntityListener.class)
@MappedSuperclass
public abstract class ManagedEntityModel extends EntityModel {
	
	private static final long serialVersionUID = 9210202540912189346L;

	@Embedded
	@ColumnPosition(position = -4)
	private EntityDateTime entityDateTime;
	
	@Embedded
	@ColumnPosition(position = -5)
	private EntityLifeState entityLifeState;	
	
	public EntityDateTime getEntityDateTime() {
		
		return this.entityDateTime;
	}
	
	public void setEntityDateTime(final EntityDateTime metainfo) {
		
		this.entityDateTime = metainfo;
	}
	
	public EntityLifeState getEntityLifeState() {
		
		return this.entityLifeState;
	}
	
	public void setEntityLifeState(final EntityLifeState entityState) {
		
		this.entityLifeState = entityState;
	}
}
