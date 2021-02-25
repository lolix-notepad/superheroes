package me.xemor.superheroes2.skills.skilldata;

import me.xemor.superheroes2.skills.Skill;
import me.xemor.superheroes2.skills.skilldata.configdata.ItemStackData;
import me.xemor.superheroes2.skills.skilldata.configdata.ParticleData;
import me.xemor.superheroes2.skills.skilldata.configdata.SoundData;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

public class GunData extends SkillData {

    private double damage;
    private double cooldown;
    private double maxDistance;
    private double bulletSize;
    private String cooldownMessage;
    private ItemStackData itemStackData;
    private SoundData shootSoundData;
    private ParticleData trailParticle;
    private ParticleData hitParticle;

    protected GunData(Skill skill, ConfigurationSection configurationSection) {
        super(skill, configurationSection);
        maxDistance = configurationSection.getDouble("maxDistance", 64);
        bulletSize = configurationSection.getDouble("bulletSize", 1.0);
        damage = configurationSection.getDouble("damage", 5.0);
        cooldown = configurationSection.getDouble("cooldown", 2);
        cooldownMessage = ChatColor.translateAlternateColorCodes('&', configurationSection.getString("cooldownMessage", "&8&lGun &7has %s seconds left until it can be used again!"));
        ConfigurationSection itemSection = configurationSection.getConfigurationSection("item");
        if (itemSection != null) {
            itemStackData = new ItemStackData(itemSection);
        }
        ConfigurationSection shootSoundSection = configurationSection.getConfigurationSection("shootSound");
        if (shootSoundSection != null) {
            shootSoundData = new SoundData(shootSoundSection);
        }
        ConfigurationSection trailParticleSection = configurationSection.getConfigurationSection("trailParticle");
        if (trailParticleSection != null) {
            trailParticle = new ParticleData(trailParticleSection);
        }
        ConfigurationSection hitParticleSection = configurationSection.getConfigurationSection("hitParticle");
        if (hitParticleSection != null) {
            hitParticle = new ParticleData(hitParticleSection);
        }
    }

    public double getDamage() {
        return damage;
    }

    public double getCooldown() {
        return cooldown;
    }

    public String getCooldownMessage() {
        return cooldownMessage;
    }

    public ItemStackData getItemStackData() {
        return itemStackData;
    }

    public SoundData getShootSoundData() {
        return shootSoundData;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public double getBulletSize() {
        return bulletSize;
    }

    public ParticleData getTrailParticle() {
        return trailParticle;
    }

    public ParticleData getHitParticle() {
        return hitParticle;
    }
}
