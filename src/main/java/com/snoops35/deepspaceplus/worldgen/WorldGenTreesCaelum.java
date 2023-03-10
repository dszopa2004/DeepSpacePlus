package com.snoops35.deepspaceplus.worldgen;

import com.snoops35.deepspaceplus.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class WorldGenTreesCaelum extends WorldGenAbstractTree
{
    private IBlockState TRUNK = BlockInit.CAELUM_LOG.getDefaultState();
    private IBlockState LEAF = BlockInit.CAELUM_LEAF_ONE.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private final int minTreeHeight = 7;

    public WorldGenTreesCaelum(boolean notify) {
        super(notify);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = i - j;
        int l = 2 + rand.nextInt(2);
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight())
        {
            for (int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1)
            {
                int j1;

                if (i1 - position.getY() < j)
                {
                    j1 = 0;
                }
                else
                {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1)
                {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1)
                    {
                        if (i1 >= 0 && i1 < worldIn.getHeight())
                        {
                            IBlockState state = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1));

                            if (!state.getBlock().isAir(state, worldIn, blockpos$mutableblockpos.setPos(k1, i1, l1)) && !state.getBlock().isLeaves(state, worldIn, blockpos$mutableblockpos.setPos(k1, i1, l1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);

                if (state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1)
                {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    int i3 = rand.nextInt(2);
                    int j3 = 1;
                    int k3 = 0;

                    for (int l3 = 0; l3 <= k; ++l3)
                    {
                        int j4 = position.getY() + i - l3;

                        for (int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2)
                        {
                            int j2 = i2 - position.getX();

                            for (int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2)
                            {
                                int l2 = k2 - position.getZ();

                                if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0)
                                {
                                    BlockPos blockpos = new BlockPos(i2, j4, k2);
                                    state = worldIn.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos))
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                    }
                                }
                            }
                        }

                        if (i3 >= j3)
                        {
                            i3 = k3;
                            k3 = 1;
                            ++j3;

                            if (j3 > l)
                            {
                                j3 = l;
                            }
                        }
                        else
                        {
                            ++i3;
                        }
                    }

                    int i4 = rand.nextInt(3);

                    for (int k4 = 0; k4 < i - i4; ++k4)
                    {
                        BlockPos upN = position.up(k4);
                        state = worldIn.getBlockState(upN);

                        if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(k4), TRUNK);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private boolean isLocationValid(World world, BlockPos pos, int height)
    {
        // Check that the tree can be generated without intersecting any existing blocks
        for (int y = pos.getY(); y <= pos.getY() + height; y++) {
            if (!world.isAirBlock(new BlockPos(pos.getX(), y, pos.getZ()))) {
                return false;
            }
        }
        // Check that the tree can be generated on dirt or grass
        Block soil = world.getBlockState(pos.down()).getBlock();
        return soil == Blocks.GRASS || soil == Blocks.DIRT || soil == Blocks.FARMLAND;
    }

    private void generateTrunk(World world, BlockPos pos, int height) {
        for (int y = 0; y < height; y++) {
            BlockPos trunkPos = pos.up(y);
            setBlockAndNotifyAdequately(world, trunkPos, TRUNK);
        }
    }

    private void generateBranchesAndLeaves(World world, BlockPos pos, int height) {
        // Generate the branches
        generateBranch(world, pos, EnumFacing.NORTH);
        generateBranch(world, pos, EnumFacing.SOUTH);
        generateBranch(world, pos, EnumFacing.EAST);
        generateBranch(world, pos, EnumFacing.WEST);

        // Generate the leaves
        generateLeafLayer(world, pos.north());
        generateLeafLayer(world, pos.south());
        generateLeafLayer(world, pos.east());
        generateLeafLayer(world, pos.west());
        generateLeafLayer(world, pos.up());

        // Generate the top of the tree
        //setBlockAndNotifyAdequately(world, pos.up(height), LEAF);
    }

    private void generateBranch(World world, BlockPos pos, EnumFacing direction) {
        int branchLength = 2;
        for (int i = 1; i <= branchLength; i++) {
            BlockPos branchPos = pos.offset(direction, i);
            setBlockAndNotifyAdequately(world, branchPos, TRUNK);
        }
    }

    private void generateLeafLayer(World world, BlockPos pos) {
        setBlockAndNotifyAdequately(world, pos, LEAF);
        setBlockAndNotifyAdequately(world, pos.up(), LEAF);
        setBlockAndNotifyAdequately(world, pos.north(), LEAF);
        setBlockAndNotifyAdequately(world, pos.south(), LEAF);
        setBlockAndNotifyAdequately(world, pos.west(), LEAF);
        setBlockAndNotifyAdequately(world, pos.east(), LEAF);
    }
}
