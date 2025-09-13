package net.ititor.cyber_implants.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ititor.cyber_implants.CyberImplants;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.HashMap;


@EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onEntityRender(RenderPlayerEvent.Pre event) {
//        LivingEntity player = event.getEntity();
//        var model = event.getRenderer().getModel();
//
//        if (!player.getTags().contains("cyber_1")){
//            return;
//        }

    }
}
