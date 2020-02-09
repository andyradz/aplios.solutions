package com.codigo.aplios.domain.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Customizer;

import com.codigo.aplios.domain.model.common.EntityModel;

@Entity
@Table(name = "Category")
@Customizer(EntityColumnPositionCustomizer.class)
public class Category extends EntityModel {//extends Dictionary { // słownik

	private static final long serialVersionUID = 5195542005041435577L;
	
//	@AttributeOverride(name = "id", column = @Column(name = "Id"))
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
//	@TableGenerator(name = "category_generator", initialValue = 0, allocationSize = 50)
//	private Long id;

	@ColumnPosition(position = 1)
	@Column(name = "Name", nullable = false, unique = true, length = 20)
	private String name;

	@ColumnPosition(position = 2)
	@Column(name = "Alias", nullable = false, unique = true, length = 20)
	private String alias;
	
//	@OneToMany(mappedBy = "category")
//	@JoinColumn(name = "CategoryId")
//	private Set<CategoryAttribute> attributes = new HashSet<>();

	public String getName() {

		return this.name;
	}

	public void setName(final String name) {

		this.name = name;
	}

}
