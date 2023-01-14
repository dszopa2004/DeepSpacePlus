package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.potions.NullEffectPotion;
import com.snoops35.deepspaceplus.potions.PositiveEffectPotion;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit
{
    public static final Potion NULL_FIELD_EFFECT = new NullEffectPotion("null_field", true, 8683390, 0, 0);
    public static final PotionType NULL_FIELD = new PotionType("null_field", new PotionEffect[] {new PotionEffect(NULL_FIELD_EFFECT, 2400)}).setRegistryName("null_field");
    public static final PotionType LONG_NULL_FIELD = new PotionType("null_field", new PotionEffect[] {new PotionEffect(NULL_FIELD_EFFECT, 4800)}).setRegistryName("long_null_field");

    public static final Potion POSITIVE_EFFECT = new PositiveEffectPotion("positive_effect", false, 8683390, 0, 0);
    public static final PotionType POSITIVE_POTION = new PotionType("positive_effect", new PotionEffect[] {new PotionEffect(POSITIVE_EFFECT, 2400)}).setRegistryName("positive_effect");
    public static final PotionType LONG_POSITIVE_POTION = new PotionType("positive_effect", new PotionEffect[] {new PotionEffect(POSITIVE_EFFECT, 4800)}).setRegistryName("long_positive_effect");

    public static void registerPotions()
    {
        registerPotion(NULL_FIELD, LONG_NULL_FIELD, NULL_FIELD_EFFECT);
        registerPotion(POSITIVE_POTION, LONG_POSITIVE_POTION, POSITIVE_EFFECT);

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

        PotionHelper.addMix(POSITIVE_POTION, Items.REDSTONE, LONG_POSITIVE_POTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.ROTTEN_FLESH, LONG_POSITIVE_POTION);
    }
}
