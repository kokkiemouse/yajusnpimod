package net.kokkiemouse.yaju;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.entity.FabricEntityType;
import net.kokkiemouse.yaju.Blocks.IkisugiBlock;
import net.kokkiemouse.yaju.Blocks.OmaruBlock;
import net.kokkiemouse.yaju.Items.UnkoItem;
import net.kokkiemouse.yaju.Items.yajuItem;
import net.kokkiemouse.yaju.entities.IkisugiEntity;
import net.kokkiemouse.yaju.util.CustomLogger;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class YajuMod implements ModInitializer {
    public static final EntityType<Entity> ikisugiTnt =    Registry.register(
			Registry.ENTITY_TYPE, new Identifier("yajumod","yajutnt"),
            FabricEntityTypeBuilder.create(EntityCategory.MISC,IkisugiEntity::new).size(EntityDimensions.changing(0.98F,0.98F)).build());
	public static final Identifier IKISUGI_SOUND_ID = new Identifier("yajumod:yaju.ikisugi");
	public static final Identifier UNKO_SOUND_ID = new Identifier("yajumod:yaju.unko");
	public static final Identifier IKISUGI_SOUND_ID_BLOCK = new Identifier("yajumod:yaju.ikisugiblock");
	public static final Identifier NAA_SOUND_ID_BLOCK = new Identifier("yajumod:yaju.naabreak");
	public static final Identifier NAA_TSOUND_ID_BLOCK = new Identifier("yajumod:yaju.naa");
	public static final Identifier IKISUGINAA_SOUND_ID=new Identifier("yajumod:yaju.observer");
	public static final Identifier IKISUGINAAA_SOUND_ID=new Identifier("yajumod:yaju.ikisuginaa");
	public static SoundEvent IKISUGINAA_SOUND_EVENT=new SoundEvent(IKISUGINAA_SOUND_ID);
	public static SoundEvent IKISUGI_SOUND_EVENT = new SoundEvent(IKISUGI_SOUND_ID);
	public static SoundEvent UNKO_SOUND_EVENT=new SoundEvent(UNKO_SOUND_ID);
	public static SoundEvent NAA_SOUND_EVENT = new SoundEvent(NAA_SOUND_ID_BLOCK);
	public static SoundEvent IKISUGINAAA_SOUND_EVENT=new SoundEvent(IKISUGINAAA_SOUND_ID);
	public static SoundEvent NAA_TSOUND_EVENT = new SoundEvent(NAA_TSOUND_ID_BLOCK);
	public static SoundEvent IKISUGI_SOUND_EVENT_BLOCK = new SoundEvent(IKISUGI_SOUND_ID_BLOCK);
	public static final Item FABRIC_ITEM = new yajuItem(new Item.Settings().group(ItemGroup.MISC));
	public static final Item UNKO_ITEM = new UnkoItem(new Item.Settings().group(ItemGroup.MISC));

	public static BlockSoundGroup Ikisugi_SOUNDGROUP=new BlockSoundGroup(1.0F,1.0F,NAA_SOUND_EVENT,IKISUGI_SOUND_EVENT,IKISUGI_SOUND_EVENT_BLOCK,IKISUGI_SOUND_EVENT,IKISUGI_SOUND_EVENT);
	public static Block FABRIC_IKISUGI = new IkisugiBlock(FabricBlockSettings.of(Material.METAL).sounds(Ikisugi_SOUNDGROUP).build());
	public static Block OMARUANIKI=new OmaruBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.ANVIL).build());
	public static Logger MyLogger=		LogManager.getLogManager().getLogger("YajuMod");
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		//MyLogger.log(Level.INFO,"yaju");

		Registry.register(Registry.SOUND_EVENT,IKISUGI_SOUND_ID,IKISUGI_SOUND_EVENT);
		Registry.register(Registry.SOUND_EVENT,IKISUGINAA_SOUND_ID,IKISUGINAA_SOUND_EVENT);
		Registry.register(Registry.SOUND_EVENT,NAA_TSOUND_ID_BLOCK,NAA_TSOUND_EVENT);
		Registry.register(Registry.SOUND_EVENT,IKISUGINAAA_SOUND_ID,IKISUGINAAA_SOUND_EVENT);
		//Registry.register(Registry.ENTITY_TYPE,new Identifier("yajumod","yajutnt"),ikisugiTnt);
		Registry.register(Registry.SOUND_EVENT,UNKO_SOUND_ID,UNKO_SOUND_EVENT);
		Registry.register(Registry.ITEM, new Identifier("yajumod","ikuiku"), FABRIC_ITEM);
		Registry.register(Registry.BLOCK,new Identifier("yajumod","ikkisugi"),FABRIC_IKISUGI);
		Registry.register(Registry.ITEM,new Identifier("yajumod","ikkisugi"),new BlockItem(FABRIC_IKISUGI,new Item.Settings().group(ItemGroup.MISC)));
		Registry.register(Registry.ITEM,new Identifier("yajumod","unko"),UNKO_ITEM);
		Registry.register(Registry.BLOCK,new Identifier("yajumod","omaru"),OMARUANIKI);
		Registry.register(Registry.ITEM,new Identifier("yajumod","omaru"),new BlockItem(OMARUANIKI,new Item.Settings().group(ItemGroup.MISC)));
	}
}
