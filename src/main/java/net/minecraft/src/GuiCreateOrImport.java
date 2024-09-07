package net.minecraft.src;

import java.util.function.Consumer;

import org.lwjgl.opengl.GL11;

import net.PeytonPlayz585.GuiSomethingFailed;
import net.PeytonPlayz585.ImportExport;
import net.PeytonPlayz585.fileutils.FileChooserResult;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiCreateWorld;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.PlayerControllerSP;
import net.minecraft.src.StringTranslate;

public class GuiCreateOrImport extends GuiScreen {
	
	private final GuiScreen parent;
	private String title;
	
	public GuiCreateOrImport(GuiScreen parent) {
		this.parent = parent;
		this.title = StringTranslate.getInstance().translateKey("What do you want to do?");
	}

	public void initGui() {
		StringTranslate st = StringTranslate.getInstance();
		controlList.add(new GuiButton(0, (width - 200) / 2, height / 3 + 5, st.translateKey("selectWorld.create")));
		controlList.add(new GuiButton(1, (width - 200) / 2, height / 3 + 29, st.translateKey("Import Existing World")));
		controlList.add(new GuiButton(2, (width - 200) / 2, height / 3 + 53, st.translateKey("gui.cancel")));
	}
	
	protected void actionPerformed(GuiButton guibutton) {
		if(guibutton.id == 0) {
			mc.displayGuiScreen(new GuiCreateWorld(parent));
		}else if(guibutton.id == 1) {
			GL11.EaglerAdapterImpl2.displayFileChooser("epk", "application/epk");
//			final String folder = ImportExport.importWorld(mc.loadingScreen);
//			if(folder == null) {
//				mc.displayGuiScreen(new GuiSomethingFailed(parent, "Import Failed", "the world is incompatible or corrupt", "maybe use an EPK decompiler to debug"));
//			}else if(folder.equals("$cancelled$")) {
//				mc.displayGuiScreen(parent);
//			}else {
//				mc.displayGuiScreen(new GuiWhatDoYouWantToName(folder, new Consumer<String>() {
//					@Override
//					public void accept(String str) {
//						ImportExport.renameImportedWorld(folder, str);
//						mc.playerController = new PlayerControllerSP(mc);
//						mc.startWorld(folder, str, 0l);
//						mc.displayGuiScreen(null);
//					}
//				}));
//			}
		}else if(guibutton.id == 2) {
			mc.displayGuiScreen(parent);
		}
	}
	
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, title, width / 2, height / 4, 0xffffff);
		super.drawScreen(i, j, f);
		
		if(GL11.EaglerAdapterImpl2.fileChooserHasResult()) {
			FileChooserResult result = GL11.EaglerAdapterImpl2.getFileChooserResult();
			final String folder = ImportExport.importWorld(mc.loadingScreen, result.fileData, result.fileName);
			if(folder == null) {
				mc.displayGuiScreen(new GuiSomethingFailed(parent, "Import Failed", "the world is incompatible or corrupt", "maybe use an EPK decompiler to debug"));
			}else if(folder.equals("$cancelled$")) {
				mc.displayGuiScreen(parent);
			}else {
				mc.displayGuiScreen(new GuiWhatDoYouWantToName(folder, new Consumer<String>() {
					@Override
					public void accept(String str) {
						ImportExport.renameImportedWorld(folder, str);
						mc.playerController = new PlayerControllerSP(mc);
						mc.startWorld(folder, str, 0l);
						mc.displayGuiScreen(null);
					}
				}));
			}
		}
	}
}