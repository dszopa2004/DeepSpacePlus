package com.snoops35.deepspaceplus.items;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.init.PotionInit;
import com.snoops35.deepspaceplus.utils.ClientProxy;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StimulantItem extends Item implements IHasModel
{
    public StimulantItem(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        this.setMaxDamage(3);
        setMaxStackSize(1);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        itemstack.setItemDamage(itemstack.getItemDamage() + 1);
        playerIn.addPotionEffect(new PotionEffect(PotionInit.POSITIVE_EFFECT, 1200, 0));

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
