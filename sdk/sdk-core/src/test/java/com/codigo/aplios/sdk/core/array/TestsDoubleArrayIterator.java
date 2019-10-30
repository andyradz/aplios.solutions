package com.codigo.aplios.sdk.core.array;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class TestsDoubleArrayIterator {

	private static Logger log = Logger.getLogger(TestsDoubleArrayIterator.class);

	static final Double[] array = {
			0.9,
			99.2,
			09.3,
			232343.09,
			2321.0,
			1231.2,
			12D,
			.09,
			12D,
			.0923D,
			.3D,
			4.5D };

	@Test
	public void testShouldCount12_Of() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.of(array);
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

	@Test
	public void testShouldCount1_OfRangeFirst_To_First() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofRange(array, 0, 0);
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

	@Test
	public void testShouldCount1_OfRangeLast_To_Last() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofRange(array, 11, 11);
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

	@Test
	public void testShouldCount9_OfRangeOne_To_Nine() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofRange(array, 1, 9);
		log.info(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		assertThat(iterator.getCountFromRange(), is(itemsCount));
		assertThat(9, is(itemsCount));
	}

	@Test
	public void testShouldCount1_OfCount_One() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofCount(array, 1);
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

	@Test
	public void testShouldCount1_OfFrst_Ten() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofFirst(array, 10);
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

	@Test
	public void testShouldCount6_OfLast_Five() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Double> iterator = ArrayIteratorFactory.ofLast(array, 5);
		log.info(iterator);

		// ...Act
		while (iterator.hasNext()) {
			iterator.next();
			itemsCount++;
		}

		// ...Assert
		assertThat(iterator.getCountFromRange(), is(itemsCount));
		assertThat(6, is(itemsCount));
	}
}
