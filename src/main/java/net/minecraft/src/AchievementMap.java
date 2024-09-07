package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

public class AchievementMap {
	public static AchievementMap instance = new AchievementMap();
	private Map guidMap = new HashMap();

	private AchievementMap() {
		try {
			BufferedReader var1 = new BufferedReader(new InputStreamReader(GL11.getResourceAsStream("/achievement/map.txt")));

			while(true) {
				String var2 = var1.readLine();
				if(var2 == null) {
					var1.close();
					break;
				}

				String[] var3 = var2.split(",");
				int var4 = Integer.parseInt(var3[0]);
				this.guidMap.put(Integer.valueOf(var4), var3[1]);
			}
		} catch (Exception var5) {
			var5.printStackTrace();
		}

	}

	public static String getGuid(int var0) {
		return (String)instance.guidMap.get(Integer.valueOf(var0));
	}
}
