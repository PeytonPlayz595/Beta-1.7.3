package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class GuiConnecting extends GuiScreen {
	private NetClientHandler clientHandler;
	private boolean cancelled = false;
	
	private Minecraft minecraft;
	private String uri;
	private int timer = 0;
	
	public GuiConnecting(Minecraft var1, String var2) {
		System.out.println("Connecting to " + var2);
		var1.changeWorld1((World)null);
		
		this.minecraft = var1;
		this.uri = var2;
	}

	public void updateScreen() {
		/*
		 * Connects AFTER drawing screen, this fixes two issues
		 * 1. A blank background while the connection is being initialized
		 * 2. The connection failed screen not showing due to the connecting screen being rendered AFTER the connection failed
		 */
		if (timer > 2 && this.clientHandler == null) {
			(new ThreadConnectToServer(this, this.minecraft, this.uri)).start();
		}
		if(this.clientHandler != null) {
			this.clientHandler.processReadPackets();
		}
		if(timer >= 1) {
			++timer;
		}
	}

	protected void keyTyped(char var1, int var2) {
	}

	public void initGui() {
		StringTranslate var1 = StringTranslate.getInstance();
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, var1.translateKey("gui.cancel")));
	}

	protected void actionPerformed(GuiButton var1) {
		if(var1.id == 0) {
			this.cancelled = true;
			if(this.clientHandler != null) {
				this.clientHandler.disconnect();
			}

			this.mc.displayGuiScreen(new GuiMainMenu());
		}

	}

	public void drawScreen(int var1, int var2, float var3) {
		if(timer == 0) {
			timer = 1;
		}
		
		this.drawDefaultBackground();
		StringTranslate var4 = StringTranslate.getInstance();
		if(this.clientHandler == null) {
			this.drawCenteredString(this.fontRenderer, var4.translateKey("connect.connecting"), this.width / 2, this.height / 2 - 50, 16777215);
			this.drawCenteredString(this.fontRenderer, "", this.width / 2, this.height / 2 - 10, 16777215);
		} else {
			this.drawCenteredString(this.fontRenderer, var4.translateKey("connect.authorizing"), this.width / 2, this.height / 2 - 50, 16777215);
			this.drawCenteredString(this.fontRenderer, this.clientHandler.field_1209_a, this.width / 2, this.height / 2 - 10, 16777215);
		}

		super.drawScreen(var1, var2, var3);
	}

	static NetClientHandler setNetClientHandler(GuiConnecting var0, NetClientHandler var1) {
		return var0.clientHandler = var1;
	}

	static boolean isCancelled(GuiConnecting var0) {
		return var0.cancelled;
	}

	static NetClientHandler getNetClientHandler(GuiConnecting var0) {
		return var0.clientHandler;
	}
}
