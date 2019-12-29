package com.codigo.aplios.domain.model.common;

public enum EntityLifeStates {
	
	/**
	 * Stan obowiązywania zaakceptowany, zatwierdzony, obowiązujący
	 */
	APPROVED,
	
	/**
	 * Stan obowiązywani oczekujacy na zatwierdzenie
	 */
	PENDING,
	
	/**
	 * Stan obowiązywania anulowany, odwołany, nieaktywny
	 */
	CANCELLED	
}
