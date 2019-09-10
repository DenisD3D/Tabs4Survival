package ml.denis3d.tabs4survival;

import net.minecraft.crash.CrashReport;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Tabs4Survival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {
    @SubscribeEvent
    public static void registryCreationEvent(net.minecraftforge.event.RegistryEvent.NewRegistry event) {
        RegistryBuilder<SurvivalTab> builder = new RegistryBuilder<SurvivalTab>();
        builder.setType(SurvivalTab.class);
        builder.setName(new ResourceLocation(Tabs4Survival.MOD_ID, "survival_tab"));
        builder.create();
    }

    @SubscribeEvent
    public static void registerTabs(RegistryEvent.Register<SurvivalTab> event)
    {
        event.getRegistry().register(new InventoryTabVanilla());
    }
}
