package net.swofty.types.generic.item.items.combat.slayer.spider.craftable;

import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.MaterialQuantifiable;
import net.swofty.types.generic.item.ReforgeType;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.*;
import net.swofty.types.generic.item.impl.recipes.ShapedRecipe;
import net.swofty.types.generic.user.SkyBlockPlayer;
import net.swofty.types.generic.user.statistics.ItemStatistic;
import net.swofty.types.generic.user.statistics.ItemStatistics;

import java.util.*;

public class ScorpionBow implements CustomSkyBlockItem, Craftable, Reforgable, ExtraRarityDisplay {
    @Override
    public SkyBlockRecipe<?> getRecipe() {
        Map<Character, MaterialQuantifiable> ingredientMap = new HashMap<>();
        ingredientMap.put('A', new MaterialQuantifiable(ItemType.ENCHANTED_EMERALD, 32));
        ingredientMap.put('B', new MaterialQuantifiable(ItemType.TARANTULA_SILK, 16));
        ingredientMap.put('C', new MaterialQuantifiable(ItemType.ENCHANTED_GOLD_INGOT, 32));
        ingredientMap.put(' ', new MaterialQuantifiable(ItemType.AIR, 1));
        List<String> pattern = List.of(
                " AB",
                "C B",
                " AB");

        return new ShapedRecipe(SkyBlockRecipe.RecipeType.SLAYER, new SkyBlockItem(ItemType.SCORPION_BOW), ingredientMap, pattern);
    }

    @Override
    public ItemStatistics getStatistics() {
        return ItemStatistics.builder()
                .with(ItemStatistic.DAMAGE, 110D)
                .with(ItemStatistic.STRENGTH, 10D)
                .build();
    }

    @Override
    public ArrayList<String> getLore(SkyBlockPlayer player, SkyBlockItem item) {
        return new ArrayList<>(Arrays.asList(
                "§6Ability: Stinger",
                "§8Fully charged shots while sneaking",
                "§7Slows the victim and deal §c35x",
                "§c§7your §cStrength ❁ §7as damage",
                "§7per second for §a6s§7.",
                "§8Mana Cost: §3150"));
    }

    @Override
    public ReforgeType getReforgeType() {
        return ReforgeType.BOWS;
    }

    @Override
    public String getExtraRarityDisplay() {
        return " BOW";
    }
}
