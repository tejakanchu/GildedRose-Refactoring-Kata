package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            handleUpdateQuality(item);
            updateSellIn(item);
            handleItemsExpired(item);
        }
    }

    private void handleUpdateQuality(Item item) {
        if (isAgedBrie(item) || isBackstagePasses(item)) {

            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (isBackstagePasses(item)) {
                    if (item.sellIn < 11) {
                        incrementQuality(item);
                    }


                    if (item.sellIn < 6) {
                        incrementQuality(item);
                    }
                }
            }
        } else if (!isSulfuras(item)){
            decrementQuality(item);
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isConjured(Item item) {
        return item.name.equals("Conjured");
    }

    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void handleItemsExpired(Item item) {
        if (item.sellIn < 0) {
            handleExpired(item);
        }
    }

    private void handleExpired(Item item) {
        if (isAgedBrie(item)) {
            incrementQuality(item);
        } else {
            if (isBackstagePasses(item)) {
                item.quality = 0;
            } else {
                if (!isSulfuras(item)) {
                    decrementQuality(item);
                }
            }
        }
    }

    private void decrementQuality(Item item) {
        if (item.quality > 0) {

            if(isConjured(item)){
                item.quality = item.quality - 2;
            }else if (!isSulfuras(item)) {
                item.quality = item.quality - 1;
            }
        }
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}
