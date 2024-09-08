package net.minecraft.src;

import net.PeytonPlayz585.input.Keyboard;
import net.minecraft.client.Minecraft;

public class StatStringFormatKeyInv implements IStatStringFormat {
	final Minecraft mc;

	public StatStringFormatKeyInv(Minecraft var1) {
		this.mc = var1;
	}

	public String formatString(String var1) {
		return String.format(var1, new Object[]{Keyboard.getKeyName(this.mc.gameSettings.keyBindInventory.keyCode)});
	}
}
