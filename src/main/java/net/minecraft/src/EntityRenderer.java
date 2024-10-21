package net.minecraft.src;

import java.util.List;

import net.PeytonPlayz585.glemu.GameOverlayFramebuffer;
import net.PeytonPlayz585.input.Keyboard;
import net.PeytonPlayz585.input.Mouse;
import net.PeytonPlayz585.opengl.Display;
import net.PeytonPlayz585.opengl.GL11;
import net.PeytonPlayz585.util.glu.GLU;
import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.minecraft.client.Minecraft;

public class EntityRenderer {
	public static boolean field_28135_a = false;
	public static int anaglyphField;
	private Minecraft mc;
	private float farPlaneDistance = 0.0F;
	public ItemRenderer itemRenderer;
	private int rendererUpdateCount;
	private Entity pointedEntity = null;
	private MouseFilter mouseFilterXAxis = new MouseFilter();
	private MouseFilter mouseFilterYAxis = new MouseFilter();
	private MouseFilter mouseFilterDummy1 = new MouseFilter();
	private MouseFilter mouseFilterDummy2 = new MouseFilter();
	private MouseFilter mouseFilterDummy3 = new MouseFilter();
	private MouseFilter mouseFilterDummy4 = new MouseFilter();
	private float field_22228_r = 4.0F;
	private float field_22227_s = 4.0F;
	private float field_22226_t = 0.0F;
	private float field_22225_u = 0.0F;
	private float field_22224_v = 0.0F;
	private float field_22223_w = 0.0F;
	private float field_22222_x = 0.0F;
	private float field_22221_y = 0.0F;
	private float field_22220_z = 0.0F;
	private float field_22230_A = 0.0F;
	private boolean cloudFog = false;
	private double cameraZoom = 1.0D;
	private double cameraYaw = 0.0D;
	private double cameraPitch = 0.0D;
	private long prevFrameTime = System.currentTimeMillis();
	private long field_28133_I = 0L;
	private Random random = new Random();
	private int rainSoundCounter = 0;
	volatile int field_1394_b = 0;
	volatile int field_1393_c = 0;
	FloatBuffer fogColorBuffer = GLAllocation.createDirectFloatBuffer(16);
	float fogColorRed;
	float fogColorGreen;
	float fogColorBlue;
	private float fogColor2;
	private float fogColor1;
	private static boolean zoomMode = false;
	
	private GameOverlayFramebuffer overlayFramebuffer;

	public EntityRenderer(Minecraft var1) {
		this.mc = var1;
		this.overlayFramebuffer = new GameOverlayFramebuffer();
		this.itemRenderer = new ItemRenderer(var1);
	}

