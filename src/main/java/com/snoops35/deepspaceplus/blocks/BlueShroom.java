package com.snoops35.deepspaceplus.blocks;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlueShroom extends BlockBreakable implements IHasModel
{
    public BlueShroom(String name, Material materialIn, boolean ignoreSimilarityIn)
    {
        super(materialIn, ignoreSimilarityIn);

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        setHardness(1.0f);
        setLightLevel(10f);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    @Override
    public void registerModels() {
        DeepSpacePlus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
