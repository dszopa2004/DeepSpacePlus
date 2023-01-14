package com.snoops35.deepspaceplus.potions;

import com.snoops35.deepspaceplus.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class NullEffectPotion extends Potion
{
    public NullEffectPotion(String name, boolean isBadPotion, int color, int iconIndexX, int iconIndexY)
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
