package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.potions.AegisAuraPotion;
import com.snoops35.deepspaceplus.potions.NullEffectPotion;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit
{
    public static final Potion NULL_FIELD_EFFECT = new NullEffectPotion("null_field", true, 8683390);
    public static final PotionType NULL_FIELD = new PotionType("null_field", new PotionEffect[] {new PotionEffect(NULL_FIELD_EFFECT, 2400)}).setRegistryName("null_field");
    public static final PotionType LONG_NULL_FIELD = new PotionType("null_field", new PotionEffect[] {new PotionEffect(NULL_FIELD_EFFECT, 4800)}).setRegistryName("long_null_field");

    public static final Potion AEGIS_AURA = new AegisAuraPotion("aegis_aura", false, 8683390);
    public static final PotionType AEGIS_AURA_POTION = new PotionType("aegis_aura", new PotionEffect[] {new PotionEffect(AEGIS_AURA, 2400)}).setRegistryName("aegis_aura");
    public static final PotionType LONG_AEGIS_AURA_POTION = new PotionType("aegis_aura", new PotionEffect[] {new PotionEffect(AEGIS_AURA, 4800)}).setRegistryName("long_aegis_aura");

    public static void registerPotions()
    {
        registerPotion(NULL_FIELD, LONG_NULL_FIELD, NULL_FIELD_EFFECT);
        registerPotion(AEGIS_AURA_POTION, LONG_AEGIS_AURA_POTION, AEGIS_AURA);

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

        PotionHelper.addMix(AEGIS_AURA_POTION, Items.REDSTONE, LONG_AEGIS_AURA_POTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.ROTTEN_FLESH, LONG_AEGIS_AURA_POTION);
    }
}
