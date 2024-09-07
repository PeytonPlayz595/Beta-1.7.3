package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiStats extends GuiScreen {
	private static RenderItem field_27153_j = new RenderItem();
	protected GuiScreen field_27152_a;
	protected String field_27154_i = "Select world";
	private GuiSlotStatsGeneral field_27151_l;
	private GuiSlotStatsItem field_27150_m;
	private GuiSlotStatsBlock field_27157_n;
	private StatFileWriter field_27156_o;
	private GuiSlot field_27155_p = null;

	public GuiStats(GuiScreen var1, StatFileWriter var2) {
		this.field_27152_a = var1;
		this.field_27156_o = var2;
	}

	public void initGui() {
		this.field_27154_i = StatCollector.translateToLocal("gui.stats");
		this.field_27151_l = new GuiSlotStatsGeneral(this);
		this.field_27151_l.registerScrollButtons(this.controlList, 1, 1);
		this.field_27150_m = new GuiSlotStatsItem(this);
		this.field_27150_m.registerScrollButtons(this.controlList, 1, 1);
		this.field_27157_n = new GuiSlotStatsBlock(this);
		this.field_27157_n.registerScrollButtons(this.controlList, 1, 1);
		this.field_27155_p = this.field_27151_l;
		this.func_27130_k();
	}

	public void func_27130_k() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.controlList.add(new GuiButton(0, this.width / 2 + 4, this.height - 28, 150, 20, var1.translateKey("gui.done")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, var1.translateKey("stat.generalButton")));
		List var10000 = this.controlList;
		GuiButton var2 = new GuiButton(2, this.width / 2 - 46, this.height - 52, 100, 20, var1.translateKey("stat.blocksButton"));
		var10000.add(var2);
		var10000 = this.controlList;
		GuiButton var3 = new GuiButton(3, this.width / 2 + 62, this.height - 52, 100, 20, var1.translateKey("stat.itemsButton"));
		var10000.add(var3);
		if(this.field_27157_n.getSize() == 0) {
			var2.enabled = false;
		}

		if(this.field_27150_m.getSize() == 0) {
			var3.enabled = false;
		}

	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 0) {
				this.mc.displayGuiScreen(this.field_27152_a);
			} else if(var1.id == 1) {
				this.field_27155_p = this.field_27151_l;
			} else if(var1.id == 3) {
				this.field_27155_p = this.field_27150_m;
			} else if(var1.id == 2) {
				this.field_27155_p = this.field_27157_n;
			} else {
				this.field_27155_p.actionPerformed(var1);
			}

		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.field_27155_p.drawScreen(var1, var2, var3);
		this.drawCenteredString(this.fontRenderer, this.field_27154_i, this.width / 2, 20, 16777215);
		super.drawScreen(var1, var2, var3);
	}

	private void func_27138_c(int var1, int var2, int var3) {
		this.func_27147_a(var1 + 1, var2 + 1);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		field_27153_j.drawItemIntoGui(this.fontRenderer, this.mc.renderEngine, var3, 0, Item.itemsList[var3].getIconFromDamage(0), var1 + 2, var2 + 2);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	private void func_27147_a(int var1, int var2) {
		this.func_27136_c(var1, var2, 0, 0);
	}

	private void func_27136_c(int var1, int var2, int var3, int var4) {
		int var5 = this.mc.renderEngine.getTexture("/gui/slot.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var5);
		Tessellator var10 = Tessellator.instance;
		var10.startDrawingQuads();
		var10.addVertexWithUV((double)(var1 + 0), (double)(var2 + 18), (double)this.zLevel, (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
		var10.addVertexWithUV((double)(var1 + 18), (double)(var2 + 18), (double)this.zLevel, (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
		var10.addVertexWithUV((double)(var1 + 18), (double)(var2 + 0), (double)this.zLevel, (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
		var10.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), (double)this.zLevel, (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
		var10.draw();
	}

	static Minecraft func_27141_a(GuiStats var0) {
		return var0.mc;
	}

	static FontRenderer func_27145_b(GuiStats var0) {
		return var0.fontRenderer;
	}

	static StatFileWriter func_27142_c(GuiStats var0) {
		return var0.field_27156_o;
	}

	static FontRenderer func_27140_d(GuiStats var0) {
		return var0.fontRenderer;
	}

	static FontRenderer func_27146_e(GuiStats var0) {
		return var0.fontRenderer;
	}

	static Minecraft func_27143_f(GuiStats var0) {
		return var0.mc;
	}

	static void func_27128_a(GuiStats var0, int var1, int var2, int var3, int var4) {
		var0.func_27136_c(var1, var2, var3, var4);
	}

	static Minecraft func_27149_g(GuiStats var0) {
		return var0.mc;
	}

	static FontRenderer func_27133_h(GuiStats var0) {
		return var0.fontRenderer;
	}

	static FontRenderer func_27137_i(GuiStats var0) {
		return var0.fontRenderer;
	}

	static FontRenderer func_27132_j(GuiStats var0) {
		return var0.fontRenderer;
	}

	static FontRenderer func_27134_k(GuiStats var0) {
		return var0.fontRenderer;
	}

	static FontRenderer func_27139_l(GuiStats var0) {
		return var0.fontRenderer;
	}

	static void func_27129_a(GuiStats var0, int var1, int var2, int var3, int var4, int var5, int var6) {
		var0.drawGradientRect(var1, var2, var3, var4, var5, var6);
	}

	static FontRenderer func_27144_m(GuiStats var0) {
		return var0.fontRenderer;
	}

	static FontRenderer func_27127_n(GuiStats var0) {
		return var0.fontRenderer;
	}

	static void func_27135_b(GuiStats var0, int var1, int var2, int var3, int var4, int var5, int var6) {
		var0.drawGradientRect(var1, var2, var3, var4, var5, var6);
	}

	static FontRenderer func_27131_o(GuiStats var0) {
		return var0.fontRenderer;
	}

	static void func_27148_a(GuiStats var0, int var1, int var2, int var3) {
		var0.func_27138_c(var1, var2, var3);
	}
}
