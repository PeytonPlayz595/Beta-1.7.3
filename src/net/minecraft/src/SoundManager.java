package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import net.PeytonPlayz585.opengl.GL11;

public class SoundManager {
	
	private GameSettings options;
	private static boolean loaded = false;
	private Random rand = new Random();
	private int ticksBeforeMusic = this.rand.nextInt(12000);
	
	private static List<Integer> sounds = new ArrayList<Integer>();
	private static int BgMusic = -1;
	
	static String[] music = new String[] {"music.calm1_ogg", "music.calm2_ogg", "music.calm3_ogg", "newMusic.hal1_ogg", "newMusic.hal2_ogg", "newMusic.hal3_ogg", "newMusic.hal4_ogg", "newMusic.nuance1_ogg", "newMusic.nuance2_ogg", "newMusic.piano1_ogg", "newMusic.piano2_ogg", "newMusic.piano3_ogg"};
	
	public void loadSoundSettings(GameSettings var1) {
		this.options = var1;
		loaded = true;
	}
	
	public void onSoundOptionsChanged() {
		for(int i = 0; i < sounds.size(); i++) {
			if(!GL11.isPlaying(sounds.get(i))) {
				sounds.remove(i);
			}
		}
		
		if(loaded) {
			if(this.options.musicVolume == 0.0F) {
				if(BgMusic != -1) {
					GL11.endSound(BgMusic);
				}
			} else {
				if(BgMusic != -1) {
					GL11.setVolume(BgMusic, this.options.musicVolume);
				}
			}
			
			if(this.options.soundVolume == 0.0F) {
				for(Integer i : sounds) {
					if(i != -1) {
						GL11.endSound(i);
					}
				}
			} else {
				for(Integer i : sounds) {
					if(i != -1) {
						GL11.setVolume(i, this.options.soundVolume);
					}
				}
			}
		}
	}
	
	public void closeMinecraft() {
		// Do nothing
	}
	
	public void addSound(String var1) {
		// Do nothing
	}

	public void addMusic(String var1) {
		// Do nothing
	}
	
	public void playRandomMusicIfReady() {
		for(int i = 0; i < sounds.size(); i++) {
			if(!GL11.isPlaying(sounds.get(i))) {
				sounds.remove(i);
			}
		}
		
		if(loaded && this.options.musicVolume != 0.0F) {
			if(!GL11.isPlaying(BgMusic)) {
				try {
					if(this.ticksBeforeMusic > 0) {
						--this.ticksBeforeMusic;
						return;
					}
				
					//Apparently I DO NOT know how to use random.nextInt
					int var1 = rand.nextInt(music.length);
					this.ticksBeforeMusic = this.rand.nextInt(12000) + 12000;
					BgMusic = GL11.beginPlaybackStatic(music[var1].replace(".", "/").replace("_", "."), this.options.musicVolume, 1.0F);
				} catch(Exception e) {
					BgMusic = -1;
					return;
				}
			}
		}
	}
	
	public void func_338_a(EntityLiving var1, float var2) {
		for(int i = 0; i < sounds.size(); i++) {
			if(!GL11.isPlaying(sounds.get(i))) {
				sounds.remove(i);
			}
		}
		
		if(loaded && this.options.soundVolume != 0.0F) {
			if(var1 != null) {
				float var9 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
				float var3 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2;
	            double var4 = var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var2;
			    double var6 = var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var2;
			    double var8 = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var2;
			    try {
			    	GL11.setListenerPos((float)var4, (float)var6, (float)var8, (float)var1.motionX, (float)var1.motionY, (float)var1.motionZ, (float)var9, (float)var3);
			    } catch(Exception e) {
			    	// ???
			    }
			}
		}
	}
	
	public void playSound(String var1, float var2, float var3, float var4, float var5, float var6) {
		playSound(var1, var2, var3, var4, var5, var6, rand.nextInt((4 - 1) + 1) + 1);
	}
	
	public void playSound(String var1, float var2, float var3, float var4, float var5, float var6, int number) {
		for(int i = 0; i < sounds.size(); i++) {
			if(!GL11.isPlaying(sounds.get(i))) {
				sounds.remove(i);
			}
		}
		
		if(loaded && this.options.soundVolume != 0.0F) {
			if(var1 == null) {
				return;
			}
			
			String var7;
			var7 = "sounds/" + var1.replace(".", "/") + number + ".ogg";
			if(var7 != null && var5 > 0.0F) {
				if(var5 > 1.0F) {
					var5 = 1.0F;
				}

				int i = GL11.beginPlayback(var7.replace("0", ""), var2, var3, var4, var5 * this.options.soundVolume, var6);
				if(i != -1) {
					sounds.add(i);
				} else {
					if(number != 0) {
						playSound(var1, var2, var3, var4, var5, var6, number - 1);
					} else {
						System.err.println("Unknown sound: " + var7.replace("0", ""));
					}
				}
			}
		}
	}
	
	public void playSoundFX(String var1, float var2, float var3) {
		playSoundFX(var1, var2, var3, rand.nextInt((4 - 1) + 1) + 1);
	}
	
	public void playSoundFX(String var1, float var2, float var3, int number) {
		for(int i = 0; i < sounds.size(); i++) {
			if(!GL11.isPlaying(sounds.get(i))) {
				sounds.remove(i);
			}
		}
		
		if(loaded && this.options.soundVolume != 0.0F) {
			String var4 = "sounds/" + var1.replace(".", "/") + number + ".ogg";
			if(var4 != null) {
				if(var2 > 1.0F) {
					var2 = 1.0F;
				}

				int i = GL11.beginPlaybackStatic(var4.replace("0", ""), var2 * this.options.soundVolume, var3);
				if(i != -1) {
					sounds.add(i);
				} else {
					if(number != 0) {
						playSoundFX(var1, var2, var3, number - 1);
					} else {
						System.err.println("Unknown sound: " + var4.replace("0", ""));
					}
				}
			}

		}
	}
	
	
	//Uhm...
	public void playStreaming(String string, float f, float g, float h, float i, float j) {
		// TODO Auto-generated method stub
		
	}
	
}
