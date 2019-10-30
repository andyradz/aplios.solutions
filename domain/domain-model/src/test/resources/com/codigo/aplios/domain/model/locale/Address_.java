package com.codigo.aplios.domain.model.locale;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-07-11T19:31:32.722+0200")
@StaticMetamodel(Address.class)
public class Address_ {
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile MapAttribute<Address, String, Address> entityMap;
	public static volatile ListAttribute<Address, Address> entityList;
	public static volatile SingularAttribute<Address, AddressType> addressType;
	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> buildNumber;
	public static volatile SingularAttribute<Address, String> flatNumber;
	public static volatile SingularAttribute<Address, String> province;
	public static volatile SingularAttribute<Address, String> county;
	public static volatile SingularAttribute<Address, String> district;
	public static volatile SingularAttribute<Address, String> zipCode;
	public static volatile SingularAttribute<Address, String> postName;
	public static volatile SingularAttribute<Address, String> postBox;
}
