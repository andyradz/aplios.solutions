package com.codigo.aplios.domain.model.locale;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.catalog.EntityDateTime;
import com.codigo.aplios.domain.model.catalog.EntityLifeState;
import com.codigo.aplios.domain.model.common.AuditListener;
import com.codigo.aplios.domain.model.common.EntityModel;

@Table(name = "Dictionary")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(value = AuditListener.class)
@Customizer(EntityColumnPositionCustomizer.class)
@MappedSuperclass
public class Dictionary extends EntityModel {
	
	private static final long serialVersionUID = -1118611759382185681L;
	
//	@AttributeOverride(name = "id", column = @Column(name = "id"))
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dictionary_generator")
//	@SequenceGenerator(name = "dictionary_generator", sequenceName = "dictionary_seq", allocationSize = 50)
//	@Column(name = "Id", nullable = false, updatable = false)
//	@ColumnPosition(position = 0)
//	private Long id;
	
	@Column(name = "DisplayOrder")
	@ColumnPosition(position = -1)
	private long displayOrder;
	
	@Column(name = "Published")
	@ColumnPosition(position = -2)
	private boolean isPublished;
	
	@ColumnPosition(position = -3)
	@Column(name = "Description", length = 255, nullable = true)
	private String description;
	
	@Embedded
	@ColumnPosition(position = -4)
	private EntityDateTime entityDateTime;
	
	@Embedded
	@ColumnPosition(position = -5)
	private EntityLifeState entityLifeState;
	
	public long getDisplayOrder() {
		
		return this.displayOrder;
	}
	
	public void setDisplayOrder(final long displayOrder) {
		
		this.displayOrder = displayOrder;
	}
	
	public boolean isPublished() {
		
		return this.isPublished;
	}
	
	public void setPublished(final boolean isPublished) {
		
		this.isPublished = isPublished;
	}
	
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
	
	public String getDescription() {
		
		return this.description;
	}
	
	public void setDescription(final String description) {
		
		this.description = description;
	}
	
}
