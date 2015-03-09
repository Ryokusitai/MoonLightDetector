package moonlightdetector;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MoonLightDetector.MODID, name = MoonLightDetector.MODNAME, version = MoonLightDetector.VERSION)
public class MoonLightDetector
{
	public static final String MODID = "MoonLightDetector";
	public static final String MODNAME = "MoonLightDetector";
	public static final String VERSION = "@VERSION@";

	public static Block moonLightDetector = new BlockMoonLightDetector();
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// ブロック登録
		GameRegistry.registerBlock(moonLightDetector, "Moon Light Detector");
		
		// レシピ登録
		//		日照センサーと月光センサーの相互変換
		GameRegistry.addShapelessRecipe(new ItemStack(moonLightDetector), new ItemStack(Blocks.daylight_detector));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.daylight_detector), new ItemStack(moonLightDetector));

	}

}
