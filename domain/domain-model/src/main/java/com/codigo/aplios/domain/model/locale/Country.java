package com.codigo.aplios.domain.model.locale;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.codigo.aplios.domain.model.catalog.ColumnPosition;

// wzorzec temporal plus jpa
// http://www.javaexpress.pl/article/show/Wzorce_projektowe_Temporal_Object?printable=true
// opisanie alternatywy do klonowania
// https://stormit.pl/klonowanie/
// http://www.rafaljankowski.pl/daj-sie-poznac/spring-security-podstawy/
@Entity(name = "Country")
@Table(name = "Country")
// @EntityListeners(HistoryEventListener.class)
public class Country extends Dictionary {

	private static final long serialVersionUID = -4524030059918299014L;

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

	@ColumnPosition(position = 5)
	@Column(name = "SubjectToVat")
	private boolean subjectToVal;

	@ColumnPosition(position = 6)
	@Column(name = "LimitedToStores")
	private boolean limitedToStores;

	@ColumnPosition(position = 7)
	@Column(name = "AllowsBilling")
	private boolean allowsBilling;

	@ColumnPosition(position = 8)
	@Column(name = "AllowsShipping")
	private boolean allowsShipping;

	@ColumnPosition(position = 9)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "Picture")
	private byte[] picture;

	// @Version
	// public long version;

	/*
	 * @Valid
	 *
	 * @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
	 * CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "country")
	 *
	 * @PrimaryKeyJoinColumn(name = "ID") private Set<StateProvince> stateProvinces
	 * = new HashSet<>();
	 */
	// Przenieść do własciwości
	// @Column(name = "MEMBEROFEU", nullable = true)
	// private boolean memberOfEu;
	//
	// @Column(name = "EUROZONE", nullable = true)
	// private boolean euroZone;
	// public void setStateProvince(Collection<StateProvince> stateProvinces) {
	//
	// this.stateProvinces = stateProvinces.stream().collect(Collectors.toSet());
	// }
	//
	// public Set<StateProvince> getStateProvince() {
	//
	// return this.stateProvinces;
	// }
	public String getIsoSequence() {

		return "ISO 3166-2:" + getTwoLetterIsoCode();
	}

	public byte[] getPicture() {

		return this.picture;
	}

	public void setPicture(final byte[] picture) {

		this.picture = picture;
	}

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getTwoLetterIsoCode() {

		return this.twoLetterIsoCode;
	}

	public void setTwoLetterIsoCode(final String twoLetterIsoCode) {

		this.twoLetterIsoCode = twoLetterIsoCode;
	}

	public String getThreLetterIsoCode() {

		return this.threLetterIsoCode;
	}

	public void setThreLetterIsoCode(final String threLetterIsoCode) {

		this.threLetterIsoCode = threLetterIsoCode;
	}

	public String getNumericIsoCode() {

		return this.numericIsoCode;
	}

	public void setNumericIsoCode(final String numericIsoCode) {

		this.numericIsoCode = numericIsoCode;
	}

	public boolean isSubjectToVal() {

		return this.subjectToVal;
	}

	public void setSubjectToVal(final boolean subjectToVal) {

		this.subjectToVal = subjectToVal;
	}

	public boolean isLimitedToStores() {

		return this.limitedToStores;
	}

	public void setLimitedToStores(final boolean limitedToStores) {

		this.limitedToStores = limitedToStores;
	}

	public boolean isAllowsBilling() {

		return this.allowsBilling;
	}

	public void setAllowsBilling(final boolean allowsBilling) {

		this.allowsBilling = allowsBilling;
	}

	public boolean isAllowsShipping() {

		return this.allowsShipping;
	}

	public void setAllowsShipping(final boolean allowsShipping) {

		this.allowsShipping = allowsShipping;
	}

	@Override
	public String toString() {

		return "Country=>\n" + "id" + ":" + getId() + ";\n" + "name" + ":" + getName() + ";\n" + "twoletteriscode" + ":"
				+ getTwoLetterIsoCode() + ";\n";
	}

}
