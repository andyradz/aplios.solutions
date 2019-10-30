package com.codigo.aplios.sdk.core.array;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

public class TestsLongArrayIterator {

	private static Logger log = Logger.getLogger(TestsLongArrayIterator.class);

	static final Long[] array = {
			9L,
			99L,
			9L,
			232343L,
			2321L,
			1231L,
			12L,
			-9L,
			-12L,
			923L,
			3L,
			-4L };

	@Test
	public void testShouldCount12_Of() {

		// ...Arrange
		int itemsCount = 0;

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.of(array);
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

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.ofRange(array, 0, 0);
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

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.ofRange(array, 11, 11);
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

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.ofRange(array, 1, 9);
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

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.ofCount(array, 1);
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

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.ofFirst(array, 10);
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

		final ArrayIterable<Long> iterator = ArrayIteratorFactory.ofLast(array, 5);
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
