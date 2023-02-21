package com.snoops35.deepspaceplus.worldgen;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import java.util.Random;

public class TreeGen implements IWorldGenerator
{
    private WorldGenerator customTreeGeneratorOne;
    private WorldGenerator customTreeGeneratorTwo;
    private WorldGenerator customTreeGeneratorThree;

    public TreeGen() {
        this.customTreeGeneratorOne = new WorldGenTreesCaelum(true);
        this.customTreeGeneratorTwo = new WorldGenTreesCaelumTwo(true);
        this.customTreeGeneratorThree = new WorldGenTreesCaelumThree(true);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);

        // Check the biome at the location of the chunk
        Biome biome = world.getBiome(new BlockPos(x, 0, z));

        if(biome == Biomes.PLAINS || biome == Biomes.FOREST)
        {
                if (random.nextFloat() < 0.33f)
                {
                    int y = world.getHeight(x, z);
                    customTreeGeneratorOne.generate(world, random, new BlockPos(x, y, z));
                }
                else if (random.nextFloat() < 0.66f)
                {
                    int y = world.getHeight(x, z);
                    customTreeGeneratorTwo.generate(world, random, new BlockPos(x, y, z));
                }
                else
                {
                    int y = world.getHeight(x, z);
                    customTreeGeneratorThree.generate(world, random, new BlockPos(x, y, z));
                }
        }
    }
}
