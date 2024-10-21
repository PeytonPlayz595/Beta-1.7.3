package net.minecraft.src;

import java.io.BufferedReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import net.PeytonPlayz585.fileutils.File;
import net.PeytonPlayz585.input.Keyboard;
import net.minecraft.client.Minecraft;

public class GameSettings {
	private static final String[] RENDER_DISTANCES = new String[]{"options.renderDistance.far", "options.renderDistance.normal", "options.renderDistance.short", "options.renderDistance.tiny"};
	private static final String[] DIFFICULTIES = new String[]{"options.difficulty.peaceful", "options.difficulty.easy", "options.difficulty.normal", "options.difficulty.hard"};
	private static final String[] GUISCALES = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
	private static final String[] LIMIT_FRAMERATES = new String[]{"performance.max", "performance.balanced", "performance.powersaver"};
	public float musicVolume = 1.0F;
	public float soundVolume = 1.0F;
	public float mouseSensitivity = 0.5F;
	public boolean invertMouse = false;
	public int renderDistance = 3;
	public boolean viewBobbing = false;
	public boolean anaglyph = false;
	public boolean advancedOpengl = false;
	public int limitFramerate = 1;
	public boolean fancyGraphics = false;
	public boolean ambientOcclusion = false;
	public boolean particles = true;
	public String skin = "Default";
	public KeyBinding keyBindForward = new KeyBinding("key.forward", 17);
	public KeyBinding keyBindLeft = new KeyBinding("key.left", 30);
	public KeyBinding keyBindBack = new KeyBinding("key.back", 31);
	public KeyBinding keyBindRight = new KeyBinding("key.right", 32);
	public KeyBinding keyBindJump = new KeyBinding("key.jump", 57);
	public KeyBinding keyBindInventory = new KeyBinding("key.inventory", 18);
	public KeyBinding keyBindDrop = new KeyBinding("key.drop", 16);
	public KeyBinding keyBindChat = new KeyBinding("key.chat", 20);
	public KeyBinding keyBindToggleFog = new KeyBinding("Function", 33);
	public KeyBinding keyBindSneak = new KeyBinding("key.sneak", 42);
	public KeyBinding keyBindZoom = new KeyBinding("Zoom", 46);
	public KeyBinding[] keyBindings = new KeyBinding[]{this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindToggleFog, this.keyBindZoom};
	protected Minecraft mc;
	private String optionsFile;
	public int difficulty = 2;
	public boolean hideGUI = false;
	public boolean thirdPersonView = false;
	public boolean showDebugInfo = false;
	public String lastServer = "";
	public boolean field_22275_C = false;
	public boolean smoothCamera = false;
	public boolean field_22273_E = false;
	public float field_22272_F = 1.0F;
	public float field_22271_G = 1.0F;
	public int guiScale = 0;

	public GameSettings(Minecraft var1, String var2) {
		this.mc = var1;
		this.optionsFile = var2 + "/" + "options.txt";
		this.loadOptions();
	}

	public GameSettings() {
	}

	public String getKeyBindingDescription(int var1) {
		StringTranslate var2 = StringTranslate.getInstance();
		return var2.translateKey(this.keyBindings[var1].keyDescription);
	}

	public String getOptionDisplayString(int var1) {
		return Keyboard.getKeyName(this.keyBindings[var1].keyCode);
	}

	public void setKeyBinding(int var1, int var2) {
		this.keyBindings[var1].keyCode = var2;
		this.saveOptions();
	}

	public void setOptionFloatValue(EnumOptions var1, float var2) {
		if(var1 == EnumOptions.MUSIC) {
			this.musicVolume = var2;
			this.mc.sndManager.onSoundOptionsChanged();
		}

		if(var1 == EnumOptions.SOUND) {
			this.soundVolume = var2;
			this.mc.sndManager.onSoundOptionsChanged();
		}

		if(var1 == EnumOptions.SENSITIVITY) {
			this.mouseSensitivity = var2;
		}

	}

