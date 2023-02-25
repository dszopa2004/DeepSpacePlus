package com.snoops35.deepspaceplus.init;

import com.snoops35.deepspaceplus.items.ItemBase;
import com.snoops35.deepspaceplus.items.StimulantItem;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item SYRINGE_TOP = new ItemBase("syringe_top");
    public static final Item SYRINGE_NEEDLE = new ItemBase("syringe_needle");
    public static final Item EMPTY_SYRINGE = new ItemBase("empty_syringe");
    public static final Item STIMULANT = new StimulantItem("stimulant");

    public static final Item GELID_AMBROSIA = new ItemBase("gelid_ambrosia");
}
