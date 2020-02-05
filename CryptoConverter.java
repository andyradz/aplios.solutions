package com.codigo.aplios.group.database.converter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(final String ccNumber) {

		final byte[] encodedBytes = Base64.getDecoder()
			.decode(ccNumber.getBytes(StandardCharsets.UTF_8));

		return new String(encodedBytes);
	}

	@Override
	public String convertToEntityAttribute(final String dbData) {

		final byte[] encodedBytes = Base64.getEncoder()
			.encode(dbData.getBytes(StandardCharsets.UTF_8));
		return new String(encodedBytes);
	}

}