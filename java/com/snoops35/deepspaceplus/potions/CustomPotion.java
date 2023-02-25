package com.snoops35.deepspaceplus.potions;

import com.snoops35.deepspaceplus.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.lang.reflect.Field;

import java.util.Collections;

public class CustomPotion extends Potion
{
    public CustomPotion(String name, boolean isBadPotion, int color, int iconIndexX, int iconIndexY)
    {
        super(isBadPotion, color);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(Reference.MODID + ":" + name));
        String randomUUID = MathHelper.getRandomUUID().toString();

        this.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, randomUUID, -0.5D, 2);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier)
    {
        entity.setSprinting(false);
        entity.setHealth(entity.getHealth() - 0.5f);
    }

    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.gammaSetting = 1.0f;
        mc.gameSettings.saveOptions();
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + "textures/gui/potion_effects.png"));
        return true;
    }
}
