package com.snoops35.deepspaceplus.utils.handlers;

import com.snoops35.deepspaceplus.init.BlockInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler
{
    public static void registerCustomMeshesAndStates()
    {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockInit.GELID_MENRIL_BLOCK), new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("deepspaceplus:gelid_menril", "fluid");
            }
        });

        ModelLoader.setCustomStateMapper(BlockInit.GELID_MENRIL_BLOCK, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return new ModelResourceLocation("deepspaceplus:gelid_menril", "fluid");
            }
        });
    }
}
