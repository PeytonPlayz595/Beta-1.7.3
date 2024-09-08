package net.minecraft.src;

import java.util.List;

import net.PeytonPlayz585.opengl.GL11;

class GuiTexturePackSlot extends GuiSlot {
	final GuiTexturePacks parentTexturePackGui;

	public GuiTexturePackSlot(GuiTexturePacks var1) {
		super(GuiTexturePacks.func_22124_a(var1), var1.width, var1.height, 32, var1.height - 55 + 4, 36);
		this.parentTexturePackGui = var1;
	}

	protected int getSize() {
		List var1 = GuiTexturePacks.func_22126_b(this.parentTexturePackGui).texturePackList.availableTexturePacks();
		return var1.size();
	}

	protected void elementClicked(int var1, boolean var2) {
		List var3 = GuiTexturePacks.func_22119_c(this.parentTexturePackGui).texturePackList.availableTexturePacks();
		GuiTexturePacks.func_22122_d(this.parentTexturePackGui).texturePackList.setTexturePack((TexturePackBase)var3.get(var1));
		GuiTexturePacks.func_22117_e(this.parentTexturePackGui).renderEngine.refreshTextures();
	}

	protected boolean isSelected(int var1) {
		List var2 = GuiTexturePacks.func_22118_f(this.parentTexturePackGui).texturePackList.availableTexturePacks();
		return GuiTexturePacks.func_22116_g(this.parentTexturePackGui).texturePackList.selectedTexturePack == var2.get(var1);
	}

	protected int getContentHeight() {
		return this.getSize() * 36;
	}

	protected void drawBackground() {
		this.parentTexturePackGui.drawDefaultBackground();
	}

	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {
		TexturePackBase var6 = (TexturePackBase)GuiTexturePacks.func_22121_h(this.parentTexturePackGui).texturePackList.availableTexturePacks().get(var1);
		var6.bindThumbnailTexture(GuiTexturePacks.func_22123_i(this.parentTexturePackGui));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		var5.startDrawingQuads();
		var5.setColorOpaque_I(16777215);
		var5.addVertexWithUV((double)var2, (double)(var3 + var4), 0.0D, 0.0D, 1.0D);
		var5.addVertexWithUV((double)(var2 + 32), (double)(var3 + var4), 0.0D, 1.0D, 1.0D);
		var5.addVertexWithUV((double)(var2 + 32), (double)var3, 0.0D, 1.0D, 0.0D);
		var5.addVertexWithUV((double)var2, (double)var3, 0.0D, 0.0D, 0.0D);
		var5.draw();
		this.parentTexturePackGui.drawString(GuiTexturePacks.func_22127_j(this.parentTexturePackGui), var6.texturePackFileName, var2 + 32 + 2, var3 + 1, 16777215);
		this.parentTexturePackGui.drawString(GuiTexturePacks.func_22120_k(this.parentTexturePackGui), var6.firstDescriptionLine, var2 + 32 + 2, var3 + 12, 8421504);
		this.parentTexturePackGui.drawString(GuiTexturePacks.func_22125_l(this.parentTexturePackGui), var6.secondDescriptionLine, var2 + 32 + 2, var3 + 12 + 10, 8421504);
	}
}
