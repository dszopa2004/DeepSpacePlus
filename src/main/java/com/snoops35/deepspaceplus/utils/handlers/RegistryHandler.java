package com.snoops35.deepspaceplus.utils.handlers;

import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.FluidInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.init.PotionInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import com.snoops35.deepspaceplus.worldgen.TreeGen;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }
    
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        RenderHandler.registerCustomMeshesAndStates();

        ModelResourceLocation grass_block_model = new ModelResourceLocation("deepspaceplus:brown_grass", "normal");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockInit.BROWN_GRASS), 0, grass_block_model);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }
        
        for(Block block : BlockInit.BLOCKS)
        {
        	if(block instanceof IHasModel)
        	{
        		((IHasModel)block).registerModels();
        	}
        }
    }
    
    public static void preInitRegistries()
    {
        FluidInit.registerFluids();
        PotionInit.registerPotions();

        TreeGen.init();
    }
    public static void initRegistries() {

    }
    public static void postInitRegistries(){}
}
