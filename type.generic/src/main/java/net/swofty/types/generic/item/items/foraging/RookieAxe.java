package net.swofty.types.generic.item.items.foraging;

import net.swofty.commons.item.ReforgeType;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.CustomSkyBlockItem;
import net.swofty.types.generic.item.impl.Reforgable;
import net.swofty.commons.statistics.ItemStatistic;
import net.swofty.commons.statistics.ItemStatistics;
import net.swofty.commons.item.ItemType;

public class RookieAxe implements CustomSkyBlockItem, Reforgable {
    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.builder()
                .withBase(ItemStatistic.DAMAGE, 20D)
                .build();
    }

    @Override
    public ReforgeType getReforgeType() {
        return ReforgeType.AXES;
    }
}
