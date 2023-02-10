package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.fluids.FluidLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit
{
    public static final Fluid AMBROSIA = new FluidLiquid("ambrosia", new ResourceLocation("deepspaceplus:blocks/ambrosia_still"), new ResourceLocation("deepspaceplus:blocks/ambrosia_flow"));

    public static void registerFluids()
    {
        registerFluid(AMBROSIA);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
