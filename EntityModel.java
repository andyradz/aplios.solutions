package com.codigo.aplios.group.database.models.location;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

/**
 * Model bazowy encji bazy danych Wszystkie nowe encje powinny dziedziczy z tego
 * modelu
 *
 * @author   andrzej.radziszewski
 * @version  1.0.0.0
 * @since    2019
 * @category Entity
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntityModel implements Serializable {

	private static final long serialVersionUID = -6333759041613153418L;

	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "entity_generator")
	@TableGenerator(name = "entity_generator", allocationSize = 50, initialValue = 0)
	protected Long id;

	public Long getId() {

		return this.id;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof EntityModel))
			return false;

		final EntityModel other = (EntityModel) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {

		return "EntityModel [id="
				+ id
				+ "]";
	}
}
