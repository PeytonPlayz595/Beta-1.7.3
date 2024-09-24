package net.minecraft.src;

import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.textures.TextureLocation;

public class GuiDispenser extends GuiContainer {
	
	private static final TextureLocation containerTexture = new TextureLocation("/gui/trap.png");
	
	public GuiDispenser(InventoryPlayer var1, TileEntityDispenser var2) {
		super(new ContainerDispenser(var1, var2));
	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString("Dispenser", 60, 6, 4210752);
		this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float var1) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		containerTexture.bindTexture();
		int var3 = (this.width - this.xSize) / 2;
		int var4 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var3, var4, 0, 0, this.xSize, this.ySize);
	}
}
