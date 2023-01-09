package com.snoops35.deepspaceplus.init;

import java.util.ArrayList;
import java.util.List;

import com.snoops35.deepspaceplus.core.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FORCE_BLOCK = new BlockBase("force_block", Material.GROUND);
}