	public void updateRenderer() {
		this.fogColor2 = this.fogColor1;
		this.field_22227_s = this.field_22228_r;
		this.field_22225_u = this.field_22226_t;
		this.field_22223_w = this.field_22224_v;
		this.field_22221_y = this.field_22222_x;
		this.field_22230_A = this.field_22220_z;
		if(this.mc.renderViewEntity == null) {
			this.mc.renderViewEntity = this.mc.thePlayer;
		}

		float var1 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(this.mc.renderViewEntity.posX), MathHelper.floor_double(this.mc.renderViewEntity.posY), MathHelper.floor_double(this.mc.renderViewEntity.posZ));
		float var2 = (float)(3 - this.mc.gameSettings.renderDistance) / 3.0F;
		float var3 = var1 * (1.0F - var2) + var2;
		this.fogColor1 += (var3 - this.fogColor1) * 0.1F;
		++this.rendererUpdateCount;
		this.itemRenderer.updateEquippedItem();
		this.addRainParticles();
	}

	public void getMouseOver(float var1) {
		if(this.mc.renderViewEntity != null) {
			if(this.mc.theWorld != null) {
				double var2 = (double)this.mc.playerController.getBlockReachDistance();
				this.mc.objectMouseOver = this.mc.renderViewEntity.rayTrace(var2, var1);
				double var4 = var2;
				Vec3D var6 = this.mc.renderViewEntity.getPosition(var1);
				if(this.mc.objectMouseOver != null) {
					var4 = this.mc.objectMouseOver.hitVec.distanceTo(var6);
				}

				if(this.mc.playerController instanceof PlayerControllerTest) {
					var2 = 32.0D;
				} else {
					if(var4 > 3.0D) {
						var4 = 3.0D;
					}

					var2 = var4;
				}

				Vec3D var7 = this.mc.renderViewEntity.getLook(var1);
				Vec3D var8 = var6.addVector(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2);
				this.pointedEntity = null;
				float var9 = 1.0F;
				List var10 = this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(this.mc.renderViewEntity, this.mc.renderViewEntity.boundingBox.addCoord(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2).expand((double)var9, (double)var9, (double)var9));
				double var11 = 0.0D;

				for(int var13 = 0; var13 < var10.size(); ++var13) {
					Entity var14 = (Entity)var10.get(var13);
					if(var14.canBeCollidedWith()) {
						float var15 = var14.getCollisionBorderSize();
						AxisAlignedBB var16 = var14.boundingBox.expand((double)var15, (double)var15, (double)var15);
						MovingObjectPosition var17 = var16.func_1169_a(var6, var8);
						if(var16.isVecInside(var6)) {
							if(0.0D < var11 || var11 == 0.0D) {
								this.pointedEntity = var14;
								var11 = 0.0D;
							}
						} else if(var17 != null) {
							double var18 = var6.distanceTo(var17.hitVec);
							if(var18 < var11 || var11 == 0.0D) {
								this.pointedEntity = var14;
								var11 = var18;
							}
						}
					}
				}

				if(this.pointedEntity != null && !(this.mc.playerController instanceof PlayerControllerTest)) {
					this.mc.objectMouseOver = new MovingObjectPosition(this.pointedEntity);
				}

			}
		}
	}

	private float getFOVModifier(float var1) {
		EntityLiving var2 = this.mc.renderViewEntity;
		float var3 = 70.0F;
		if(var2.isInsideOfMaterial(Material.water)) {
			var3 = 60.0F;
		}
		
		boolean flag = false;
		if(this.mc.currentScreen == null) {
			flag = Keyboard.isKeyDown(this.mc.gameSettings.keyBindZoom.keyCode);
		}
		
		if(flag) {
			if(!zoomMode) {
				zoomMode = true;
				this.mc.gameSettings.smoothCamera = true;
			}
			
			if(zoomMode) {
				var3 /= 4.0F;
			}
		} else if(zoomMode) {
			zoomMode = false;
			this.mc.gameSettings.smoothCamera = false;
			this.mouseFilterXAxis = new MouseFilter();
			this.mouseFilterYAxis = new MouseFilter();
		}
		
		if(var2.health <= 0) {
			float var4 = (float)var2.deathTime + var1;
			var3 /= (1.0F - 500.0F / (var4 + 500.0F)) * 2.0F + 1.0F;
		}

		return var3 + this.field_22221_y + (this.field_22222_x - this.field_22221_y) * var1;
	}

	private void hurtCameraEffect(float var1) {
		EntityLiving var2 = this.mc.renderViewEntity;
		float var3 = (float)var2.hurtTime - var1;
		float var4;
		if(var2.health <= 0) {
			var4 = (float)var2.deathTime + var1;
			GL11.glRotatef(40.0F - 8000.0F / (var4 + 200.0F), 0.0F, 0.0F, 1.0F);
		}

		if(var3 >= 0.0F) {
			var3 /= (float)var2.maxHurtTime;
			var3 = MathHelper.sin(var3 * var3 * var3 * var3 * (float)Math.PI);
			var4 = var2.attackedAtYaw;
			GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
		}
	}

	private void setupViewBobbing(float var1) {
		if(this.mc.renderViewEntity instanceof EntityPlayer) {
			EntityPlayer var2 = (EntityPlayer)this.mc.renderViewEntity;
			float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
			float var4 = -(var2.distanceWalkedModified + var3 * var1);
			float var5 = var2.field_775_e + (var2.field_774_f - var2.field_775_e) * var1;
			float var6 = var2.cameraPitch + (var2.field_9328_R - var2.cameraPitch) * var1;
			GL11.glTranslatef(MathHelper.sin(var4 * (float)Math.PI) * var5 * 0.5F, -Math.abs(MathHelper.cos(var4 * (float)Math.PI) * var5), 0.0F);
			GL11.glRotatef(MathHelper.sin(var4 * (float)Math.PI) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(Math.abs(MathHelper.cos(var4 * (float)Math.PI - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
		}
	}

	private void orientCamera(float var1) {
		EntityLiving var2 = this.mc.renderViewEntity;
		float var3 = var2.yOffset - 1.62F;
		double var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * (double)var1;
		double var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * (double)var1 - (double)var3;
		double var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * (double)var1;
		GL11.glRotatef(this.field_22230_A + (this.field_22220_z - this.field_22230_A) * var1, 0.0F, 0.0F, 1.0F);
		if(var2.isPlayerSleeping()) {
			var3 = (float)((double)var3 + 1.0D);
			GL11.glTranslatef(0.0F, 0.3F, 0.0F);
			if(!this.mc.gameSettings.field_22273_E) {
				int var10 = this.mc.theWorld.getBlockId(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
				if(var10 == Block.blockBed.blockID) {
					int var11 = this.mc.theWorld.getBlockMetadata(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
					int var12 = var11 & 3;
					GL11.glRotatef((float)(var12 * 90), 0.0F, 1.0F, 0.0F);
				}

				GL11.glRotatef(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * var1 + 180.0F, 0.0F, -1.0F, 0.0F);
				GL11.glRotatef(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * var1, -1.0F, 0.0F, 0.0F);
			}
		} else if(this.mc.gameSettings.thirdPersonView) {
			double var27 = (double)(this.field_22227_s + (this.field_22228_r - this.field_22227_s) * var1);
			float var13;
			float var28;
			if(this.mc.gameSettings.field_22273_E) {
				var28 = this.field_22225_u + (this.field_22226_t - this.field_22225_u) * var1;
				var13 = this.field_22223_w + (this.field_22224_v - this.field_22223_w) * var1;
				GL11.glTranslatef(0.0F, 0.0F, (float)(-var27));
				GL11.glRotatef(var13, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var28, 0.0F, 1.0F, 0.0F);
			} else {
				var28 = var2.rotationYaw;
				var13 = var2.rotationPitch;
				double var14 = (double)(-MathHelper.sin(var28 / 180.0F * (float)Math.PI) * MathHelper.cos(var13 / 180.0F * (float)Math.PI)) * var27;
				double var16 = (double)(MathHelper.cos(var28 / 180.0F * (float)Math.PI) * MathHelper.cos(var13 / 180.0F * (float)Math.PI)) * var27;
				double var18 = (double)(-MathHelper.sin(var13 / 180.0F * (float)Math.PI)) * var27;

				for(int var20 = 0; var20 < 8; ++var20) {
					float var21 = (float)((var20 & 1) * 2 - 1);
					float var22 = (float)((var20 >> 1 & 1) * 2 - 1);
					float var23 = (float)((var20 >> 2 & 1) * 2 - 1);
					var21 *= 0.1F;
					var22 *= 0.1F;
					var23 *= 0.1F;
					MovingObjectPosition var24 = this.mc.theWorld.rayTraceBlocks(Vec3D.createVector(var4 + (double)var21, var6 + (double)var22, var8 + (double)var23), Vec3D.createVector(var4 - var14 + (double)var21 + (double)var23, var6 - var18 + (double)var22, var8 - var16 + (double)var23));
					if(var24 != null) {
						double var25 = var24.hitVec.distanceTo(Vec3D.createVector(var4, var6, var8));
						if(var25 < var27) {
							var27 = var25;
						}
					}
				}

				GL11.glRotatef(var2.rotationPitch - var13, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(var2.rotationYaw - var28, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(0.0F, 0.0F, (float)(-var27));
				GL11.glRotatef(var28 - var2.rotationYaw, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(var13 - var2.rotationPitch, 1.0F, 0.0F, 0.0F);
			}
		} else {
			GL11.glTranslatef(0.0F, 0.0F, -0.1F);
		}

		if(!this.mc.gameSettings.field_22273_E) {
			GL11.glRotatef(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * var1, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * var1 + 180.0F, 0.0F, 1.0F, 0.0F);
		}

		GL11.glTranslatef(0.0F, var3, 0.0F);
		var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * (double)var1;
		var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * (double)var1 - (double)var3;
		var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * (double)var1;
		this.cloudFog = this.mc.renderGlobal.func_27307_a(var4, var6, var8, var1);
	}

	private void setupCameraTransform(float var1, int var2) {
		this.farPlaneDistance = (float)(256 >> this.mc.gameSettings.renderDistance);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		float var3 = 0.07F;
		if(this.mc.gameSettings.anaglyph) {
			GL11.glTranslatef((float)(-(var2 * 2 - 1)) * var3, 0.0F, 0.0F);
		}

		if(this.cameraZoom != 1.0D) {
			GL11.glTranslatef((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
			GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
			GLU.gluPerspective(this.getFOVModifier(var1), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
		} else {
			GLU.gluPerspective(this.getFOVModifier(var1), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
		}

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		if(this.mc.gameSettings.anaglyph) {
			GL11.glTranslatef((float)(var2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
		}

		this.hurtCameraEffect(var1);
		if(this.mc.gameSettings.viewBobbing) {
			this.setupViewBobbing(var1);
		}

		float var4 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * var1;
		if(var4 > 0.0F) {
			float var5 = 5.0F / (var4 * var4 + 5.0F) - var4 * 0.04F;
			var5 *= var5;
			GL11.glRotatef(((float)this.rendererUpdateCount + var1) * 20.0F, 0.0F, 1.0F, 1.0F);
			GL11.glScalef(1.0F / var5, 1.0F, 1.0F);
			GL11.glRotatef(-((float)this.rendererUpdateCount + var1) * 20.0F, 0.0F, 1.0F, 1.0F);
		}

		this.orientCamera(var1);
	}

	private void func_4135_b(float var1, int var2) {
		GL11.glLoadIdentity();
		if(this.mc.gameSettings.anaglyph) {
			GL11.glTranslatef((float)(var2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
		}

		GL11.glPushMatrix();
		this.hurtCameraEffect(var1);
		if(this.mc.gameSettings.viewBobbing) {
			this.setupViewBobbing(var1);
		}

		if(!this.mc.gameSettings.thirdPersonView && !this.mc.renderViewEntity.isPlayerSleeping() && !this.mc.gameSettings.hideGUI) {
			this.itemRenderer.renderItemInFirstPerson(var1);
		}

		GL11.glPopMatrix();
		if(!this.mc.gameSettings.thirdPersonView && !this.mc.renderViewEntity.isPlayerSleeping()) {
			this.itemRenderer.renderOverlays(var1);
			this.hurtCameraEffect(var1);
		}

		if(this.mc.gameSettings.viewBobbing) {
			this.setupViewBobbing(var1);
		}

	}

	public void updateCameraAndRender(float var1) {
		if(!GL11.EaglerAdapterImpl2.isFocused()) {
			this.mc.displayInGameMenu();
		} else {
			this.prevFrameTime = System.currentTimeMillis();
		}

		if(this.mc.inGameHasFocus) {
			this.mc.mouseHelper.mouseXYChange();
			float var2 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float var3 = var2 * var2 * var2 * 8.0F;
			float var4 = (float)this.mc.mouseHelper.deltaX * var3;
			float var5 = (float)this.mc.mouseHelper.deltaY * var3;
			byte var6 = 1;
			if(this.mc.gameSettings.invertMouse) {
				var6 = -1;
			}

			if(this.mc.gameSettings.smoothCamera) {
				var4 = this.mouseFilterXAxis.func_22386_a(var4, 0.05F * var3);
				var5 = this.mouseFilterYAxis.func_22386_a(var5, 0.05F * var3);
			}

			this.mc.thePlayer.func_346_d(var4, var5 * (float)var6);
		}

		if(!this.mc.skipRenderWorld) {
			field_28135_a = this.mc.gameSettings.anaglyph;
			ScaledResolution var13 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int var14 = var13.getScaledWidth();
			int var15 = var13.getScaledHeight();
			int var16 = Mouse.getX() * var14 / this.mc.displayWidth;
			int var17 = var15 - Mouse.getY() * var15 / this.mc.displayHeight - 1;
			short var7 = 200;
			if(this.mc.gameSettings.limitFramerate == 1) {
				var7 = 120;
			}

			if(this.mc.gameSettings.limitFramerate == 2) {
				var7 = 40;
			}

			long var8;
			if(this.mc.theWorld != null) {
				if(this.mc.gameSettings.limitFramerate == 0) {
					this.renderWorld(var1, 0L);
				} else {
					this.renderWorld(var1, this.field_28133_I + (long)(1000000000 / var7));
				}

				if(this.mc.gameSettings.limitFramerate == 2) {
					var8 = (this.field_28133_I + (long)(1000000000 / var7) - System.nanoTime()) / 1000000L;
					if(var8 > 0L && var8 < 500L) {
						try {
							Thread.sleep(var8);
						} catch (InterruptedException var12) {
							var12.printStackTrace();
						}
					}
				}

				this.field_28133_I = System.nanoTime();
				if(!this.mc.gameSettings.hideGUI || this.mc.currentScreen != null) {
					GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
					long framebufferAge = this.overlayFramebuffer.getAge();
					if(framebufferAge == -1l || framebufferAge > (Minecraft.debugFPS < 25 ? 125l : 75l)) {
						this.overlayFramebuffer.beginRender(mc.displayWidth, mc.displayHeight);
						GL11.glColorMask(true, true, true, true);
						GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
						GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
						GL11.enableOverlayFramebufferBlending(true);
						this.mc.ingameGUI.renderGameOverlay(var1, this.mc.currentScreen != null, var16, var17);
						GL11.enableOverlayFramebufferBlending(false);
						this.overlayFramebuffer.endRender();
						GL11.glClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
					}
					this.func_905_b();
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glEnable(GL11.GL_BLEND);
					if (this.mc.gameSettings.fancyGraphics) {
						this.mc.ingameGUI.renderVignette(this.mc.thePlayer.getEntityBrightness(var1), var14, var15);
					}
					this.mc.ingameGUI.renderCrossHairs(var14, var15);
					GL11.glBindTexture(this.overlayFramebuffer.getTexture());
					GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glDepthMask(false);
					Tessellator tessellator = Tessellator.instance;
					tessellator.startDrawingQuads();
					tessellator.addVertexWithUV(0.0D, (double) var15, -90.0D, 0.0D, 0.0D);
					tessellator.addVertexWithUV((double) var14, (double) var15, -90.0D, 1.0D, 0.0D);
					tessellator.addVertexWithUV((double) var14, 0.0D, -90.0D, 1.0D, 1.0D);
					tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 1.0D);
					tessellator.draw();
					GL11.glDepthMask(true);
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glDisable(GL11.GL_BLEND);
				}
			} else {
				GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();
				this.func_905_b();
				if(this.mc.gameSettings.limitFramerate == 2) {
					var8 = (this.field_28133_I + (long)(1000000000 / var7) - System.nanoTime()) / 1000000L;
					if(var8 < 0L) {
						var8 += 10L;
					}

					if(var8 > 0L && var8 < 500L) {
						try {
							Thread.sleep(var8);
						} catch (InterruptedException var11) {
							var11.printStackTrace();
						}
					}
				}

				this.field_28133_I = System.nanoTime();
			}

			if(this.mc.currentScreen != null) {
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				this.mc.currentScreen.drawScreen(var16, var17, var1);
				if(this.mc.currentScreen != null && this.mc.currentScreen.field_25091_h != null) {
					this.mc.currentScreen.field_25091_h.func_25087_a(var1);
				}
			}

		}
	}

	public void renderWorld(float var1, long var2) {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		if(this.mc.renderViewEntity == null) {
			this.mc.renderViewEntity = this.mc.thePlayer;
		}

		this.getMouseOver(var1);
		EntityLiving var4 = this.mc.renderViewEntity;
		RenderGlobal var5 = this.mc.renderGlobal;
		EffectRenderer var6 = this.mc.effectRenderer;
		double var7 = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * (double)var1;
		double var9 = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * (double)var1;
		double var11 = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * (double)var1;
		IChunkProvider var13 = this.mc.theWorld.getIChunkProvider();
		int var16;
		if(var13 instanceof ChunkProviderLoadOrGenerate) {
			ChunkProviderLoadOrGenerate var14 = (ChunkProviderLoadOrGenerate)var13;
			int var15 = MathHelper.floor_float((float)((int)var7)) >> 4;
			var16 = MathHelper.floor_float((float)((int)var11)) >> 4;
			var14.setCurrentChunkOver(var15, var16);
		}

		for(int var18 = 0; var18 < 2; ++var18) {
			if(this.mc.gameSettings.anaglyph) {
				anaglyphField = var18;
				if(anaglyphField == 0) {
					GL11.glColorMask(false, true, true, false);
				} else {
					GL11.glColorMask(true, false, false, false);
				}
			}

			GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
			this.updateFogColor(var1);
			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
			GL11.glEnable(GL11.GL_CULL_FACE);
			this.setupCameraTransform(var1, var18);
			ClippingHelperImpl.getInstance();
			if(this.mc.gameSettings.renderDistance < 2) {
				this.setupFog(-1, var1);
				var5.renderSky(var1);
			}

			GL11.glEnable(GL11.GL_FOG);
			this.setupFog(1, var1);

			Frustrum var19 = new Frustrum();
			var19.setPosition(var7, var9, var11);
			this.mc.renderGlobal.clipRenderersByFrustrum(var19, var1);
			if(var18 == 0) {
				while(!this.mc.renderGlobal.updateRenderers(var4, false) && var2 != 0L) {
					long var20 = var2 - System.nanoTime();
					if(var20 < 0L || var20 > 1000000000L) {
						break;
					}
				}
			}

			this.setupFog(0, var1);
			GL11.glEnable(GL11.GL_FOG);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
			RenderHelper.disableStandardItemLighting();
			var5.sortAndRender(var4, 0, (double)var1);
			RenderHelper.enableStandardItemLighting();
			var5.renderEntities(var4.getPosition(var1), var19, var1);
			var6.func_1187_b(var4, var1);
			RenderHelper.disableStandardItemLighting();
			this.setupFog(0, var1);
			if(this.mc.gameSettings.particles) {
				var6.renderParticles(var4, var1);
			}
			EntityPlayer var21;
			if(this.mc.objectMouseOver != null && var4.isInsideOfMaterial(Material.water) && var4 instanceof EntityPlayer) {
				var21 = (EntityPlayer)var4;
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				var5.drawBlockBreaking(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
				var5.drawSelectionBox(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
			}

			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.setupFog(0, var1);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
			//TODO: Find out why water is broken
			
//			if(this.mc.gameSettings.fancyGraphics) {
//				GL11.glColorMask(false, false, false, false);
//				var16 = var5.sortAndRender(var4, 1, (double)var1);
//				if(this.mc.gameSettings.anaglyph) {
//					if(anaglyphField == 0) {
//						GL11.glColorMask(false, true, true, true);
//					} else {
//						GL11.glColorMask(true, false, false, true);
//					}
//				} else {
//					GL11.glColorMask(true, true, true, true);
//				}
//
//				if(var16 > 0) {
//					var5.renderAllRenderLists(1, (double)var1);
//				}
//			} else {
				var5.sortAndRender(var4, 1, (double)var1);
//			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
			if(this.cameraZoom == 1.0D && var4 instanceof EntityPlayer && this.mc.objectMouseOver != null && !var4.isInsideOfMaterial(Material.water)) {
				var21 = (EntityPlayer)var4;
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				var5.drawBlockBreaking(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
				var5.drawSelectionBox(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
			}

			this.renderRainSnow(var1);
			GL11.glDisable(GL11.GL_FOG);
			if(this.pointedEntity != null) {
			}

			this.setupFog(0, var1);
			GL11.glEnable(GL11.GL_FOG);
			var5.renderClouds(var1);
			GL11.glDisable(GL11.GL_FOG);
			this.setupFog(1, var1);
			if(this.cameraZoom == 1.0D) {
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				this.func_4135_b(var1, var18);
			}

			if(!this.mc.gameSettings.anaglyph) {
				return;
			}
		}

		GL11.glColorMask(true, true, true, false);
	}

	private void addRainParticles() {
		if(!this.mc.gameSettings.particles) {
			return;
		}
		
		float var1 = this.mc.theWorld.func_27162_g(1.0F);
		if(!this.mc.gameSettings.fancyGraphics) {
			var1 /= 2.0F;
		}

		if(var1 != 0.0F) {
			this.random.setSeed((long)this.rendererUpdateCount * 312987231L);
			EntityLiving var2 = this.mc.renderViewEntity;
			World var3 = this.mc.theWorld;
			int var4 = MathHelper.floor_double(var2.posX);
			int var5 = MathHelper.floor_double(var2.posY);
			int var6 = MathHelper.floor_double(var2.posZ);
			byte var7 = 10;
			double var8 = 0.0D;
			double var10 = 0.0D;
			double var12 = 0.0D;
			int var14 = 0;

			for(int var15 = 0; var15 < (int)(100.0F * var1 * var1); ++var15) {
				int var16 = var4 + this.random.nextInt(var7) - this.random.nextInt(var7);
				int var17 = var6 + this.random.nextInt(var7) - this.random.nextInt(var7);
				int var18 = var3.findTopSolidBlock(var16, var17);
				int var19 = var3.getBlockId(var16, var18 - 1, var17);
				if(var18 <= var5 + var7 && var18 >= var5 - var7 && var3.getWorldChunkManager().getBiomeGenAt(var16, var17).canSpawnLightningBolt()) {
					float var20 = this.random.nextFloat();
					float var21 = this.random.nextFloat();
					if(var19 > 0) {
						if(Block.blocksList[var19].blockMaterial == Material.lava) {
							this.mc.effectRenderer.addEffect(new EntitySmokeFX(var3, (double)((float)var16 + var20), (double)((float)var18 + 0.1F) - Block.blocksList[var19].minY, (double)((float)var17 + var21), 0.0D, 0.0D, 0.0D));
						} else {
							++var14;
							if(this.random.nextInt(var14) == 0) {
								var8 = (double)((float)var16 + var20);
								var10 = (double)((float)var18 + 0.1F) - Block.blocksList[var19].minY;
								var12 = (double)((float)var17 + var21);
							}

							this.mc.effectRenderer.addEffect(new EntityRainFX(var3, (double)((float)var16 + var20), (double)((float)var18 + 0.1F) - Block.blocksList[var19].minY, (double)((float)var17 + var21)));
						}
					}
				}
			}

			if(var14 > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
				this.rainSoundCounter = 0;
				if(var10 > var2.posY + 1.0D && var3.findTopSolidBlock(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posZ)) > MathHelper.floor_double(var2.posY)) {
					this.mc.theWorld.playSoundEffect(var8, var10, var12, "ambient.weather.rain", 0.1F, 0.5F);
				} else {
					this.mc.theWorld.playSoundEffect(var8, var10, var12, "ambient.weather.rain", 0.2F, 1.0F);
				}
			}

		}
	}

	protected void renderRainSnow(float var1) {
		float var2 = this.mc.theWorld.func_27162_g(var1);
		if(var2 > 0.0F) {
			EntityLiving var3 = this.mc.renderViewEntity;
			World var4 = this.mc.theWorld;
			int var5 = MathHelper.floor_double(var3.posX);
			int var6 = MathHelper.floor_double(var3.posY);
			int var7 = MathHelper.floor_double(var3.posZ);
			Tessellator var8 = Tessellator.instance;
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.01F);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/environment/snow.png"));
			double var9 = var3.lastTickPosX + (var3.posX - var3.lastTickPosX) * (double)var1;
			double var11 = var3.lastTickPosY + (var3.posY - var3.lastTickPosY) * (double)var1;
			double var13 = var3.lastTickPosZ + (var3.posZ - var3.lastTickPosZ) * (double)var1;
			int var15 = MathHelper.floor_double(var11);
			byte var16 = 5;
			if(this.mc.gameSettings.fancyGraphics) {
				var16 = 10;
			}

			BiomeGenBase[] var17 = var4.getWorldChunkManager().func_4069_a(var5 - var16, var7 - var16, var16 * 2 + 1, var16 * 2 + 1);
			int var18 = 0;

			int var19;
			int var20;
			BiomeGenBase var21;
			int var22;
			int var23;
			int var24;
			float var26;
			for(var19 = var5 - var16; var19 <= var5 + var16; ++var19) {
				for(var20 = var7 - var16; var20 <= var7 + var16; ++var20) {
					var21 = var17[var18++];
					if(var21.getEnableSnow()) {
						var22 = var4.findTopSolidBlock(var19, var20);
						if(var22 < 0) {
							var22 = 0;
						}

						var23 = var22;
						if(var22 < var15) {
							var23 = var15;
						}

						var24 = var6 - var16;
						int var25 = var6 + var16;
						if(var24 < var22) {
							var24 = var22;
						}

						if(var25 < var22) {
							var25 = var22;
						}

						var26 = 1.0F;
						if(var24 != var25) {
							this.random.setSeed((long)(var19 * var19 * 3121 + var19 * 45238971 + var20 * var20 * 418711 + var20 * 13761));
							float var27 = (float)this.rendererUpdateCount + var1;
							float var28 = ((float)(this.rendererUpdateCount & 511) + var1) / 512.0F;
							float var29 = this.random.nextFloat() + var27 * 0.01F * (float)this.random.nextGaussian();
							float var30 = this.random.nextFloat() + var27 * (float)this.random.nextGaussian() * 0.001F;
							double var31 = (double)((float)var19 + 0.5F) - var3.posX;
							double var33 = (double)((float)var20 + 0.5F) - var3.posZ;
							float var35 = MathHelper.sqrt_double(var31 * var31 + var33 * var33) / (float)var16;
							var8.startDrawingQuads();
							float var36 = var4.getLightBrightness(var19, var23, var20);
							GL11.glColor4f(var36, var36, var36, ((1.0F - var35 * var35) * 0.3F + 0.5F) * var2);
							var8.setTranslationD(-var9 * 1.0D, -var11 * 1.0D, -var13 * 1.0D);
							var8.addVertexWithUV((double)(var19 + 0), (double)var24, (double)var20 + 0.5D, (double)(0.0F * var26 + var29), (double)((float)var24 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)(var19 + 1), (double)var24, (double)var20 + 0.5D, (double)(1.0F * var26 + var29), (double)((float)var24 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)(var19 + 1), (double)var25, (double)var20 + 0.5D, (double)(1.0F * var26 + var29), (double)((float)var25 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)(var19 + 0), (double)var25, (double)var20 + 0.5D, (double)(0.0F * var26 + var29), (double)((float)var25 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var24, (double)(var20 + 0), (double)(0.0F * var26 + var29), (double)((float)var24 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var24, (double)(var20 + 1), (double)(1.0F * var26 + var29), (double)((float)var24 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var25, (double)(var20 + 1), (double)(1.0F * var26 + var29), (double)((float)var25 * var26 / 4.0F + var28 * var26 + var30));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var25, (double)(var20 + 0), (double)(0.0F * var26 + var29), (double)((float)var25 * var26 / 4.0F + var28 * var26 + var30));
							var8.setTranslationD(0.0D, 0.0D, 0.0D);
							var8.draw();
						}
					}
				}
			}

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/environment/rain.png"));
			if(this.mc.gameSettings.fancyGraphics) {
				var16 = 10;
			}

			var18 = 0;

			for(var19 = var5 - var16; var19 <= var5 + var16; ++var19) {
				for(var20 = var7 - var16; var20 <= var7 + var16; ++var20) {
					var21 = var17[var18++];
					if(var21.canSpawnLightningBolt()) {
						var22 = var4.findTopSolidBlock(var19, var20);
						var23 = var6 - var16;
						var24 = var6 + var16;
						if(var23 < var22) {
							var23 = var22;
						}

						if(var24 < var22) {
							var24 = var22;
						}

						float var37 = 1.0F;
						if(var23 != var24) {
							this.random.setSeed((long)(var19 * var19 * 3121 + var19 * 45238971 + var20 * var20 * 418711 + var20 * 13761));
							var26 = ((float)(this.rendererUpdateCount + var19 * var19 * 3121 + var19 * 45238971 + var20 * var20 * 418711 + var20 * 13761 & 31) + var1) / 32.0F * (3.0F + this.random.nextFloat());
							double var38 = (double)((float)var19 + 0.5F) - var3.posX;
							double var39 = (double)((float)var20 + 0.5F) - var3.posZ;
							float var40 = MathHelper.sqrt_double(var38 * var38 + var39 * var39) / (float)var16;
							var8.startDrawingQuads();
							float var32 = var4.getLightBrightness(var19, 128, var20) * 0.85F + 0.15F;
							GL11.glColor4f(var32, var32, var32, ((1.0F - var40 * var40) * 0.5F + 0.5F) * var2);
							var8.setTranslationD(-var9 * 1.0D, -var11 * 1.0D, -var13 * 1.0D);
							var8.addVertexWithUV((double)(var19 + 0), (double)var23, (double)var20 + 0.5D, (double)(0.0F * var37), (double)((float)var23 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)(var19 + 1), (double)var23, (double)var20 + 0.5D, (double)(1.0F * var37), (double)((float)var23 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)(var19 + 1), (double)var24, (double)var20 + 0.5D, (double)(1.0F * var37), (double)((float)var24 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)(var19 + 0), (double)var24, (double)var20 + 0.5D, (double)(0.0F * var37), (double)((float)var24 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var23, (double)(var20 + 0), (double)(0.0F * var37), (double)((float)var23 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var23, (double)(var20 + 1), (double)(1.0F * var37), (double)((float)var23 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var24, (double)(var20 + 1), (double)(1.0F * var37), (double)((float)var24 * var37 / 4.0F + var26 * var37));
							var8.addVertexWithUV((double)var19 + 0.5D, (double)var24, (double)(var20 + 0), (double)(0.0F * var37), (double)((float)var24 * var37 / 4.0F + var26 * var37));
							var8.setTranslationD(0.0D, 0.0D, 0.0D);
							var8.draw();
						}
					}
				}
			}

			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		}
	}

	public void func_905_b() {
		ScaledResolution var1 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, var1.field_25121_a, var1.field_25120_b, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
	}

	private void updateFogColor(float var1) {
		World var2 = this.mc.theWorld;
		EntityLiving var3 = this.mc.renderViewEntity;
		float var4 = 1.0F / (float)(4 - this.mc.gameSettings.renderDistance);
		var4 = 1.0F - (float)Math.pow((double)var4, 0.25D);
		Vec3D var5 = var2.func_4079_a(this.mc.renderViewEntity, var1);
		float var6 = (float)var5.xCoord;
		float var7 = (float)var5.yCoord;
		float var8 = (float)var5.zCoord;
		Vec3D var9 = var2.getFogColor(var1);
		this.fogColorRed = (float)var9.xCoord;
		this.fogColorGreen = (float)var9.yCoord;
		this.fogColorBlue = (float)var9.zCoord;
		this.fogColorRed += (var6 - this.fogColorRed) * var4;
		this.fogColorGreen += (var7 - this.fogColorGreen) * var4;
		this.fogColorBlue += (var8 - this.fogColorBlue) * var4;
		float var10 = var2.func_27162_g(var1);
		float var11;
		float var12;
		if(var10 > 0.0F) {
			var11 = 1.0F - var10 * 0.5F;
			var12 = 1.0F - var10 * 0.4F;
			this.fogColorRed *= var11;
			this.fogColorGreen *= var11;
			this.fogColorBlue *= var12;
		}

		var11 = var2.func_27166_f(var1);
		if(var11 > 0.0F) {
			var12 = 1.0F - var11 * 0.5F;
			this.fogColorRed *= var12;
			this.fogColorGreen *= var12;
			this.fogColorBlue *= var12;
		}

		if(this.cloudFog) {
			Vec3D var16 = var2.func_628_d(var1);
			this.fogColorRed = (float)var16.xCoord;
			this.fogColorGreen = (float)var16.yCoord;
			this.fogColorBlue = (float)var16.zCoord;
		} else if(var3.isInsideOfMaterial(Material.water)) {
			this.fogColorRed = 0.02F;
			this.fogColorGreen = 0.02F;
			this.fogColorBlue = 0.2F;
		} else if(var3.isInsideOfMaterial(Material.lava)) {
			this.fogColorRed = 0.6F;
			this.fogColorGreen = 0.1F;
			this.fogColorBlue = 0.0F;
		}

		var12 = this.fogColor2 + (this.fogColor1 - this.fogColor2) * var1;
		this.fogColorRed *= var12;
		this.fogColorGreen *= var12;
		this.fogColorBlue *= var12;
		if(this.mc.gameSettings.anaglyph) {
			float var13 = (this.fogColorRed * 30.0F + this.fogColorGreen * 59.0F + this.fogColorBlue * 11.0F) / 100.0F;
			float var14 = (this.fogColorRed * 30.0F + this.fogColorGreen * 70.0F) / 100.0F;
			float var15 = (this.fogColorRed * 30.0F + this.fogColorBlue * 70.0F) / 100.0F;
			this.fogColorRed = var13;
			this.fogColorGreen = var14;
			this.fogColorBlue = var15;
		}

		GL11.glClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
	}

	private void setupFog(int var1, float var2) {
		EntityLiving var3 = this.mc.renderViewEntity;
		GL11.glFog(GL11.GL_FOG_COLOR, this.func_908_a(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 1.0F));
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var4;
		float var5;
		float var6;
		float var7;
		float var8;
		float var9;
		if(this.cloudFog) {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			GL11.glFogf(GL11.GL_FOG_DENSITY, 0.1F);
			var4 = 1.0F;
			var5 = 1.0F;
			var6 = 1.0F;
			if(this.mc.gameSettings.anaglyph) {
				var7 = (var4 * 30.0F + var5 * 59.0F + var6 * 11.0F) / 100.0F;
				var8 = (var4 * 30.0F + var5 * 70.0F) / 100.0F;
				var9 = (var4 * 30.0F + var6 * 70.0F) / 100.0F;
			}
		} else if(var3.isInsideOfMaterial(Material.water)) {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			GL11.glFogf(GL11.GL_FOG_DENSITY, 0.1F);
			var4 = 0.4F;
			var5 = 0.4F;
			var6 = 0.9F;
			if(this.mc.gameSettings.anaglyph) {
				var7 = (var4 * 30.0F + var5 * 59.0F + var6 * 11.0F) / 100.0F;
				var8 = (var4 * 30.0F + var5 * 70.0F) / 100.0F;
				var9 = (var4 * 30.0F + var6 * 70.0F) / 100.0F;
			}
		} else if(var3.isInsideOfMaterial(Material.lava)) {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			GL11.glFogf(GL11.GL_FOG_DENSITY, 2.0F);
			var4 = 0.4F;
			var5 = 0.3F;
			var6 = 0.3F;
			if(this.mc.gameSettings.anaglyph) {
				var7 = (var4 * 30.0F + var5 * 59.0F + var6 * 11.0F) / 100.0F;
				var8 = (var4 * 30.0F + var5 * 70.0F) / 100.0F;
				var9 = (var4 * 30.0F + var6 * 70.0F) / 100.0F;
			}
		} else {
			GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
			GL11.glFogf(GL11.GL_FOG_START, this.farPlaneDistance * 0.25F);
			GL11.glFogf(GL11.GL_FOG_END, this.farPlaneDistance);
			if(var1 < 0) {
				GL11.glFogf(GL11.GL_FOG_START, 0.0F);
				GL11.glFogf(GL11.GL_FOG_END, this.farPlaneDistance * 0.8F);
			}

			if(this.mc.theWorld.worldProvider.isNether) {
				GL11.glFogf(GL11.GL_FOG_START, 0.0F);
			}
		}

		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT);
	}

	private FloatBuffer func_908_a(float var1, float var2, float var3, float var4) {
		this.fogColorBuffer.clear();
		this.fogColorBuffer.put(var1).put(var2).put(var3).put(var4);
		this.fogColorBuffer.flip();
		return this.fogColorBuffer;
	}
}
