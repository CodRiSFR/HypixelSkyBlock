package net.swofty.types.generic.minion.extension.extensions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.item.ItemComponent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.gui.inventory.inventories.GUIMinion;
import net.swofty.types.generic.gui.inventory.item.GUIClickableItem;
import net.swofty.types.generic.item.ItemTypeLinker;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.item.impl.MinionUpgradeItem;
import net.swofty.types.generic.item.updater.NonPlayerItemUpdater;
import net.swofty.types.generic.minion.IslandMinionData;
import net.swofty.types.generic.minion.extension.MinionExtension;
import net.swofty.types.generic.user.SkyBlockPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class MinionUpgradeExtension extends MinionExtension {

    public MinionUpgradeExtension(@Nullable ItemTypeLinker itemTypeLinker, @Nullable Object data) {
        super(itemTypeLinker, data);
    }

    @Override
    public @NotNull GUIClickableItem getDisplayItem(IslandMinionData.IslandMinion minion, int slot) {
        if (getItemTypeLinkerPassedIn() == null) {
            return new GUIClickableItem(slot) {
                @Override
                public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                    SkyBlockItem upgradeItem = new SkyBlockItem(e.getCursorItem());

                    if (upgradeItem.getGenericInstance() == null) {
                        player.sendMessage("§cThis item is not a valid Minion Upgrade.");
                        e.setCancelled(true);
                        return;
                    }

                    ItemTypeLinker itemTypeLinker = upgradeItem.getAttributeHandler().getPotentialClassLinker();
                    if (minion.getExtensionData().hasMinionUpgrade(itemTypeLinker)) {
                        player.sendMessage("§cThis upgrade is already applied to your minion.");
                        e.setCancelled(true);
                        return;
                    }

                    if (upgradeItem.getGenericInstance() instanceof MinionUpgradeItem) {
                        e.setClickedItem(ItemStack.AIR);
                        setItemTypeLinkerPassedIn(itemTypeLinker);
                        minion.getExtensionData().setData(slot, MinionUpgradeExtension.this);
                    } else {
                        player.sendMessage("§cThis item is not a valid Minion Upgrade.");
                        e.setCancelled(true);
                    }
                }

                @Override
                public void runPost(InventoryClickEvent e, SkyBlockPlayer player) {
                    new GUIMinion(minion).open(player);
                }

                @Override
                public boolean canPickup() {
                    return true;
                }

                @Override
                public ItemStack.Builder getItem(SkyBlockPlayer player) {
                    return ItemStackCreator.getStack("§aUpgrade Slot", Material.YELLOW_STAINED_GLASS_PANE, 1,
                            "§7You can improve your minion by",
                            "§7adding a minion upgrade item",
                            "§7here.");
                }
            };
        } else {
            return new GUIClickableItem(slot) {
                @Override
                public void run(InventoryPreClickEvent e, SkyBlockPlayer player) {
                    if (!e.getCursorItem().isAir()) {
                        player.sendMessage("§cYour cursor must be empty to pick this item up!");
                        e.setCancelled(true);
                        return;
                    }

                    player.addAndUpdateItem(getItemTypeLinkerPassedIn());
                    setItemTypeLinkerPassedIn(null);
                    e.setClickedItem(ItemStack.AIR);
                    minion.getExtensionData().setData(slot, MinionUpgradeExtension.this);
                }

                @Override
                public void runPost(InventoryClickEvent e, SkyBlockPlayer player) {
                    new GUIMinion(minion).open(player);
                }

                @Override
                public boolean canPickup() {
                    return true;
                }

                @Override
                public ItemStack.Builder getItem(SkyBlockPlayer player) {
                    ItemStack.Builder item = new NonPlayerItemUpdater(new SkyBlockItem(getItemTypeLinkerPassedIn())).getUpdatedItem();
                   item.set(ItemComponent.CUSTOM_NAME, Component.text("§aUpgrade Slot").decoration(TextDecoration.ITALIC, false));
                    item = ItemStackCreator.updateLore(item, Stream.of(
                            "§7You can improve your minion by",
                            "§7adding a minion upgrade item",
                            "§7here.",
                            " ",
                            "§7Current Upgrade: " + getItemTypeLinkerPassedIn().type.rarity.getColor() + getItemTypeLinkerPassedIn().getDisplayName(null),
                            " ",
                            "§eClick to remove."
                    ).toList());

                    return item;
                }
            };
        }
    }

    @Override
    public String toString() {
        if (getItemTypeLinkerPassedIn() == null)
            return "null";
        return getItemTypeLinkerPassedIn().name();
    }

    @Override
    public void fromString(String string) {
        if (string.equals("null")) {
            setItemTypeLinkerPassedIn(null);
            return;
        }
        setItemTypeLinkerPassedIn(ItemTypeLinker.valueOf(string));
    }
}
