package com.gildedrose;

class GildedRose {
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            handleUpdateQuality(item);
            updateSellIn(item);
            if (item.sellIn < 0) {
                handleExpired(item);
            }
        }
    }

    private void handleUpdateQuality(Item item) {
        if (isAgedBrie(item)) {
            incrementQuality(item);

        } else if(isBackstagePasses(item)){

            incrementQuality(item);

            if (item.quality < 50) {
                if (item.sellIn < 11) {
                    incrementQuality(item);
                }

                if (item.sellIn < 6) {
                    incrementQuality(item);
                }
            }

        } else if (!isSulfuras(item)){
            decrementQuality(item);
        }
    }

    private void handleExpired(Item item) {
        if (isAgedBrie(item)) {
            incrementQuality(item);
        } else if (isBackstagePasses(item)) {
            item.quality = 0;
        } else if (isSulfuras(item)) {
            return;
        } else {
            decrementQuality(item);
        }
    }

    private void decrementQuality(Item item) {
        if (item.quality > 0) {

            if (isConjured(item)){
                item.quality = item.quality - 2;
            } else if (!isSulfuras(item)) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void updateSellIn(Item item) {
        if (isSulfuras(item)) {
            return;
        }
        item.sellIn--;
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isConjured(Item item) {
        return item.name.equals(CONJURED);
    }
}
