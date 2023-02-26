package com.snoops35.deepspaceplus.blocks;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class CaelumPlanks extends Block implements IHasModel, IFuelHandler
{
    public CaelumPlanks(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        setHardness(2.0f);
        setHarvestLevel("axe", 0);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        return 300;
    }

    @Override
    public void registerModels()
    {
        DeepSpacePlus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
