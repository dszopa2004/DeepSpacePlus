package com.snoops35.deepspaceplus.init;

import java.util.ArrayList;
import java.util.List;

import com.snoops35.deepspaceplus.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FORCE_BLOCK = new ForceBlock("force_block", Material.GROUND);
	public static final Block MUD_BLOCK = new BlockBase("mud", Material.GROUND);
	public static final Block BROWN_GRASS = new BrownGrass("brown_grass", Material.GRASS);

	public static final Block CAELUM_PLANKS = new CaelumPlanks("caelum_planks", Material.WOOD);
	public static final Block CAELUM_LOG = new CaelumLog("caelum_log");
	public static final Block CAELUM_LEAF_ONE = new CaelumLeaves("caelum_leaves_1");
	public static final Block CAELUM_LEAF_TWO = new CaelumLeaves("caelum_leaves_2");
	public static final Block CAELUM_LEAF_THREE = new CaelumLeaves("caelum_leaves_3");
	public static final Block CAELUM_SAPLING = new CaelumSapling("caelum_sapling");

	public static final Block AMBROSIA_BLOCK = new BlockFluid("ambrosia", FluidInit.AMBROSIA, Material.WATER);
	public static final Block CAELUM_BLOCK = new BlockFluid("caelum", FluidInit.CAELUM, Material.WATER);
}
