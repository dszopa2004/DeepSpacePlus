package com.snoops35.deepspaceplus.items;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.init.PotionInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class StimulantItem extends Item implements IHasModel
{
    public StimulantItem(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        setMaxStackSize(1);
        this.setMaxDamage(3);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        itemstack.setItemDamage(itemstack.getItemDamage() + 1);
        playerIn.addPotionEffect(new PotionEffect(PotionInit.AEGIS_AURA, 1200, 0));

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        EntityPlayer player = (EntityPlayer)entityIn;

        if (stack.getItemDamage() >= stack.getMaxDamage())
            player.inventory.removeStackFromSlot(itemSlot);
    }

    @Override
    public void registerModels()
    {
        DeepSpacePlus.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
