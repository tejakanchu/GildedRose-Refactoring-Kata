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
    public void sellInDateDecreases_butQualityCannotBeNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    public void qualityDecreases() {
        Item[] items = new Item[] { new Item("Others", 10, 10) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    public void qualityDecreasesForMultipleItems() {
        Item[] items = new Item[] { new Item("Others1", 10, 10), new Item("Others2", 8, 8) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, items[0].quality);
        assertEquals(9, items[0].sellIn);
        assertEquals(7, items[1].quality);
        assertEquals(7, items[1].sellIn);
    }

    @Test
    public void qualityDecreasesTwiceFasterAfterSellInDateExpired() {
        Item[] items = new Item[] { new Item("Others", 0, 10) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(8, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    public void qualityDecreasesTwiceWithNegativeSellInDate() {
        Item[] items = new Item[] { new Item("Others", -1, 10) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(8, items[0].quality);
        assertEquals(-2, items[0].sellIn);
    }

    @Test
    public void QualityNeverFallsBelowZero() {
        Item[] items = new Item[] { new Item("Others", 10, 0) };

        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, gildedRose.items[0].sellIn);
        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void QualityNeverIncreasesBeyond50ForAgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(9, gildedRose.items[0].sellIn);
        assertEquals(50, gildedRose.items[0].quality);
    }

    @Test
    public void QualityAndSellInNeverLowersForSulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 10) };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(10, gildedRose.items[0].sellIn);
        assertEquals(10, gildedRose.items[0].quality);
    }

    @Test
    public void backstagePassesQualityIs0AfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    public void increasesBackstagePassesQualityThriceAsFastWhenSellInValueIsEqualOrLessThan5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(4, app.items[0].sellIn);
        assertEquals(13, app.items[0].quality);
    }

    @Test
    public void increasesBackstagePassesQualityTwiceAsFastWhenSellInValueIsEqualOrLessThan10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        Item[] items = new Item[] { new Item("Conjured", 2, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(28, items[0].quality);
        assertEquals(1, items[0].sellIn);
    }

    @Test
    public void conjuredItemsDegradesInQualityBy4IfExpired() {
        Item[] items = new Item[] { new Item("Conjured", 0, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(26, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    public void conjuredItemsDegradesInQualityBy4IfExpired1() {
        Item[] items = new Item[] { new Item("Conjured", 10, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
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

}
