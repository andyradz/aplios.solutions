package com.codigo.aplios.domain.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;

@Entity
@Table(name = "ZipCodeShortcut")
public class ZipCodeShortcut implements Serializable {
	
	private static final long serialVersionUID = -4630230067427684663L;
	
	/**
	 * Atrybut obiektu określa unikalny identyfikator encji w zbiorze danych
	 */
//	@AttributeOverride(name = "id", column = @Column(name="id"))
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zipCodeShortcut_generator")
	@SequenceGenerator(name = "zipCodeShortcut_generator", sequenceName = "zipCodeShortcut_seq", allocationSize = 50)
	@Column(name = "Id", nullable = false, updatable = false)
	@ColumnPosition(position = 0)
	protected Long id;
	
	@Column(name = "Code", nullable = false, length = 10)
	private String code;
	
	@Column(name = "Name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "Description", nullable = false, length = 255)
	private String description;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		
		return this.code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		
		this.code = code;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		
		return this.name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		
		return this.description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		
		this.description = description;
	}
	
}
