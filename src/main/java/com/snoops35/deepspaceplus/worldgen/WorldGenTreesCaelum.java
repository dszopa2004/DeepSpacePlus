package com.snoops35.deepspaceplus.worldgen;

import com.snoops35.deepspaceplus.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

import java.util.Random;

public class WorldGenTreesCaelum extends WorldGenAbstractTree
{
    private IBlockState TRUNK = BlockInit.CAELUM_LOG.getDefaultState();
    private IBlockState LEAF_ONE = BlockInit.CAELUM_LEAF_ONE.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static int DIM_ID = -1101;
    private final int minTreeHeight = 7;

    public WorldGenTreesCaelum(boolean parShouldNotify)
    {
        super(parShouldNotify);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        IBlockState states = worldIn.getBlockState(position);
        Block block = states.getBlock();


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
                                            this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF_ONE);
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


    private void generateLeaves(World parWorld, BlockPos parBlockPos, int height, Random parRandom)
    {
        for (int foliageY = parBlockPos.getY() - 3 + height; foliageY <= parBlockPos.getY() + height; ++foliageY)
        {
            int foliageLayer = foliageY - (parBlockPos.getY() + height);
            int foliageLayerRadius = 1 - foliageLayer / 2;

            for (int foliageX = parBlockPos.getX() - foliageLayerRadius; foliageX <= parBlockPos.getX() + foliageLayerRadius; ++foliageX)
            {
                int foliageRelativeX = foliageX - parBlockPos.getX();

                for (int foliageZ = parBlockPos.getZ() - foliageLayerRadius; foliageZ <= parBlockPos.getZ() + foliageLayerRadius; ++foliageZ)
                {
                    int foliageRelativeZ = foliageZ - parBlockPos.getZ();

                    // Fill in layer with some randomness
                    if (Math.abs(foliageRelativeX) != foliageLayerRadius || Math.abs(foliageRelativeZ) != foliageLayerRadius || parRandom.nextInt(2) != 0 && foliageLayer != 0)
                    {
                        BlockPos blockPos = new BlockPos(foliageX, foliageY, foliageZ);
                        IBlockState state = parWorld.getBlockState(blockPos);

                        if (state.getBlock().isAir(state, parWorld, blockPos) || state.getBlock().isLeaves(state, parWorld, blockPos))
                        {
                            setBlockAndNotifyAdequately(parWorld, blockPos, LEAF_ONE);
                        }
                    }
                }
            }
        }
    }

    private void generateTrunk(World parWorld, BlockPos parBlockPos, int minHeight)
    {
        for (int height = 0; height < minHeight; ++height)
        {
            BlockPos upN = parBlockPos.up(height);
            IBlockState state = parWorld.getBlockState(upN);

            if (state.getBlock().isAir(state, parWorld, upN) || state.getBlock().isLeaves(state, parWorld, upN))
            {
                setBlockAndNotifyAdequately(parWorld, parBlockPos.up(height), TRUNK.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            }
        }
    }

    private boolean isSuitableLocation(World parWorld, BlockPos parBlockPos, int minHeight)
    {
        boolean isSuitableLocation = true;

        for (int checkY = parBlockPos.getY(); checkY <= parBlockPos.getY() + 1 + minHeight; ++checkY)
        {
            // Handle increasing space towards top of tree
            int extraSpaceNeeded = 1;
            // Handle base location
            if (checkY == parBlockPos.getY())
            {
                extraSpaceNeeded = 0;
            }
            // Handle top location
            if (checkY >= parBlockPos.getY() + 1 + minHeight - 2)
            {
                extraSpaceNeeded = 2;
            }

            BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

            for (int checkX = parBlockPos.getX() - extraSpaceNeeded; checkX <= parBlockPos.getX() + extraSpaceNeeded && isSuitableLocation; ++checkX)
            {
                for (int checkZ = parBlockPos.getZ() - extraSpaceNeeded; checkZ <= parBlockPos.getZ() + extraSpaceNeeded && isSuitableLocation; ++checkZ)
                {
                    isSuitableLocation = isReplaceable(parWorld,blockPos.setPos(checkX, checkY, checkZ));
                }
            }
        }

        return isSuitableLocation;
    }
}
