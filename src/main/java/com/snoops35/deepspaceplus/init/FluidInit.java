package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.fluids.FluidLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit
{
    public static final Fluid GELID_MENRIL = new FluidLiquid("gelid_menril", new ResourceLocation("deepspaceplus:blocks/gelid_menril_still"), new ResourceLocation("deepspaceplus:blocks/gelid_menril_flow"));

    public static void registerFluids()
    {
        registerFluid(GELID_MENRIL);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
