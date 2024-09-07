package net.minecraft.src;

import net.minecraft.client.Minecraft;

class ThreadConnectToServer {
	final Minecraft mc;
	final String hostName;
	final GuiConnecting connectingGui;

	ThreadConnectToServer(GuiConnecting var1, Minecraft var2, String var3) {
		this.connectingGui = var1;
		this.mc = var2;
		this.hostName = var3;
	}

	public void start() {
		try {
			GuiConnecting.setNetClientHandler(this.connectingGui, new NetClientHandler(this.mc, this.hostName));
			if(GuiConnecting.isCancelled(this.connectingGui)) {
				return;
			}

			GuiConnecting.getNetClientHandler(this.connectingGui).handleHandshake();
		} catch (Throwable var4) {
			System.out.println("yee");
			if(GuiConnecting.isCancelled(this.connectingGui)) {
				return;
			}

			this.mc.displayGuiScreen(new GuiConnectFailed("connect.failed", "disconnect.genericReason", new Object[]{var4.toString()}));
			var4.printStackTrace();
		}

	}
}