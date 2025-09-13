package net.ititor.cyber_implants.event;

import net.ititor.cyber_implants.CyberImplants;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = CyberImplants.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (event.getEntity().getTags().contains("cyber_1")){
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 0, false, false, false));
        }
    }
}
