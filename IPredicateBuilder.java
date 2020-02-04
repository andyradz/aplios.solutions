package com.codigo.aplios.group.database.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface IPredicateBuilder<T> {

	Predicate build(CriteriaBuilder criteriaBuilder, Root<T> root);

}
