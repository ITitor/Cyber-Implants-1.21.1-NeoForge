package net.ititor.cyber_implants.item;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.item.item.CyberImplantItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CyberImplants.MOD_ID);


//    public static final DeferredItem<Item> CYBER_IMPLANT = ITEMS.register("cyber_implant",
//            () -> new CyberImplantItem(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
