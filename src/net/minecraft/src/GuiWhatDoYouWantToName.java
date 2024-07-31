package net.minecraft.src;

import java.util.function.Consumer;

public class GuiWhatDoYouWantToName extends GuiScreen {

	private final Consumer<String> cont;
	private final String defaultName;
	private final String title;
	private GuiTextField nameField;
	
	public GuiWhatDoYouWantToName(String defaultName, Consumer<String> cont) {
		this.defaultName = defaultName;
		this.cont = cont;
		this.title = StringTranslate.getInstance().translateKey("What do you want to name this world?");
	}
	
	public void initGui() {
		nameField = new GuiTextField(this, fontRenderer, width / 2 - 100, height / 3, 200, 20, defaultName);
		controlList.add(new GuiButton(0, (width - 200) / 2, height / 3 + 35, StringTranslate.getInstance().translateKey("gui.done")));
	}
	
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, title, width / 2, height / 4, 0xFFFFFF);
		nameField.drawTextBox();
		super.drawScreen(i, j, f);
	}
	
	public void actionPerformed(GuiButton bnt) {
		if(bnt.id == 0) {
			String s = nameField.getText();
			if (MathHelper.stringNullOrLengthZero(s)) {
				s = defaultName;
			}
			cont.accept(s);
		}
	}

	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		nameField.textboxKeyTyped(c, i);
	}
	
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		nameField.mouseClicked(i, j, k);
	}
	
}