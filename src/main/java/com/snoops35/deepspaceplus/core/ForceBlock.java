package com.snoops35.deepspaceplus.core;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.init.PotionInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ForceBlock extends Block implements IHasModel
{
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

    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        world.scheduleUpdate(pos, this, tickRate(world));
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        for (EntityPlayer player : world.playerEntities)
        {
            if (player.getDistanceSq(pos) <= 2500) // 50 blocks
            {
                if(!player.isPotionActive(PotionInit.AEGIS_AURA))
                {
                    player.addPotionEffect(new PotionEffect(PotionInit.NULL_FIELD_EFFECT, 200, 0));
                }
            }
        }
        world.scheduleUpdate(pos, this, tickRate(world));
    }

    @Override
    public void registerModels() { DeepSpacePlus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); }
}
