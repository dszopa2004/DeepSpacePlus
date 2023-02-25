package com.snoops35.deepspaceplus.potions;

import com.snoops35.deepspaceplus.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NullEffectPotion extends Potion
{
    ResourceLocation icon = new ResourceLocation(Reference.MODID + ":textures/gui/null_field.png");
    public NullEffectPotion(String name, boolean isBadPotion, int color)
    {
        super(isBadPotion, color);
        setPotionName("effect." + name);
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
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
    {
        mc.getTextureManager().bindTexture(icon);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
    {
        mc.getTextureManager().bindTexture(icon);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }
}
