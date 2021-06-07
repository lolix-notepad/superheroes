package me.xemor.superheroes2.skills.implementations;

import me.xemor.superheroes2.SkillCooldownHandler;
import me.xemor.superheroes2.data.HeroHandler;
import me.xemor.superheroes2.skills.Skill;
import me.xemor.superheroes2.skills.skilldata.ConvertBlockData;
import me.xemor.superheroes2.skills.skilldata.SkillData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Collection;

public class ConvertBlockSkill extends SkillImplementation {

    SkillCooldownHandler skillCooldownHandler = new SkillCooldownHandler();

    public ConvertBlockSkill(HeroHandler heroHandler) {
        super(heroHandler);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            Collection<SkillData> skillDatas = heroHandler.getSuperhero(player).getSkillData(Skill.getSkill("CONVERTBLOCK"));
            for (SkillData skillData : skillDatas) {
                ConvertBlockData convertBlockData = (ConvertBlockData) skillData;
                Block block = e.getClickedBlock();
                if (block == null) {
                    return;
                }
                if (convertBlockData.areConditionsTrue(player, block)) {
                    if (skillCooldownHandler.isCooldownOver(convertBlockData, player.getUniqueId())) {
                        if (convertBlockData.getInputBlocks().contains(block.getType())) {
                            block.setType(convertBlockData.getOutputBlock());
                            skillCooldownHandler.startCooldown(convertBlockData, player.getUniqueId());
                        }
                    }
                }
            }
        }
    }

}
