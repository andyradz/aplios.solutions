package com.codigo.aplios.domain.model.contacts;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;
import com.codigo.aplios.domain.model.catalog.EntityColumnPositionCustomizer;
import com.codigo.aplios.domain.model.common.EntityModel;

@Entity
@Table(name = "Phone")
@Customizer(EntityColumnPositionCustomizer.class)
public class Phone extends EntityModel implements IPhone {

	private static final long serialVersionUID = 6282306950384374334L;

	@ColumnPosition(position = 1)
	@Column(name = "CountryCode")
	private String countryCode;

	@ColumnPosition(position = 2)
	@Column(name = "Extension")
	private String extension;

	@ColumnPosition(position = 3)
	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;

	@ColumnPosition(position = 4)
	@Enumerated(EnumType.STRING)
	@Column(name = "PhoneType")
	private PhoneType phoneType;

	@ColumnPosition(position = 5)
	@Column(name = "IsDefault")
	private boolean isDefault;

	@ColumnPosition(position = 6)
	@Column(name = "IsActive")
	private boolean isActive;

	@Override
	public String getCountryCode() {

		return this.countryCode;
	}

	@Override
	public void setCountryCode(final String countryCode) {

		this.countryCode = countryCode;
	}

	@Override
	public String getPhoneNumber() {

		return this.phoneNumber;
	}

	@Override
	public void setPhoneNumber(final String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	@Override
	public PhoneType getPhoneType() {

		return this.phoneType;
	}

	@Override
	public void setPhoneType(final PhoneType phoneType) {

		this.phoneType = phoneType;
	}

	@Override
	public String getExtension() {

		return this.extension;
	}

	@Override
	public void setExtension(final String extension) {

		this.extension = extension;
	}

	@Override
	public boolean isDefault() {

		return this.isDefault;
	}

	@Override
	public void setDefault(final boolean isDefault) {

		this.isDefault = isDefault;
	}

	@Override
	public boolean isActive() {

		return this.isActive;
	}

	@Override
	public void setActive(final boolean isActive) {

		this.isActive = isActive;
	}

	@Override
	public int hashCode() {

		return Objects.hash(this.isActive, this.isDefault, this.countryCode, this.phoneNumber, this.extension);
	}

}
