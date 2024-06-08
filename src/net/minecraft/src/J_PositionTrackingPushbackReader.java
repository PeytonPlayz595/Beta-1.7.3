package net.minecraft.src;

import java.io.IOException;

final class J_PositionTrackingPushbackReader implements J_ThingWithPosition {
	private String field_27338_a;
	private int field_27337_b = 0;
	private int field_27340_c = 1;
	private boolean field_27339_d = false;
	private int index = 0;

	public J_PositionTrackingPushbackReader(String var1) {
		if(var1 == null) {
			this.field_27338_a = "";
		} else {
			this.field_27338_a = var1;
		}
	}

	public void func_27334_a(char var1) throws IOException {
		--this.field_27337_b;
		if(this.field_27337_b < 0) {
			this.field_27337_b = 0;
		}
		index--;
	}

	public void func_27335_a(char[] var1) {
		this.field_27337_b -= var1.length;
		if(this.field_27337_b < 0) {
			this.field_27337_b = 0;
		}
		index -= var1.length;
	}

	public int func_27333_c() throws IOException {
		if (index >= field_27338_a.length()) {
	        return -1; //EOF reached (I think)
	    }
		char var1 = field_27338_a.charAt(index++);
        this.func_27332_a(var1);
        return var1;
	}

	public int func_27336_b(char[] var1) throws IOException {
		int length = Math.min(var1.length, field_27338_a.length() - index);
        for (int i = 0; i < length; i++) {
            var1[i] = field_27338_a.charAt(index++);
            this.func_27332_a(var1[i]);
        }
        return length;
	}

	private void func_27332_a(int var1) {
		if(13 == var1) {
			this.field_27337_b = 0;
			++this.field_27340_c;
			this.field_27339_d = true;
		} else {
			if(10 == var1 && !this.field_27339_d) {
				this.field_27337_b = 0;
				++this.field_27340_c;
			} else {
				++this.field_27337_b;
			}

			this.field_27339_d = false;
		}

	}

	public int func_27331_a() {
		return this.field_27337_b;
	}

	public int func_27330_b() {
		return this.field_27340_c;
	}
}