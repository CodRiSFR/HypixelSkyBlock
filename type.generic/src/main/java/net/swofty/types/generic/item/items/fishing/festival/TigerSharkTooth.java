package net.swofty.types.generic.item.items.fishing.festival;

import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.CustomSkyBlockItem;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.commons.statistics.ItemStatistics;

import java.util.ArrayList;
import java.util.Arrays;

public class TigerSharkTooth implements CustomSkyBlockItem {
    @Override
    public ItemStatistics getStatistics(SkyBlockItem instance) {
        return ItemStatistics.empty();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§7Dropped by a Shark during",
                "§7Fishing Festival."));
    }
}
