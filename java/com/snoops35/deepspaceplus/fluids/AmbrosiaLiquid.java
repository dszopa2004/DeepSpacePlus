package com.snoops35.deepspaceplus.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class AmbrosiaLiquid extends Fluid
{
    public AmbrosiaLiquid(String name, ResourceLocation still, ResourceLocation flow)
    {
        super(name, still, flow);
        this.setUnlocalizedName(name);
        this.viscosity = 5000;
    }
}
