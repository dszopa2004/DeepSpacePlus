package com.snoops35.deepspaceplus.core;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ForceBlock extends Block implements IHasModel
{
    public static final AxisAlignedBB FORCE_COLLISION_AABB = new AxisAlignedBB(0.999,0.999,0.999,0.999,0.999,0.999);

    public ForceBlock(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        setLightLevel(7.0f);
        setBlockUnbreakable();

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    @Override
    public EnumPushReaction getMobilityFlag(IBlockState state) {
        return EnumPushReaction.BLOCK;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return FORCE_COLLISION_AABB;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.addVelocity(1,1,1);
    }

    @Override
    public void registerModels() { DeepSpacePlus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); }
}
