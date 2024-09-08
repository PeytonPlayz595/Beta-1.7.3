package net.PeytonPlayz585;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.lwjgl.opengl.Display;

import net.PeytonPlayz585.opengl.GL11;
import net.minecraft.client.Minecraft;

public final class GameWindowListener extends WindowAdapter {
	public void windowClosing(WindowEvent par1WindowEvent) {
		if(Minecraft.getMinecraft() != null) {
			Minecraft.getMinecraft().shutdownMinecraftApplet();
			Minecraft.getMinecraft().shutdown();
		} else {
			GL11.EaglerAdapterImpl2.exit();
		}
	}
}
