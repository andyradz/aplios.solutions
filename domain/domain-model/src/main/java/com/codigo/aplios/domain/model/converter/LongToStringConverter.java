package com.codigo.aplios.domain.model.converter;

import java.util.Objects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LongToStringConverter implements AttributeConverter<Long, String> {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
	 * Object)
	 */
	@Override
	public String convertToDatabaseColumn(Long attribute) {
		
		return Objects.isNull(attribute) ? null : attribute.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
	 * Object)
	 */
	@Override
	public Long convertToEntityAttribute(String dbData) {
		
		return Objects.isNull(dbData) ? null : Long.valueOf(dbData);
	}
}
