package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseBAgedBrieTest {
	public static final int DEFAULT_SELL_IN = 4;
	public static final int SELL_IN_DECREMENT = 1;
	public static final int DEFAULT_QUALITY = 3;
	public static final int QUALITY_INCREMENT_WHEN_NOT_EXPIRED = 1;
	public static final int QUALITY_INCREMENT_WHEN_EXPIRED = 2;
	public static final String AGED_BRIE = "Aged Brie";
	public static final int EXPIRED_SELL_IN = -1;
	public static final int MAX_QUALITY = 50;

//	@Test
//	public void testUpdateQualityAgedBrie1() {
//		Item item = new Item("Aged Brie", 4, 3);
//		Item[] items = new Item[] { item };
//		GildedRose app = new GildedRose(items);
//		app.updateQuality();
//		assertEquals("Aged Brie", app.items[0].name);
//		assertEquals(3, app.items[0].sellIn);
//		assertEquals(4, app.items[0].quality);
//	}
//
//	@Test
//	public void testUpdateQualityAgedBrie2() {
//		Item item = new Item("Aged Brie", -1, 3);
//		Item[] items = new Item[] { item };
//		GildedRose app = new GildedRose(items);
//		app.updateQuality();
//		assertEquals("Aged Brie", app.items[0].name);
//		assertEquals(-2, app.items[0].sellIn);
//		assertEquals(5, app.items[0].quality);
//	}
//
//	@Test
//	public void testUpdateQualityAgedBrie3() {
//		Item item = new Item("Aged Brie", 4, 50);
//		Item[] items = new Item[] { item };
//		GildedRose app = new GildedRose(items);
//		app.updateQuality();
//		assertEquals("Aged Brie", app.items[0].name);
//		assertEquals(3, app.items[0].sellIn);
//		assertEquals(50, app.items[0].quality);
//	}

	@Test
	public void decrementSellInBy1_And_IncrementQualityBy1_If_ItemIsNotExpiredAgedBrie() {
		GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, DEFAULT_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, DEFAULT_SELL_IN - SELL_IN_DECREMENT, DEFAULT_QUALITY + QUALITY_INCREMENT_WHEN_NOT_EXPIRED);

		assertItemEquals(expected, app.items[0]);
	}


	@Test
	public void decrementSellInBy1_And_IncrementQualityBy2_If_ItemIsExpiredAgedBrie() {
		GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, EXPIRED_SELL_IN - SELL_IN_DECREMENT, DEFAULT_QUALITY + QUALITY_INCREMENT_WHEN_EXPIRED);

		assertItemEquals(expected, app.items[0]);
	}

	@Test
	public void decrementSellInBy1_If_ItemIsExpiredAgedBrie_With_MaxQuality() {
		GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, DEFAULT_SELL_IN, MAX_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, DEFAULT_SELL_IN - SELL_IN_DECREMENT, MAX_QUALITY);

		assertItemEquals(expected, app.items[0]);
	}

	private void assertItemEquals(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	private GildedRose getGildedRoseWithOneItem(String name, int sellIn, int quality) {
		Item item = new Item(name, sellIn, quality);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}
}
