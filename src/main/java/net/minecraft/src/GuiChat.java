package net.minecraft.src;

import org.lwjgl.input.Keyboard;

import net.lax1dude.eaglercraft.beta.SingleplayerCommands;

public class GuiChat extends GuiScreen {
	protected String message = "";
	private int updateCounter = 0;
	private static final String field_20082_i = ChatAllowedCharacters.allowedCharacters;

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	public void updateScreen() {
		++this.updateCounter;
	}

	protected void keyTyped(char var1, int var2) {
		if(var2 == 1) {
			this.mc.displayGuiScreen(null);
		} else if(var2 == 28) {
			String var3 = this.message.trim();
			if(var3.length() > 0) {
				String var4 = this.message.trim();
				if(!this.mc.isMultiplayerWorld() && var4.startsWith("/")) {
					SingleplayerCommands.processCommand(this.mc, var4.substring(1));
				} else {
					this.mc.thePlayer.sendChatMessage(var4);
				}
			}

			this.mc.displayGuiScreen((GuiScreen)null);
		} else if((int)var1 == 16 || (GuiScreen.isCtrlKeyDown() && var2 == 47)) {
			String string = GuiScreen.getClipboardString();
			if (string == null) {
				string = "";
			}
			this.message = this.message + string;
			if(this.message.length() > 100) {
				this.message = this.message.substring(0, 100);
			}
			return;
		} else {
			if(var2 == 14 && this.message.length() > 0) {
				this.message = this.message.substring(0, this.message.length() - 1);
			}

			if(field_20082_i.indexOf(var1) >= 0 && this.message.length() < 100) {
				this.message = this.message + var1;
			}
		}
	}

	public void drawScreen(int var1, int var2, float var3) {
		this.drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
		this.drawString(this.fontRenderer, "> " + this.message + (this.updateCounter / 6 % 2 == 0 ? "_" : ""), 4, this.height - 12, 14737632);
		super.drawScreen(var1, var2, var3);
	}

	protected void mouseClicked(int var1, int var2, int var3) {
		if(var3 == 0) {
			if(this.mc.ingameGUI.field_933_a != null) {
				if(this.message.length() > 0 && !this.message.endsWith(" ")) {
					this.message = this.message + " ";
				}

				this.message = this.message + this.mc.ingameGUI.field_933_a;
				byte var4 = 100;
				if(this.message.length() > var4) {
					this.message = this.message.substring(0, var4);
				}
			} else {
				super.mouseClicked(var1, var2, var3);
			}
		}

	}
	
	public boolean doesGuiPauseGame() {
		return false;
	}
}
