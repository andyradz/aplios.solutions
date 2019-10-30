package com.codigo.aplios.domain.model.contacts;

public interface IPhone {

	/**
	 * @return
	 */
	public String getCountryCode();

	public void setCountryCode(final String countryCode);

	public String getPhoneNumber();

	public void setPhoneNumber(final String phoneNumber);

	public PhoneType getPhoneType();

	public void setPhoneType(final PhoneType phoneType);

	public String getExtension();

	public void setExtension(final String extension);

	public boolean isDefault();

	public void setDefault(final boolean isDefault);

	public boolean isActive();

	public void setActive(final boolean isActive);
}
