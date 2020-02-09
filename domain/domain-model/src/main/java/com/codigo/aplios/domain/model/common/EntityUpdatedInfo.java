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
public class EntityUpdatedInfo {
	
	@Embedded
	@AttributeOverrides(@AttributeOverride(name = "date", column = @Column(name = "UpdatedDateUtc", columnDefinition = "date")))
	@ColumnPosition(position = 94)
	private EntityDate updatedDateUtc;
	
	@Embedded
	@AttributeOverrides(@AttributeOverride(name = "time", column = @Column(name = "UpdatedTimeUtc", columnDefinition = "time(7)")))
	@ColumnPosition(position = 95)
	private EntityTime updatedTimeUtc;
	
	@Column(name = "UpdatedByUser")
	@ColumnPosition(position = 96)
	private String updatedByUser;
	
	public Date getUpdatedDateUtc() {
		return this.updatedDateUtc.getCreatedDateUtc();
	}
	
	public EntityUpdatedInfo() {
		this.updatedDateUtc = new EntityDate();
		this.updatedTimeUtc = new EntityTime();
	}
	
	public void setUpdatedDateUtc(Date updatedDateUtc) {
		this.updatedDateUtc.setCreatedDateUtc(updatedDateUtc);
	}
	
	public Date getUpdatedTimeUtc() {
		return this.updatedTimeUtc.getTime();
	}
	
	public void setUpdatedTimeUtc(Date updatedTimeUtc) {
		this.updatedTimeUtc.setTime(updatedTimeUtc);
	}
	
	public String getUpdatedByUser() {
		return this.updatedByUser;
	}
	
	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}
}
