package net.minecraft.src;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class StatBase {
	public final int statId;
	public final String statName;
	public boolean field_27088_g;
	public String statGuid;
	private final IStatType field_26902_a;
	private static NumberFormat field_26903_b = NumberFormat.getIntegerInstance(Locale.US);
	public static IStatType field_27087_i = new StatTypeSimple();
	private static DecimalFormat field_26904_c = new DecimalFormat("########0.00");
	public static IStatType field_27086_j = new StatTypeTime();
	public static IStatType field_27085_k = new StatTypeDistance();

	public StatBase(int var1, String var2, IStatType var3) {
		this.field_27088_g = false;
		this.statId = var1;
		this.statName = var2;
		this.field_26902_a = var3;
	}

	public StatBase(int var1, String var2) {
		this(var1, var2, field_27087_i);
	}

	public StatBase func_27082_h() {
		this.field_27088_g = true;
		return this;
	}

	public StatBase registerStat() {
		if(StatList.field_25169_C.containsKey(Integer.valueOf(this.statId))) {
			throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.field_25169_C.get(Integer.valueOf(this.statId))).statName + "\" and \"" + this.statName + "\" at id " + this.statId);
		} else {
			StatList.field_25188_a.add(this);
			StatList.field_25169_C.put(Integer.valueOf(this.statId), this);
			this.statGuid = AchievementMap.getGuid(this.statId);
			return this;
		}
	}

	public boolean func_25067_a() {
		return false;
	}

	public String func_27084_a(int var1) {
		return this.field_26902_a.func_27192_a(var1);
	}

	public String toString() {
		return this.statName;
	}

	static NumberFormat func_27083_i() {
		return field_26903_b;
	}

	static DecimalFormat func_27081_j() {
		return field_26904_c;
	}
}
