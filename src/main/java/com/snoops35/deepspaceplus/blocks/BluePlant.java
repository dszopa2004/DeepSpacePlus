package com.snoops35.deepspaceplus.blocks;

import com.snoops35.deepspaceplus.DeepSpacePlus;
import com.snoops35.deepspaceplus.init.BlockInit;
import com.snoops35.deepspaceplus.init.ItemInit;
import com.snoops35.deepspaceplus.utils.IHasModel;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BluePlant extends BlockBush implements IHasModel, IGrowable, net.minecraftforge.common.IShearable
{
    public static final PropertyEnum<BluePlant.EnumPlantType> VARIANT = PropertyEnum.<BluePlant.EnumPlantType>create("variant", BluePlant.EnumPlantType.class);
    public static final PropertyEnum<BluePlant.EnumBlockHalf> HALF = PropertyEnum.<BluePlant.EnumBlockHalf>create("half", BluePlant.EnumBlockHalf.class);
    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;

    public BluePlant(String name)
    {
        super(Material.PLANTS);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);

        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BluePlant.EnumBlockHalf.LOWER));
        this.setHardness(0.0F);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    private BluePlant.EnumPlantType getType(IBlockAccess blockAccess, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this)
        {
            state = state.getActualState(blockAccess, pos);
            return (BluePlant.EnumPlantType)state.getValue(VARIANT);
        }
        else
        {
            return BluePlant.EnumPlantType.BLUEPLANT;
        }
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() != this)
        {
            return true;
        }
        else
        {
            BluePlant.EnumPlantType blockblueplant$enumplanttype = (BluePlant.EnumPlantType)iblockstate.getActualState(worldIn, pos).getValue(VARIANT);
            return blockblueplant$enumplanttype == BluePlant.EnumPlantType.BLUEPLANT || blockblueplant$enumplanttype == BluePlant.EnumPlantType.BLUEPLANT;
        }
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            boolean flag = state.getValue(HALF) == BluePlant.EnumBlockHalf.UPPER;
            BlockPos blockpos = flag ? pos : pos.up();
            BlockPos blockpos1 = flag ? pos.down() : pos;
            Block block = (Block)(flag ? this : worldIn.getBlockState(blockpos).getBlock());
            Block block1 = (Block)(flag ? worldIn.getBlockState(blockpos1).getBlock() : this);

            if (!flag) this.dropBlockAsItem(worldIn, pos, state, 0); //Forge move above the setting to air.

            if (block == this)
            {
                worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            }

            if (block1 == this)
            {
                worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() != this) return super.canBlockStay(worldIn, pos, state); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        if (state.getValue(HALF) == BluePlant.EnumBlockHalf.UPPER)
        {
            return worldIn.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.up());
            return iblockstate.getBlock() == this && super.canBlockStay(worldIn, pos, iblockstate);
        }
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(HALF) == BluePlant.EnumBlockHalf.UPPER)
        {
            return Items.AIR;
        }
        else
        {
            BluePlant.EnumPlantType blockblueplant$enumplanttype = (BluePlant.EnumPlantType)state.getValue(VARIANT);
            return blockblueplant$enumplanttype == BluePlant.EnumPlantType.BLUEPLANT ? Items.AIR : (blockblueplant$enumplanttype == BluePlant.EnumPlantType.BLUEPLANT ? (rand.nextInt(8) == 0 ? Items.WHEAT_SEEDS : Items.AIR) : super.getItemDropped(state, rand, fortune));
        }
    }

    public int damageDropped(IBlockState state)
    {
        return state.getValue(HALF) != BluePlant.EnumBlockHalf.UPPER && state.getValue(VARIANT) != BluePlant.EnumPlantType.BLUEPLANT ? ((BluePlant.EnumPlantType)state.getValue(VARIANT)).getMeta() : 0;
    }

    public void placeAt(World worldIn, BlockPos lowerPos, BluePlant.EnumPlantType variant, int flags)
    {
        worldIn.setBlockState(lowerPos, this.getDefaultState().withProperty(HALF, BluePlant.EnumBlockHalf.LOWER).withProperty(VARIANT, variant), flags);
        worldIn.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(HALF, BluePlant.EnumBlockHalf.UPPER), flags);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BluePlant.EnumBlockHalf.UPPER), 2);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(HALF) == BluePlant.EnumBlockHalf.UPPER)
        {
            if (worldIn.getBlockState(pos.down()).getBlock() == this)
            {
                if (player.capabilities.isCreativeMode)
                {
                    worldIn.setBlockToAir(pos.down());
                }
                else
                {
                    IBlockState iblockstate = worldIn.getBlockState(pos.down());
                    BluePlant.EnumPlantType blockblueplant$enumplanttype = (BluePlant.EnumPlantType)iblockstate.getValue(VARIANT);

                    if (blockblueplant$enumplanttype != BluePlant.EnumPlantType.BLUEPLANT && blockblueplant$enumplanttype != BluePlant.EnumPlantType.BLUEPLANT)
                    {
                        worldIn.destroyBlock(pos.down(), true);
                    }
                    else if (worldIn.isRemote)
                    {
                        worldIn.setBlockToAir(pos.down());
                    }
                    else if (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == Items.SHEARS)
                    {
                        this.onHarvest(worldIn, pos, iblockstate, player);
                        worldIn.setBlockToAir(pos.down());
                    }
                    else
                    {
                        worldIn.destroyBlock(pos.down(), true);
                    }
                }
            }
        }
        else if (worldIn.getBlockState(pos.up()).getBlock() == this)
        {
            worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    private boolean onHarvest(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BluePlant.EnumPlantType blockblueplant$enumplanttype = (BluePlant.EnumPlantType)state.getValue(VARIANT);

        if (blockblueplant$enumplanttype != BluePlant.EnumPlantType.BLUEPLANT && blockblueplant$enumplanttype != BluePlant.EnumPlantType.BLUEPLANT)
        {
            return false;
        }
        else
        {
            player.addStat(StatList.getBlockStats(this));
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for (BluePlant.EnumPlantType blockblueplant$enumplanttype : BluePlant.EnumPlantType.values())
        {
            list.add(new ItemStack(itemIn, 1, blockblueplant$enumplanttype.getMeta()));
        }
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this, 1, this.getType(worldIn, pos, state).getMeta());
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        BluePlant.EnumPlantType blockblueplant$enumplanttype = this.getType(worldIn, pos, state);
        return blockblueplant$enumplanttype != BluePlant.EnumPlantType.BLUEPLANT && blockblueplant$enumplanttype != BluePlant.EnumPlantType.BLUEPLANT;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        spawnAsEntity(worldIn, pos, new ItemStack(this, 1, this.getType(worldIn, pos, state).getMeta()));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, BluePlant.EnumBlockHalf.UPPER) : this.getDefaultState().withProperty(HALF, BluePlant.EnumBlockHalf.LOWER).withProperty(VARIANT, BluePlant.EnumPlantType.byMetadata(meta & 7));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(HALF) == BluePlant.EnumBlockHalf.LOWER) {
            Block blockAbove = world.getBlockState(pos.up()).getBlock();
            if (blockAbove == this) {
                return state.withProperty(HALF, BluePlant.EnumBlockHalf.LOWER);
            } else {
                return state.withProperty(HALF, BluePlant.EnumBlockHalf.UPPER);
            }
        } else {
            return state;
        }
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(HALF) == BluePlant.EnumBlockHalf.UPPER ? 8 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex() : ((BluePlant.EnumPlantType)state.getValue(VARIANT)).getMeta();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HALF, VARIANT, FACING});
    }

    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        EnumPlantType type = (EnumPlantType)state.getValue(VARIANT);
        return state.getValue(HALF) == EnumBlockHalf.LOWER && (type == EnumPlantType.BLUEPLANT || type == EnumPlantType.BLUEPLANT);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        //Forge: Break both parts on the client to prevent the top part flickering as default type for a few frames.
        if (state.getBlock() ==  this && state.getValue(HALF) == EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockToAir(pos.up());
        return world.setBlockToAir(pos);
    }

    public static enum EnumBlockHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }

    public static enum EnumPlantType implements IStringSerializable
    {
        BLUEPLANT(0, "blue_plant");

        private static final BluePlant.EnumPlantType[] META_LOOKUP = new BluePlant.EnumPlantType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumPlantType(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumPlantType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMeta()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BluePlant.EnumPlantType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 1;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (BluePlant.EnumPlantType blockblueplant$enumplanttype : values())
            {
                META_LOOKUP[blockblueplant$enumplanttype.getMeta()] = blockblueplant$enumplanttype;
            }
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registerModels() {
        DeepSpacePlus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
