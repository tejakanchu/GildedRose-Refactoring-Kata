package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void test_Existing_Functionality() {
        Item[] items = new Item[]{
            new Item("Aged Brie", 2, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Elixir of the Monkey", 5, 7),
            new Item("Other kind", 10, 20)
        };


        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(1, items[0].quality);
        assertEquals(21, items[1].quality);
        assertEquals(14, items[1].sellIn);
        assertEquals(80, items[2].quality);
        assertEquals(0, items[2].sellIn);
        assertEquals(6, items[3].quality);
        assertEquals(4, items[3].sellIn);
        assertEquals(19, items[4].quality);
        assertEquals(9, items[4].sellIn);
    }

    /*@Test
    public void decreasesBy1EveryDayBeforeSelByDate(String name, int sellIn, int quality){
        Item item = new Item(name, sellIn, quality);
        Item[] items = new Item[] {item};
        GildedRose gildedRose = new GildedRose(items);
        assertEquals(sellIn-1, item.sellIn);


    }*/

    @Test
    public void sellInDateDecreases_butQualityCannotBeNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    public void qualityDecreases() {
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    public void qualityDecreasesFasterAfterSellInDateExpired() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(8, items[0].quality);
        assertEquals(-1, items[0].sellIn);

    }

}
