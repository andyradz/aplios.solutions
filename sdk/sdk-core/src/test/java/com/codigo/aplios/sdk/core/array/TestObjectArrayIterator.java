package com.codigo.aplios.sdk.core.array;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testowanie implementacji iteratora dla kolekcji typu TestObject")
public class TestObjectArrayIterator {

	private static Logger log = Logger.getLogger(TestObjectArrayIterator.class);

	static final TestObject[] array = {
			new TestObject("Andrzej", (byte) 40, 101d, 147d),
			new TestObject("Marek", (byte) 40, 102d, 157d),
			new TestObject("Tomasz", (byte) 50, 103d, 147d),
			new TestObject("Witold", (byte) 41, 104d, 137d),
			new TestObject("Jan", (byte) 42, 105d, 127d),
			new TestObject("Grzegorz", (byte) 43, 106d, 167d),
			new TestObject("Stanisław", (byte) 44, 107d, 157d),
			new TestObject("Józef", (byte) 45, 108d, 127d),
			new TestObject("Kamil", (byte) 46, 109d, 134d),
			new TestObject("Paweł", (byte) 47, 110d, 133d),
			new TestObject("Piotr", (byte) 48, 111d, 112d),
			new TestObject("Zdzisław", (byte) 30, 30d, 243d)
	};

	private TestObjectArrayIterator() {

	}

	@DisplayName("Test sprawdzania poprawności ilości odczytanych elementów kolekcji")
	@Test
	public void testShouldCount12_Of() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<TestObject> iterator = ArrayIteratorFactory.of(array);
		log.info(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		assertThat(iterator.getCount(), is(itemsCount));
		assertThat(array.length, is(itemsCount));
	}

	@DisplayName("Test sprawdzenia poprawności odczytanego pierwszego elementu kolekcji")
	@Test
	public void testShouldCount1_OfRangeFirst_To_First() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<TestObject> iterator = ArrayIteratorFactory.ofRange(array, 0, 0);
		log.info(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		assertThat(iterator.getCountFromRange(), is(itemsCount));
		assertThat(1, is(itemsCount));
	}

	@DisplayName("Test sprawdzenia poprawności odczytanego ostatniego elementu kolekcji")
	@Test
	public void testShouldCount1_OfRangeLast_To_Last() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<TestObject> iterator = ArrayIteratorFactory.ofRange(array, 11, 11);
		log.info(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		assertThat(iterator.getCountFromRange(), is(itemsCount));
		assertThat(1, is(itemsCount));
	}

	static class TestObject {
		private String name;
		private byte age;
		private double weight;
		private double height;

		public TestObject(final String name, final byte age, final double weight, final double height) {

			super();
			this.name = name;
			this.age = age;
			this.weight = weight;
			this.height = height;
		}

		public String getName() {

			return name;
		}

		public void setName(final String name) {

			this.name = name;
		}

		public byte getAge() {

			return age;
		}

		public void setAge(final byte age) {

			this.age = age;
		}

		public double getWeight() {

			return weight;
		}

		public void setWeight(final double weight) {

			this.weight = weight;
		}

		public double getHeight() {

			return height;
		}

		public void setHeight(final double height) {

			this.height = height;
		}
	}

}
