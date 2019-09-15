package ml.denis3d.tabs4survival;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class InventoryTabVanilla extends SurvivalTab
{

    public InventoryTabVanilla() {
        super(176, 166);
        setRegistryName(new ResourceLocation(Tabs4Survival.MOD_ID, "inventory_tab_vanilla"));

        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("SkullOwner", Minecraft.getInstance().getSession().getUsername());
        ItemStack itemstack = new ItemStack(Items.PLAYER_HEAD, 1, nbt);
        setRenderStack(itemstack);
    }

    @Override
    public Class getGui() {
        return InventoryScreen.class;
    }

    @Override
    public void onPress() {
        Minecraft.getInstance().displayGuiScreen(new InventoryScreen(Minecraft.getInstance().player));
    }
}
