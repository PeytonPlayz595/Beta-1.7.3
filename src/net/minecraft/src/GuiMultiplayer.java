package net.minecraft.src;

import org.lwjgl.input.Keyboard;

import net.PeytonPlayz585.profile.Profile;

public class GuiMultiplayer extends GuiScreen {
	private GuiScreen parentScreen;
	private GuiTextField field_22111_h;
	private GuiTextField username;

	public GuiMultiplayer(GuiScreen var1) {
		this.parentScreen = var1;
	}

	public void updateScreen() {
		this.username.updateCursorCounter();
		this.field_22111_h.updateCursorCounter();
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, var1.translateKey("multiplayer.connect")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
		String var2 = this.mc.gameSettings.lastServer.replaceAll("_", ":");
		String var3 = Profile.getName();
		this.username = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, this.height / 4 - 10 + 25 + 18, 200, 20, var3);
		this.field_22111_h = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, this.height / 4 - 10 + 65 + 18, 200, 20, var2);
		this.username.isFocused = true;
		this.username.setMaxStringLength(16);
		this.field_22111_h.isFocused = false;
		this.field_22111_h.setMaxStringLength(128);
		
		((GuiButton)this.controlList.get(0)).enabled = this.field_22111_h.getText().length() > 0 && this.username.getText().length()  > 0;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.enabled) {
			if(var1.id == 1) {
				this.mc.displayGuiScreen(this.parentScreen);
			} else if(var1.id == 0) {
				String var2 = this.field_22111_h.getText().trim();
				String username1 = this.username.getText().trim();
				this.mc.gameSettings.lastServer = var2.replaceAll(":", "_");
				Profile.setName(username1);
				Profile.save();
				this.mc.gameSettings.saveOptions();
				
				this.mc.displayGuiScreen(new GuiConnecting(this.mc, var2));
			}

		}
	}

	private int parseIntWithDefault(String var1, int var2) {
		try {
			return Integer.parseInt(var1.trim());
		} catch (Exception var4) {
			return var2;
		}
	}

	protected void keyTyped(char var1, int var2) {
		this.field_22111_h.textboxKeyTyped(var1, var2);
		this.username.textboxKeyTyped(var1, var2);
		
		((GuiButton)this.controlList.get(0)).enabled = this.field_22111_h.getText().length() > 0 && this.username.getText().length()  > 0;
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		super.mouseClicked(var1, var2, var3);
		this.field_22111_h.mouseClicked(var1, var2, var3);
		this.username.mouseClicked(var1, var2, var3);
	}

	public void drawScreen(int var1, int var2, float var3) {
		StringTranslate var4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, var4.translateKey("multiplayer.title"), this.width / 2, this.height / 4 - 60 + 20, 16777215);
		this.drawString(this.fontRenderer, var4.translateKey("multiplayer.info1"), this.width / 2 - 140, this.height / 4 - 60 + 50 + 0, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("multiplayer.info2"), this.width / 2 - 140, this.height / 4 - 60 + 50 + 9, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("Username:"), this.width / 2 - 100, this.height / 4 - 100 + 85 + 36, 10526880);
		this.drawString(this.fontRenderer, var4.translateKey("Server IP:"), this.width / 2 - 100, this.height / 4 - 60 + 85 + 36, 10526880);
		this.field_22111_h.drawTextBox();
		this.username.drawTextBox();
		super.drawScreen(var1, var2, var3);
	}
	
	private boolean containsOnlyAZ09(String input) {
        return input.matches("[a-z0-9]+") || input.matches("[A-Z0-9]+") || input.equals(" ");
    }
}
