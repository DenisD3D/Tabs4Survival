package ml.denis3d.tabs4survival;

import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ml.denis3d.tabs4survival.Tabs4Survival.MOD_ID;


@Mod(MOD_ID)
public class Tabs4Survival
{
    public static final String MOD_ID = "tabs4survival";

    private static final Logger LOGGER = LogManager.getLogger("tabs4survival");

    public Tabs4Survival()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }


    private void doClientStuff(final FMLClientSetupEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void guiPostInit (GuiScreenEvent.InitGuiEvent.Post event)
    {
        for (SurvivalTab tab : RegistryManager.ACTIVE.getRegistry(SurvivalTab.class).getValues())
        {
            if (tab.getGui().isInstance(event.getGui()))
            {
                for(SurvivalTab t : RegistryManager.ACTIVE.getRegistry(SurvivalTab.class).getValues())
                {
                    ObfuscationReflectionHelper.setPrivateValue(Widget.class, t, !t.getGui().isInstance(event.getGui()), "focused");

                    event.addWidget(t);
                }
            }
        }
    }

}
