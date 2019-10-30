package com.codigo.aplios.domain.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.locale.Dictionary;

@Entity(name="Slownik")
@Table(name = "Slownik")
public class Slownik extends Dictionary {
	
	private static final long serialVersionUID = -8317010832051797198L;
	
	@ColumnPosition(position = 1)
	@Column(name = "Name", nullable = true, unique = true, length = 100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
