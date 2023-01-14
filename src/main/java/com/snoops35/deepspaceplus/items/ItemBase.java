package com.snoops35.deepspaceplus.items;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);

        ItemInit.ITEMS.add(this);
    }
    @Override
    public void registerModels()
    {
        DeepSpacePlus.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
