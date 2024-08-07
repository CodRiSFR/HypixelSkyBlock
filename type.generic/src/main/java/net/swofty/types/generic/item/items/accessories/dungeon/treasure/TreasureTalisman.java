package net.swofty.types.generic.item.items.accessories.dungeon.treasure;

import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.NotFinishedYet;
import net.swofty.types.generic.item.impl.SkullHead;
import net.swofty.types.generic.item.impl.TieredTalisman;
import net.swofty.types.generic.user.SkyBlockPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TreasureTalisman implements TieredTalisman, NotFinishedYet, SkullHead {
    @Override
    public List<String> getTalismanDisplay() {
        return List.of("§7Grants §a+1% §7extra loot to end",
                "§7of dungeon chests.");
    }

    @Override
    public String getSkullTexture(@Nullable SkyBlockPlayer player, SkyBlockItem item) {
        return "31f320025142596396032cc0088e2ac36489f24cfa5e9dda13e081cf69f77f4d";
    }

    @Override
    public ItemTypeLinker getBaseTalismanTier() {
        return ItemTypeLinker.TREASURE_TALISMAN;
    }

    @Override
    public Integer getTier() {
        return 1;
    }
}