	public void setOptionValue(EnumOptions var1, int var2) {
		if(var1 == EnumOptions.INVERT_MOUSE) {
			this.invertMouse = !this.invertMouse;
		}

		if(var1 == EnumOptions.RENDER_DISTANCE) {
			this.renderDistance = this.renderDistance + var2 & 3;
		}

		if(var1 == EnumOptions.GUI_SCALE) {
			this.guiScale = this.guiScale + var2 & 3;
		}

		if(var1 == EnumOptions.VIEW_BOBBING) {
			this.viewBobbing = !this.viewBobbing;
		}

		if(var1 == EnumOptions.ADVANCED_OPENGL) {
			this.advancedOpengl = !this.advancedOpengl;
			this.mc.renderGlobal.loadRenderers();
		}

		if(var1 == EnumOptions.ANAGLYPH) {
			this.anaglyph = !this.anaglyph;
			this.mc.renderEngine.refreshTextures();
		}

		if(var1 == EnumOptions.FRAMERATE_LIMIT) {
			this.limitFramerate = (this.limitFramerate + var2 + 3) % 3;
		}

		if(var1 == EnumOptions.DIFFICULTY) {
			this.difficulty = this.difficulty + var2 & 3;
		}

		if(var1 == EnumOptions.GRAPHICS) {
			this.fancyGraphics = !this.fancyGraphics;
			this.mc.renderGlobal.loadRenderers();
		}

		if(var1 == EnumOptions.AMBIENT_OCCLUSION) {
			this.ambientOcclusion = !this.ambientOcclusion;
			this.mc.renderGlobal.loadRenderers();
		}
		
		if(var1 == EnumOptions.PARTICLES) {
			this.particles = !this.particles;
		}

		this.saveOptions();
	}

	public float getOptionFloatValue(EnumOptions var1) {
		return var1 == EnumOptions.MUSIC ? this.musicVolume : (var1 == EnumOptions.SOUND ? this.soundVolume : (var1 == EnumOptions.SENSITIVITY ? this.mouseSensitivity : 0.0F));
	}

	public boolean getOptionOrdinalValue(EnumOptions var1) {
		switch(EnumOptionsMappingHelper.enumOptionsMappingHelperArray[var1.ordinal()]) {
		case 1:
			return this.invertMouse;
		case 2:
			return this.viewBobbing;
		case 3:
			return this.anaglyph;
		case 4:
			return this.advancedOpengl;
		case 5:
			return this.ambientOcclusion;
		case 6:
			return this.particles;
		default:
			return false;
		}
	}

	public String getKeyBinding(EnumOptions var1) {
		StringTranslate var2 = StringTranslate.getInstance();
		String var3 = var2.translateKey(var1.getEnumString()) + ": ";
		if(var1.getEnumFloat()) {
			float var5 = this.getOptionFloatValue(var1);
			return var1 == EnumOptions.SENSITIVITY ? (var5 == 0.0F ? var3 + var2.translateKey("options.sensitivity.min") : (var5 == 1.0F ? var3 + var2.translateKey("options.sensitivity.max") : var3 + (int)(var5 * 200.0F) + "%")) : (var5 == 0.0F ? var3 + var2.translateKey("options.off") : var3 + (int)(var5 * 100.0F) + "%");
		} else if(var1.getEnumBoolean()) {
			boolean var4 = this.getOptionOrdinalValue(var1);
			return var4 ? var3 + var2.translateKey("options.on") : var3 + var2.translateKey("options.off");
		} else {
			return var1 == EnumOptions.RENDER_DISTANCE ? var3 + var2.translateKey(RENDER_DISTANCES[this.renderDistance]) : (var1 == EnumOptions.DIFFICULTY ? var3 + var2.translateKey(DIFFICULTIES[this.difficulty]) : (var1 == EnumOptions.GUI_SCALE ? var3 + var2.translateKey(GUISCALES[this.guiScale]) : (var1 == EnumOptions.FRAMERATE_LIMIT ? var3 + StatCollector.translateToLocal(LIMIT_FRAMERATES[this.limitFramerate]) : (var1 == EnumOptions.GRAPHICS ? (this.fancyGraphics ? var3 + var2.translateKey("options.graphics.fancy") : var3 + var2.translateKey("options.graphics.fast")) : var3))));
		}
	}

