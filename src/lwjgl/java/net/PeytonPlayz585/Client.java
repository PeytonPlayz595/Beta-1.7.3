package net.PeytonPlayz585;

import net.PeytonPlayz585.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Session;

public class Client {
	
public static void main(String[] par0ArrayOfStr) {
		GL11.EaglerAdapterImpl2.initializeContext();
		
		Minecraft mc = new Minecraft();
		mc.session = new Session("Player094", "mcpass");
		mc.run();
		
	}

}
