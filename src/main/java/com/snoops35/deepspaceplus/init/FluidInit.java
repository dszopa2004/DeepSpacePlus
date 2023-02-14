package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.fluids.CaelumLiquid;
import com.snoops35.deepspaceplus.fluids.AmbrosiaLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit
{
    public static final Fluid AMBROSIA = new AmbrosiaLiquid("ambrosia", new ResourceLocation("deepspaceplus:blocks/ambrosia_still"), new ResourceLocation("deepspaceplus:blocks/ambrosia_flow"));
    public static final Fluid CAELUM = new CaelumLiquid("caelum", new ResourceLocation("deepspaceplus:blocks/caelum_sap_still"), new ResourceLocation("deepspaceplus:blocks/caelum_sap_flow"));


    public static void registerFluids()
    {
        registerFluid(AMBROSIA);
        registerFluid(CAELUM);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
