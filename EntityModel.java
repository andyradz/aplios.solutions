package com.codigo.aplios.group.database.models.location;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(name = "CreatedTimestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@PrePersist
	private void onPersist() {

		// final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		final Validator validator = factory.getValidator();
//
//		final Set<ConstraintViolation<BaseEntity>> constraintViolations =
//				validator.validate(this);

		this.createdDate = Date.from(Instant.now());
		this.createdTimestamp = Timestamp.from(Instant.now());
	}

	public Long getId() {

		return this.id;
	}

	@Override
	public String toString() {

		return "BaseEntity [id="
				+ id
				+ ", createdDate="
				+ createdDate
				+ "]";
	}
}
