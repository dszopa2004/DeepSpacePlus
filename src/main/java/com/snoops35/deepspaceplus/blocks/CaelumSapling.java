package com.snoops35.deepspaceplus.blocks;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import com.snoops35.deepspaceplus.worldgen.WorldGenTreesCaelum;
import com.snoops35.deepspaceplus.worldgen.WorldGenTreesCaelumThree;
import com.snoops35.deepspaceplus.worldgen.WorldGenTreesCaelumTwo;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class CaelumSapling extends BlockBush implements IHasModel, IGrowable
{
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);


    public CaelumSapling(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);

        setHardness(0.0F);
        setSoundType(SoundType.PLANT);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                grow(worldIn, rand, pos, state);
            }
        }
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int leaves = generateRandomTreeType();
        if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;

        switch (leaves)
        {
            case 0:
                WorldGenerator worldgeneratorone = new WorldGenTreesCaelum(true);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
                worldgeneratorone.generate(worldIn, rand, pos);
                break;

            case 1:
                WorldGenerator worldgeneratortwo = new WorldGenTreesCaelumTwo(true);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
                worldgeneratortwo.generate(worldIn, rand, pos);
                break;

            case 2:
                WorldGenerator worldgeneratorthree = new WorldGenTreesCaelumThree(true);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
                worldgeneratorthree.generate(worldIn, rand, pos);
                break;
        }
    }

    public int generateRandomTreeType()
    {
        int min = 0;
        int max = 2;
        int rand_leaves = (int)Math.floor(Math.random() * (max - min + 1) + min);

        return rand_leaves;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        if (state.getValue(STAGE).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            generateTree(worldIn, pos, state, rand);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(STAGE).intValue() << 3;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {STAGE});
    }

    @Override
    public void registerModels() {
        DeepSpacePlus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
