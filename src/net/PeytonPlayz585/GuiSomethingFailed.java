package net.PeytonPlayz585;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StringTranslate;

public class GuiSomethingFailed extends GuiScreen {
	
	private final String title;
	private final String[] description;
	private final GuiScreen cont;
	
	public GuiSomethingFailed(GuiScreen cont, String title, String... description) {
		this.cont = cont;
		this.title = title;
		this.description = description;
	}
	
	public void initGui() {
		controlList.add(new GuiButton(0, (width - 200) / 2, height / 4 + 32 + description.length * 10, StringTranslate.getInstance().translateKey("gui.cancel")));
	}
	
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		int h = height / 4;
		drawCenteredString(fontRenderer, title, width / 2, h, 0xffffff);
		h += 16;
		for(String s : description) {
			drawCenteredString(fontRenderer, s, width / 2, h, 0xffcccc);
			h += 10;
		}
		super.drawScreen(i, j, f);
	}
	
	public void actionPerformed(GuiButton bnt) {
		if(bnt.id == 0) {
			mc.displayGuiScreen(cont);
		}
	}
	
}