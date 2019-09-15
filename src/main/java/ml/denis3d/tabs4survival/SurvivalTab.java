package ml.denis3d.tabs4survival;


import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SurvivalTab extends AbstractButton implements IForgeRegistryEntry<SurvivalTab> {

    private final int guiXSize;
    private final int guiYSize;
    private ItemStack renderStack;
    private ResourceLocation registryName = null;
    ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
    protected ItemRenderer itemRenderer;

    protected static ArrayList<SurvivalTab> TABS = new ArrayList<SurvivalTab>();


    public SurvivalTab(int guiXSize, int guiYSize) {
        super(0, 0, 28, 25, "");
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
        this.guiXSize = guiXSize;
        this.guiYSize = guiYSize;
    }

    @Override
    public void onPress()
    {
        if (this.getGui().isInstance(Minecraft.getInstance().currentScreen))
            return;
        try {
            Minecraft.getInstance().displayGuiScreen(this.getGui().newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(double p_onClick_1_, double p_onClick_3_) {
        if (this.getGui().isInstance(Minecraft.getInstance().currentScreen))
            return;
        super.onClick(p_onClick_1_, p_onClick_3_);
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_)
    {
        if (TABS.indexOf(this) >= 6)
        {
            return;
        }
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
        this.x = (Minecraft.getInstance().currentScreen.width - guiXSize) / 2 + TABS.indexOf(this) * 29;
        this.y = (Minecraft.getInstance().currentScreen.height - guiYSize) / 2 - 25;

        if (this.visible)
        {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);


            Minecraft.getInstance().getTextureManager().bindTexture(this.texture);
            this.blit(this.x,
                    this.isFocused() ? this.y : this.y - 3,
                    TABS.indexOf(this) * 28,
                    this.isFocused() ? 2 : 32,
                    28,
                    this.isFocused() ? 25 : 32);

            RenderHelper.enableGUIStandardItemLighting();
            this.itemRenderer.zLevel = 100.0F;
            GlStateManager.enableLighting();
            GlStateManager.enableRescaleNormal();
            this.itemRenderer.renderItemAndEffectIntoGUI(this.renderStack, this.x + 6, this.y + 8);
            this.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getInstance().fontRenderer, this.renderStack, this.x + 6, this.y + 8, null);
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            this.itemRenderer.zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();

        }
    }

    public Class<? extends Screen> getGui()
    {
        return null;
    }

    public void setRenderStack(ItemStack renderStack) {
        this.renderStack = renderStack;
    }

    //Registry

    public SurvivalTab setRegistryName(String name) {
        if (getRegistryName() != null)
            throw new IllegalStateException("Attempted to set registry name with existing registry name! New: " + name + " Old: " + getRegistryName());

        this.registryName = GameData.checkPrefix(name, true);
        return this;
    }

    public final SurvivalTab setRegistryName(ResourceLocation name){ return setRegistryName(name.toString()); }
    public final SurvivalTab setRegistryName(String modID, String name){ return setRegistryName(modID + ":" + name); }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    @Override
    public Class getRegistryType() {
        return this.getClass();
    }

    //Registry End
}
