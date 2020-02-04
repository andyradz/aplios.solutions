package com.codigo.aplios.group.database.models.company;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name = "Participants")
@Table(name = "Participants")
public class Participant extends Contractor {

	private static final long serialVersionUID = -6788161602051789620L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "participants_generator")
	@TableGenerator(name = "participants_generator", allocationSize = 50, initialValue = 0)
	private Long id;

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Participant))
			return false;

		final Participant other = (Participant) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {

		return "Participant [id="
				+ id
				+ "]";
	}
}
