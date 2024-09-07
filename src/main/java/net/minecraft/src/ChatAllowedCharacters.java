package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;

public class ChatAllowedCharacters {
	public static final String allowedCharacters = getAllowedCharacters();
	public static final char[] allowedCharactersArray = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};

	private static String getAllowedCharacters() {
		String var0 = "";

		try {
			BufferedReader var1 = new BufferedReader(new InputStreamReader(GL11.getResourceAsStream("/font.txt"), "UTF-8"));
			String var2 = "";

			while(true) {
				var2 = var1.readLine();
				if(var2 == null) {
					var1.close();
					break;
				}

				if(!var2.startsWith("#")) {
					var0 = var0 + var2;
				}
			}
		} catch (Exception var3) {
		}

		return var0;
	}
}
