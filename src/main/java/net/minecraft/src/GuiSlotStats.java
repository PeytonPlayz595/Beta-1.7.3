package net.minecraft.src;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.input.Mouse;

abstract class GuiSlotStats extends GuiSlot {
	protected int field_27268_b;
	protected List field_27273_c;
	protected Comparator field_27272_d;
	protected int field_27271_e;
	protected int field_27270_f;
	final GuiStats field_27269_g;

	protected GuiSlotStats(GuiStats var1) {
		super(GuiStats.func_27143_f(var1), var1.width, var1.height, 32, var1.height - 64, 20);
		this.field_27269_g = var1;
		this.field_27268_b = -1;
		this.field_27271_e = -1;
		this.field_27270_f = 0;
		this.func_27258_a(false);
		this.func_27259_a(true, 20);
	}

	protected void elementClicked(int var1, boolean var2) {
	}

	protected boolean isSelected(int var1) {
		return false;
	}

	protected void drawBackground() {
		this.field_27269_g.drawDefaultBackground();
	}

	protected void func_27260_a(int var1, int var2, Tessellator var3) {
		if(!Mouse.isButtonDown(0)) {
			this.field_27268_b = -1;
		}

		if(this.field_27268_b == 0) {
			GuiStats.func_27128_a(this.field_27269_g, var1 + 115 - 18, var2 + 1, 0, 0);
		} else {
			GuiStats.func_27128_a(this.field_27269_g, var1 + 115 - 18, var2 + 1, 0, 18);
		}

		if(this.field_27268_b == 1) {
			GuiStats.func_27128_a(this.field_27269_g, var1 + 165 - 18, var2 + 1, 0, 0);
		} else {
			GuiStats.func_27128_a(this.field_27269_g, var1 + 165 - 18, var2 + 1, 0, 18);
		}

		if(this.field_27268_b == 2) {
			GuiStats.func_27128_a(this.field_27269_g, var1 + 215 - 18, var2 + 1, 0, 0);
		} else {
			GuiStats.func_27128_a(this.field_27269_g, var1 + 215 - 18, var2 + 1, 0, 18);
		}

		if(this.field_27271_e != -1) {
			short var4 = 79;
			byte var5 = 18;
			if(this.field_27271_e == 1) {
				var4 = 129;
			} else if(this.field_27271_e == 2) {
				var4 = 179;
			}

			if(this.field_27270_f == 1) {
				var5 = 36;
			}

			GuiStats.func_27128_a(this.field_27269_g, var1 + var4, var2 + 1, var5, 0);
		}

	}

	protected void func_27255_a(int var1, int var2) {
		this.field_27268_b = -1;
		if(var1 >= 79 && var1 < 115) {
			this.field_27268_b = 0;
		} else if(var1 >= 129 && var1 < 165) {
			this.field_27268_b = 1;
		} else if(var1 >= 179 && var1 < 215) {
			this.field_27268_b = 2;
		}

		if(this.field_27268_b >= 0) {
			this.func_27266_c(this.field_27268_b);
			GuiStats.func_27149_g(this.field_27269_g).sndManager.playSoundFX("random.click", 1.0F, 1.0F);
		}

	}

	protected final int getSize() {
		return this.field_27273_c.size();
	}

	protected final StatCrafting func_27264_b(int var1) {
		return (StatCrafting)this.field_27273_c.get(var1);
	}

	protected abstract String func_27263_a(int var1);

	protected void func_27265_a(StatCrafting var1, int var2, int var3, boolean var4) {
		String var5;
		if(var1 != null) {
			var5 = var1.func_27084_a(GuiStats.func_27142_c(this.field_27269_g).writeStat(var1));
			this.field_27269_g.drawString(GuiStats.func_27133_h(this.field_27269_g), var5, var2 - GuiStats.func_27137_i(this.field_27269_g).getStringWidth(var5), var3 + 5, var4 ? 16777215 : 9474192);
		} else {
			var5 = "-";
			this.field_27269_g.drawString(GuiStats.func_27132_j(this.field_27269_g), var5, var2 - GuiStats.func_27134_k(this.field_27269_g).getStringWidth(var5), var3 + 5, var4 ? 16777215 : 9474192);
		}

	}

	protected void func_27257_b(int var1, int var2) {
		if(var2 >= this.top && var2 <= this.bottom) {
			int var3 = this.func_27256_c(var1, var2);
			int var4 = this.field_27269_g.width / 2 - 92 - 16;
			if(var3 >= 0) {
				if(var1 < var4 + 40 || var1 > var4 + 40 + 20) {
					return;
				}

				StatCrafting var9 = this.func_27264_b(var3);
				this.func_27267_a(var9, var1, var2);
			} else {
				String var5 = "";
				if(var1 >= var4 + 115 - 18 && var1 <= var4 + 115) {
					var5 = this.func_27263_a(0);
				} else if(var1 >= var4 + 165 - 18 && var1 <= var4 + 165) {
					var5 = this.func_27263_a(1);
				} else {
					if(var1 < var4 + 215 - 18 || var1 > var4 + 215) {
						return;
					}

					var5 = this.func_27263_a(2);
				}

				var5 = ("" + StringTranslate.getInstance().translateKey(var5)).trim();
				if(var5.length() > 0) {
					int var6 = var1 + 12;
					int var7 = var2 - 12;
					int var8 = GuiStats.func_27139_l(this.field_27269_g).getStringWidth(var5);
					GuiStats.func_27129_a(this.field_27269_g, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
					GuiStats.func_27144_m(this.field_27269_g).drawStringWithShadow(var5, var6, var7, -1);
				}
			}

		}
	}

	protected void func_27267_a(StatCrafting var1, int var2, int var3) {
		if(var1 != null) {
			Item var4 = Item.itemsList[var1.func_25072_b()];
			String var5 = ("" + StringTranslate.getInstance().translateNamedKey(var4.getItemName())).trim();
			if(var5.length() > 0) {
				int var6 = var2 + 12;
				int var7 = var3 - 12;
				int var8 = GuiStats.func_27127_n(this.field_27269_g).getStringWidth(var5);
				GuiStats.func_27135_b(this.field_27269_g, var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
				GuiStats.func_27131_o(this.field_27269_g).drawStringWithShadow(var5, var6, var7, -1);
			}

		}
	}

	protected void func_27266_c(int var1) {
		if(var1 != this.field_27271_e) {
			this.field_27271_e = var1;
			this.field_27270_f = -1;
		} else if(this.field_27270_f == -1) {
			this.field_27270_f = 1;
		} else {
			this.field_27271_e = -1;
			this.field_27270_f = 0;
		}

		Collections.sort(this.field_27273_c, this.field_27272_d);
	}
}
