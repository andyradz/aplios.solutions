/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.domain.model.common;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;

/**
 *
 * @author andrzej.radziszewski
 */
@Embeddable
@Customizer(EntityColumnPositionCustomizer.class)
public class EntityCreatedInfo {
	
	@Embedded
	@AttributeOverrides(@AttributeOverride(name = "date", column = @Column(name = "CreatedDateUtc", columnDefinition = "date")))
	@ColumnPosition(position = 91)
	private EntityDate createdDateUtc;
	
	@Embedded
	@AttributeOverrides(@AttributeOverride(name = "time", column = @Column(name = "CreatedTimeUtc", columnDefinition = "time(7)")))
	@ColumnPosition(position = 92)
	private EntityTime createdTimeUtc;
	
	@Embedded
	@AttributeOverrides(@AttributeOverride(name = "timestamp", column = @Column(name = "CreatedTimestampUtc", columnDefinition = "datetime2")))
	@ColumnPosition(position = 92)
	private EntityTimestamp createdTimestampUtc;
	
	@Column(name = "CreatedByUser")
	@ColumnPosition(position = 93)
	private String createdByUser;
	
	EntityCreatedInfo() {
		this.createdDateUtc = new EntityDate();
		this.createdTimeUtc = new EntityTime();
		this.createdTimestampUtc = new EntityTimestamp();
	}
	
	public Date getCreatedDateUtc() {
		return this.createdDateUtc.getCreatedDateUtc();
	}
	
	public void setCreatedDateUtc(Date createdDateUtc) {
		this.createdDateUtc.setCreatedDateUtc(createdDateUtc);
	}
	
	public Date getCreatedTimeUtc() {
		return this.createdTimeUtc.getTime();
	}
	
	public void setCreatedTimeUtc(Date createdTimeUtc) {
		this.createdTimeUtc.setTime(createdTimeUtc);
	}
	
	public String getCreatedByUser() {
		return this.createdByUser;
	}
	
	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}
}
