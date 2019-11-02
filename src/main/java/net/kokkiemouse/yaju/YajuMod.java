package net.kokkiemouse.yaju;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.kokkiemouse.yaju.Blocks.IkisugiBlock;
import net.kokkiemouse.yaju.Items.yajuItem;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class YajuMod implements ModInitializer {
	public static final Item FABRIC_ITEM = new yajuItem(new Item.Settings().group(ItemGroup.MISC));

	public static Block FABRIC_IKISUGI = new IkisugiBlock(FabricBlockSettings.of(Material.ICE).sounds(BlockSoundGroup.ANVIL).build());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		Registry.register(Registry.ITEM, new Identifier("yajumod","ikuiku"), FABRIC_ITEM);
		Registry.register(Registry.BLOCK,new Identifier("yajumod","ikkisugi"),FABRIC_IKISUGI);
		Registry.register(Registry.ITEM,new Identifier("yajumod","ikkisugi"),new BlockItem(FABRIC_IKISUGI,new Item.Settings().group(ItemGroup.MISC)));

	}
}
