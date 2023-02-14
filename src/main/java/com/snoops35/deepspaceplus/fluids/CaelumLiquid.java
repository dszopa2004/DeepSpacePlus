package com.snoops35.deepspaceplus.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class CaelumLiquid extends Fluid
{
    public CaelumLiquid(String name, ResourceLocation still, ResourceLocation flow)
    {
        super(name, still, flow);
        this.setUnlocalizedName(name);
        this.viscosity = 5000;
    }
}
