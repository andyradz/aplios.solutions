package com.codigo.aplios.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.locale.Dictionary;

@Entity
@Table(name = "Parameter")
@Customizer(EntityColumnPositionCustomizer.class)
public class Parameter extends Dictionary {
	
	private static final long serialVersionUID = 6571250117545511181L;

	@ColumnPosition(position = 1)
	@Column(name = "ParameterKey", unique = false, length = 16)
	private String parameterKey;
	
	@ColumnPosition(position = 2)
	@Column(name = "SubtypeKey", unique = false, length = 16)
	private String subtypeKey;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name =  "SubtypeId")
	private Parameter subtype;
			
	@OneToMany(mappedBy = "subtype")
	private Set<Parameter> parameters = new HashSet<>();


	public String getParameterKey() {
		return parameterKey;
	}

	public void setParameterKey(String parameterKey) {
		this.parameterKey = parameterKey;
	}

	public String getSubtypeKey() {
		return subtypeKey;
	}

	public void setSubtypeKey(String subtypeKey) {
		this.subtypeKey = subtypeKey;
	}

	public Parameter getSubtype() {
		return subtype;
	}

	public void setSubtype(Parameter subtype) {
		this.subtype = subtype;
	}
}
