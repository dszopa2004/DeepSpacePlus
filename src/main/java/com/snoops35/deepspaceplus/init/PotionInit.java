package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.potions.CustomPotion;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit
{
    public static final Potion NULL_FIELD_EFFECT = new CustomPotion("null_field", true, 8683390, 0, 0);

    public static final PotionType NULL_FIELD = new PotionType("null_field", new PotionEffect[] {new PotionEffect(NULL_FIELD_EFFECT, 2400)}).setRegistryName("null_field");
    public static final PotionType LONG_NULL_FIELD = new PotionType("null_field", new PotionEffect[] {new PotionEffect(NULL_FIELD_EFFECT, 4800)}).setRegistryName("long_null_field");

    public static void registerPotions()
    {
        registerPotion(NULL_FIELD, LONG_NULL_FIELD, NULL_FIELD_EFFECT);

        registerPotionMixes();
    }

    private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect)
    {
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    private static void registerPotionMixes()
    {
        PotionHelper.addMix(NULL_FIELD, Items.REDSTONE, LONG_NULL_FIELD);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.ROTTEN_FLESH, LONG_NULL_FIELD);
    }
}