	public void loadOptions() {
		try {
			if(!File.exists(this.optionsFile)) {
				return;
			}

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(File.readFile(this.optionsFile));
			InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream, "UTF-8");
			BufferedReader var1 = new BufferedReader(inputStreamReader);
			String var2 = "";

			while(true) {
				var2 = var1.readLine();
				if(var2 == null) {
					var1.close();
					break;
				}

				try {
					String[] var3 = var2.split(":");
					if(var3[0].equals("music")) {
						this.musicVolume = this.parseFloat(var3[1]);
					}

					if(var3[0].equals("sound")) {
						this.soundVolume = this.parseFloat(var3[1]);
					}

					if(var3[0].equals("mouseSensitivity")) {
						this.mouseSensitivity = this.parseFloat(var3[1]);
					}

					if(var3[0].equals("invertYMouse")) {
						this.invertMouse = var3[1].equals("true");
					}

					if(var3[0].equals("viewDistance")) {
						this.renderDistance = Integer.parseInt(var3[1]);
					}

					if(var3[0].equals("guiScale")) {
						this.guiScale = Integer.parseInt(var3[1]);
					}

					if(var3[0].equals("bobView")) {
						this.viewBobbing = var3[1].equals("true");
					}

					if(var3[0].equals("anaglyph3d")) {
						this.anaglyph = var3[1].equals("true");
					}

					if(var3[0].equals("advancedOpengl")) {
						this.advancedOpengl = var3[1].equals("true");
					}

					if(var3[0].equals("fpsLimit")) {
						this.limitFramerate = Integer.parseInt(var3[1]);
					}

					if(var3[0].equals("difficulty")) {
						this.difficulty = Integer.parseInt(var3[1]);
					}

					if(var3[0].equals("fancyGraphics")) {
						this.fancyGraphics = var3[1].equals("true");
					}

					if(var3[0].equals("ao")) {
						this.ambientOcclusion = var3[1].equals("true");
					}

					if(var3[0].equals("skin")) {
						this.skin = var3[1];
					}

					if(var3[0].equals("lastServer") && var3.length >= 2) {
						this.lastServer = var3[1];
					}
					
					if(var3[0].equals("particles")) {
						this.particles = var3[1].equals("true");
					}

					for(int var4 = 0; var4 < this.keyBindings.length; ++var4) {
						if(var3[0].equals("key_" + this.keyBindings[var4].keyDescription)) {
							this.keyBindings[var4].keyCode = Integer.parseInt(var3[1]);
						}
					}
				} catch (Exception var5) {
					System.out.println("Skipping bad option: " + var2);
				}
			}
		} catch (Exception var6) {
			System.out.println("Failed to load options");
			var6.printStackTrace();
		}

	}

	private float parseFloat(String var1) {
		return var1.equals("true") ? 1.0F : (var1.equals("false") ? 0.0F : Float.parseFloat(var1));
	}

	public void saveOptions() {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			PrintWriter var1 = new PrintWriter(byteArrayOutputStream);
			var1.println("music:" + this.musicVolume);
			var1.println("sound:" + this.soundVolume);
			var1.println("invertYMouse:" + this.invertMouse);
			var1.println("mouseSensitivity:" + this.mouseSensitivity);
			var1.println("viewDistance:" + this.renderDistance);
			var1.println("guiScale:" + this.guiScale);
			var1.println("bobView:" + this.viewBobbing);
			var1.println("anaglyph3d:" + this.anaglyph);
			var1.println("advancedOpengl:" + this.advancedOpengl);
			var1.println("fpsLimit:" + this.limitFramerate);
			var1.println("difficulty:" + this.difficulty);
			var1.println("fancyGraphics:" + this.fancyGraphics);
			var1.println("ao:" + this.ambientOcclusion);
			var1.println("skin:" + this.skin);
			var1.println("lastServer:" + this.lastServer);
			var1.println("particles:" + this.particles);

			for(int var2 = 0; var2 < this.keyBindings.length; ++var2) {
				var1.println("key_" + this.keyBindings[var2].keyDescription + ":" + this.keyBindings[var2].keyCode);
			}
			
			var1.flush();
			byte[] data = byteArrayOutputStream.toByteArray();
			File.writeFile(this.optionsFile, data);

			var1.close();
		} catch (Exception var3) {
			System.out.println("Failed to save options");
			var3.printStackTrace();
		}

	}
}
