package com.snoops35.deepspaceplus.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class TreeGen
{
    private static final WorldGenTreesCaelum CAELUM_TREE = new WorldGenTreesCaelum(true);
    private static final WorldGenTreesCaelumTwo CAELUM_TREE_TWO = new WorldGenTreesCaelumTwo(true);
    private static final WorldGenTreesCaelumThree CAELUM_TREE_THREE = new WorldGenTreesCaelumThree(true);

    public static void init()
    {
        GameRegistry.registerWorldGenerator(new IWorldGenerator()
        {
            @Override
            public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
                int randTree;
                int DIM_ID = -1100;

                if (world.provider.getDimension() == DIM_ID)
                {
                    if (random.nextInt(9) < 10) { // adjust the probability as needed
                        int x = chunkX * 16 + random.nextInt(8);
                        int z = chunkZ * 16 + random.nextInt(8);
                        int y = world.getHeight(x, z);
                        BlockPos pos = new BlockPos(x, y, z);
                        randTree = random.nextInt(3);

                        switch (randTree)
                        {
                            case 0:
                                CAELUM_TREE.generate(world, random, pos);
                                break;
                            case 1:
                                CAELUM_TREE_TWO.generate(world, random, pos);
                                break;
                            case 2:
                                CAELUM_TREE_THREE.generate(world, random, pos);
                                break;
                        }
                    }
                }
            }
        }, 0);
    }
}





















