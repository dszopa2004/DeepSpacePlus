package com.snoops35.deepspaceplus.potions;

import com.snoops35.deepspaceplus.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class AegisEffectPotion extends Potion
{
    public AegisEffectPotion(String name, boolean isBadPotion, int color, int iconIndexX, int iconIndexY)
    {
        super(isBadPotion, color);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(Reference.MODID + ":" + name));
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public boolean hasStatusIcon()
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + "textures/gui/potion_effects.png"));
        return true;
    }
}
