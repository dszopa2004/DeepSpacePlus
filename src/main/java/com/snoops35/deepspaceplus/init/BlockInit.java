package com.snoops35.deepspaceplus.init;

import java.util.ArrayList;
import java.util.List;

import com.snoops35.deepspaceplus.blocks.BlockFluid;
import com.snoops35.deepspaceplus.blocks.ForceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FORCE_BLOCK = new ForceBlock("force_block", Material.GROUND);
	public static final Block GELID_MENRIL_BLOCK = new BlockFluid("gelid_menril", FluidInit.GELID_MENRIL, Material.LAVA);
}
