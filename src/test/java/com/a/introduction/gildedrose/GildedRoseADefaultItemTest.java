package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseADefaultItemTest {

    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int DEFAULT_SELL_IN = 15;
    public static final int EXPIRED_SELL_IN = -1;
    public static final int DEFAULT_QUALITY = 3;
    public static final int SELL_IN_DECREMENT = 1;
    public static final int QUALITY_DECREMENT_WHEN_NOT_EXPIRED = 1;
    public static final int QUALITY_DECREMENT_WHEN_EXPIRED = 2;

//    /**
//     * Method to test the variation in quality of the item for the non expired
//     * Item.
//     *
//     * The quality should decrease by 1 when the item is not expired
//     * and sell in should decrease by 1.
//     *
//     */
//    @Test
//    public void testUpdateQualityDefault1() {
//        Item item = new Item("DEFAULT_ITEM", 15, 3);
//        Item[] items = new Item[] { item };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertEquals("DEFAULT_ITEM", app.items[0].name);
//        assertEquals(14, app.items[0].sellIn);
//        assertEquals(2, app.items[0].quality);
//    }
//
//    /**
//     * Method to test the variation in quality of the item for the non expired
//     * Item.
//     *
//     * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
//     *
//     */
//    @Test
//    public void testUpdateQualityForExpiredItem() {
//        Item item = new Item("DEFAULT_ITEM", -1, 3);
//        Item[] items = new Item[] { item };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertEquals("DEFAULT_ITEM", app.items[0].name);
//        assertEquals(-2, app.items[0].sellIn);
//        assertEquals(1, app.items[0].quality);
//    }

    @Test
    public void decreaseQualityBy1_And_decreaseSellInBy1_If_ItemNotExpired() {
        GildedRose gildedRose = getGildedRoseWithOneItem(DEFAULT_ITEM, DEFAULT_SELL_IN, DEFAULT_QUALITY);

        gildedRose.updateQuality();

        Item expected = new Item(DEFAULT_ITEM, DEFAULT_SELL_IN - SELL_IN_DECREMENT, DEFAULT_QUALITY - QUALITY_DECREMENT_WHEN_NOT_EXPIRED);

        assertItemEquals(expected, gildedRose.items[0]);
    }

    @Test
    public void decreaseQualityBy2_And_decreaseSellInBy1_If_ItemExpired() {
        GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);

        app.updateQuality();

        Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - SELL_IN_DECREMENT, DEFAULT_QUALITY - QUALITY_DECREMENT_WHEN_EXPIRED);

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