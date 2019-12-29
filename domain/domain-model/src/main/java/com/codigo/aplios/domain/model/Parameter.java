package com.codigo.aplios.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.common.LogicalStates;
import com.codigo.aplios.domain.model.common.ManagedEntityModel;

@Entity
@Table(name = "Parameter", uniqueConstraints = @UniqueConstraint(columnNames = { "ParameterKey", "SubtypeKey" }))
@Customizer(EntityColumnPositionCustomizer.class)
public class Parameter extends ManagedEntityModel {
	
	private static final long serialVersionUID = 6571250117545511181L;
	
	@ColumnPosition(position = 1)
	@Column(name = "ParameterKey", unique = false, length = 16, nullable = false, updatable = false)
	@NotEmpty
	@Size(min = 1, max = 16)
	private String parameterKey;
	
	@ColumnPosition(position = 2)
	@Column(name = "SubtypeKey", unique = false, length = 16)
	private String subtypeKey;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "SubtypeId")
	private Parameter subtype;
	
	@OneToMany(mappedBy = "subtype")
	private Set<Parameter> parameters = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="Factural")//czy merytoryczny
	private LogicalStates factual = LogicalStates.No;
	
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
