package net.kokkiemouse.yaju;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ModInitializer;
import net.kokkiemouse.yaju.Items.yajuItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
public class YajuMod implements ModInitializer {
	public static final Item FABRIC_ITEM = new yajuItem(new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		Registry.register(Registry.ITEM, new Identifier("yajumod","イキスギ"), FABRIC_ITEM);
	}
}
