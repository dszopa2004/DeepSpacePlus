package com.snoops35.deepspaceplus.init;

import java.util.ArrayList;
import java.util.List;

import com.snoops35.deepspaceplus.blocks.BlockBase;
import com.snoops35.deepspaceplus.blocks.BlockFluid;
import com.snoops35.deepspaceplus.blocks.ForceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FORCE_BLOCK = new ForceBlock("force_block", Material.GROUND);
	public static final Block MUD_BLOCK = new BlockBase("mud", Material.GROUND);
	public static final Block AMBROSIA_BLOCK = new BlockFluid("ambrosia", FluidInit.AMBROSIA, Material.WATER);
	public static final Block CAELUM_BLOCK = new BlockFluid("caelum", FluidInit.CAELUM, Material.WATER);
}
