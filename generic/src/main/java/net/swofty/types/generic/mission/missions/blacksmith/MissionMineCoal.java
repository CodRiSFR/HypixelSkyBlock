package net.swofty.types.generic.mission.missions.blacksmith;

import net.minestom.server.event.Event;
import net.minestom.server.item.Material;
import net.swofty.types.generic.data.DataHandler;
import net.swofty.types.generic.data.datapoints.DatapointDouble;
import net.swofty.types.generic.event.EventNodes;
import net.swofty.types.generic.event.EventParameters;
import net.swofty.types.generic.event.custom.CustomBlockBreakEvent;
import net.swofty.types.generic.levels.SkyBlockLevelCause;
import net.swofty.types.generic.mission.MissionData;
import net.swofty.types.generic.mission.SkyBlockMission;
import net.swofty.types.generic.mission.SkyBlockProgressMission;
import net.swofty.types.generic.region.RegionType;
import net.swofty.types.generic.skill.SkillCategories;
import net.swofty.types.generic.user.SkyBlockPlayer;

import java.util.*;

@EventParameters(description = "Mine coal mission",
        node = EventNodes.CUSTOM,
        requireDataLoaded = false)
public class MissionMineCoal extends SkyBlockProgressMission {
    @Override
    public Class<? extends Event> getEvent() {
        return CustomBlockBreakEvent.class;
    }

    @Override
    public void run(Event tempEvent) {
        CustomBlockBreakEvent event = (CustomBlockBreakEvent) tempEvent;
        MissionData data = event.getPlayer().getMissionData();

        if (data.isCurrentlyActive(this.getClass()) || data.hasCompleted(this.getClass())) {
            return;
        }

        if (event.getMaterial().equals(Material.COAL_ORE)) {
            MissionData.ActiveMission mission = data.getMission(this.getClass()).getKey();
            mission.setMissionProgress(mission.getMissionProgress() + 1);
            mission.checkIfMissionEnded(event.getPlayer());
        }
    }

    @Override
    public String getID() {
        return "mission_mine_coal";
    }

    @Override
    public String getName() {
        return "Mine Coal";
    }

    @Override
    public Map<String, Object> onStart(SkyBlockPlayer player, MissionData.ActiveMission mission) {
        return new HashMap<>();
    }

    @Override
    public void onEnd(SkyBlockPlayer player, Map<String, Object> customData, MissionData.ActiveMission mission) {
        mission.getObjectiveCompleteText(new ArrayList<>(List.of("§3100 §7Mining Experience", "§b5 SkyBlock XP"))).forEach(player::sendMessage);
        player.getSkills().setRaw(player, SkillCategories.MINING, player.getSkills().getRaw(SkillCategories.MINING) + 100);
        player.getSkyBlockExperience().addExperience(SkyBlockLevelCause.getMissionCause(getID()));
    }

    @Override
    public Set<RegionType> getValidRegions() {
        return Set.of(RegionType.BLACKSMITH, RegionType.COAL_MINE);
    }

    @Override
    public Double getAttachedSkyBlockXP() {
        return 5D;
    }

    @Override
    public int getMaxProgress() {
        return 10;
    }
}