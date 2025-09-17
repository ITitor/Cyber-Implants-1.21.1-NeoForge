package net.ititor.cyber_implants.item.item;

import net.ititor.cyber_implants.gui.CyberImplantsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class CyberImplantItem extends Item {
    public CyberImplantItem(Properties properties) {
        super(properties);
    }

//    @Override
//    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
//        if (armorType.getIndex() == EquipmentSlot.HEAD.getIndex()){
//            return true;
//        }else {
//            return super.canEquip(stack, armorType, entity);
//        }
//    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        if (!player.isShiftKeyDown()) {
            if (level.isClientSide) {
                Minecraft.getInstance().setScreen(new CyberImplantsScreen());
            }
        }else {
            player.getTags().forEach(player::removeTag);
        }

        return super.use(level, player, usedHand);
    }
}
