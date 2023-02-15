package com.snoops35.deepspaceplus.utils;

import com.google.common.util.concurrent.ListenableFuture;
import com.snoops35.deepspaceplus.blocks.CaelumLeaves;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class CommonProxy
{
    public void registerItemRenderer(Item item, int meta, String id)
    {

    }

    public void setGraphicsLevel(CaelumLeaves caelumLeaves, boolean parFancyEnabled) {
        caelumLeaves.setGraphicsLevel(parFancyEnabled);
    }
}
