package moonlightdetector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMoonLightDetector extends BlockDaylightDetector
{
	private IIcon blockIconSide;

	public BlockMoonLightDetector() {
		super();
		this.setBlockName("BlockMoonLightDetector");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		String textureName = "moonlightdetector:moonlight_detector";
		this.blockIcon = iconRegister.registerIcon(textureName + "_top");
		this.blockIconSide = iconRegister.registerIcon(textureName + "_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return this.blockIcon;
		}

		return this.blockIconSide;
	}

	@Override
	/**
	 * レッドストーン信号出力の処理
	 * 日照センサーはもっと難しいことをしていますが、これは最大出力or出力なしの2択です。
	 */
	public void func_149957_e(World world, int x, int y, int z)
    {
        if (!world.provider.hasNoSky)
        {
        	int metadata = world.getBlockMetadata(x, y, z);
        	int ro;		// redstone output	出力の強さ
        	int time = getTime(world.getWorldTime());

        	if (time >= 185 || time < 50) {
        		ro = 15;
        	} else {
        		ro = 0;
        	}

        	if (metadata != ro) {
        		world.setBlockMetadataWithNotify(x, y, z, ro, 3);
        	}
        }
    }

	/**
	 * 現在の時刻を取得
	 * @param worldTime world.getWorldTime()の値をください。
	 * @return 時間に直したものを返します。午後6時半は185です。
	 * マインクラフトの時間の基準がなぜか午前6時なので+6000で合わせています。
	 */
	public int getTime(Long worldTime) {
		return (int) (((worldTime + 6000) % 24000) / 100);
	}

}
