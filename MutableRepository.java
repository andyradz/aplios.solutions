package com.codigo.aplios.group.database.repository;

import javax.persistence.EntityManager;

import com.codigo.aplios.group.database.models.location.EntityModel;

//TODO: nie ma fizycznego usuwania tylko rekord ma usuwaną flagę na usniętą
public class MutableRepository<T extends EntityModel> extends GenericRepository<T> {

	public MutableRepository(final Class<T> entityType, final String dbStoreName) {

		super(entityType, dbStoreName);
	}

	public MutableRepository(final EntityManager entityManager, final Class<T> entityType, final String dbStoreName)
			throws Exception {

		super(entityManager, entityType, dbStoreName);
	}
}
