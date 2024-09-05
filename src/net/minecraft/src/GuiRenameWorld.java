package net.minecraft.src;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

public class GuiRenameWorld extends GuiScreen {
	private GuiScreen field_22112_a;
	private GuiTextField field_22114_h;
	private final String field_22113_i;

	public GuiRenameWorld(GuiScreen var1, String var2) {
		this.field_22112_a = var1;
		this.field_22113_i = var2;
	}

	public void updateScreen() {
		this.field_22114_h.updateCursorCounter();
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("selectWorld.renameButton")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
		ISaveFormat var2 = this.mc.getSaveLoader();
		WorldInfo var3 = var2.func_22173_b(this.field_22113_i);
		String var4 = var3.getWorldName();
		this.field_22114_h = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, 60, 200, 20, var4);
		this.field_22114_h.isFocused = true;
		this.field_22114_h.setMaxStringLength(32);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 1) {
				this.mc.displayGuiScreen(this.field_22112_a);
			} else if(var1.id == 0) {
				ISaveFormat var2 = this.mc.getSaveLoader();
				var2.func_22170_a(Minecraft.getMinecraft().getSaveDir() + "/" + this.field_22113_i, this.field_22114_h.getText().trim());
				this.mc.displayGuiScreen(this.field_22112_a);
			}

		}
	}

	protected void keyTyped(char var1, int var2) {
		this.field_22114_h.textboxKeyTyped(var1, var2);
		((GuiButton)this.controlList.get(0)).enabled = this.field_22114_h.getText().trim().length() > 0;
		if(var1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}

	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		this.field_22114_h.mouseClicked(var1, var2, var3);
	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.translateKey("selectWorld.renameTitle"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
		this.drawString(this.fontRenderer, var4.translateKey("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
		this.field_22114_h.drawTextBox();
		super.drawScreen(var1, var2, var3);
	}
}
