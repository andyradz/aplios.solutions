package com.codigo.aplios.domain.model.locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;

@Table(name = "CountryCode")
@Entity(name = "CountryCode")
public class CountryCode extends Dictionary {

	private static final long serialVersionUID = -6108329114581741039L;
	
	@ColumnPosition(position = 1)
	@Column(name = "Name", nullable = false, unique = true, length = 100)
	private String name;

	@ColumnPosition(position = 2)
	@Column(name = "TwoLetterIsoCode", nullable = false, unique = true, length = 2)
	private String twoLetterIsoCode;

	@ColumnPosition(position = 3)
	@Column(name = "ThreeLetterIsoCode", nullable = true, length = 3)
	private String threLetterIsoCode;

	@ColumnPosition(position = 4)
	@Column(name = "NumericIsoCode", unique = true, nullable = true, length = 3)
	private String numericIsoCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTwoLetterIsoCode() {
		return twoLetterIsoCode;
	}

	public void setTwoLetterIsoCode(String twoLetterIsoCode) {
		this.twoLetterIsoCode = twoLetterIsoCode;
	}

	public String getThreLetterIsoCode() {
		return threLetterIsoCode;
	}

	public void setThreLetterIsoCode(String threLetterIsoCode) {
		this.threLetterIsoCode = threLetterIsoCode;
	}

	public String getNumericIsoCode() {
		return numericIsoCode;
	}

	public void setNumericIsoCode(String numericIsoCode) {
		this.numericIsoCode = numericIsoCode;
	}
}
