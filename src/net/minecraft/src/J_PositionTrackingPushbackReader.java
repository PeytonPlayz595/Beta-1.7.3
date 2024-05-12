package net.minecraft.src;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

final class J_PositionTrackingPushbackReader implements J_ThingWithPosition {
	private final CharBuffer field_27338_a;
	private int field_27337_b = 0;
	private int field_27340_c = 1;
	private boolean field_27339_d = false;

	public J_PositionTrackingPushbackReader(Reader var1) {
		CharBuffer buffer;
		try {
			StringBuilder builder = new StringBuilder();
	    	int c;
	    	while ((c = var1.read()) != -1) {
	    		builder.append((char) c);
	    	}
	    	buffer = CharBuffer.wrap(builder);
		} catch(IOException e) {
			buffer = CharBuffer.allocate(1);
		}
		
		this.field_27338_a = buffer;
	}

	public void func_27334_a(char var1) {
		field_27338_a.position(field_27338_a.position() - 1);
		if (var1 == '\n' && !field_27339_d) {
			field_27337_b = 0;
			field_27340_c--;
		} else {
			field_27337_b--;
			if (field_27337_b < 0) {
				field_27337_b = 0;
			}
		}
		field_27339_d = var1 == '\n';
	}

	public void func_27335_a(char[] var1) {
		for (int i = var1.length - 1; i >= 0; i--) {
			func_27334_a(var1[i]);
		}
	}

	public int func_27333_c() throws IOException {
		int var1 = field_27338_a.get();
		func_27332_a(var1);
	    return var1;
	}

	public int func_27336_b(char[] var1) throws IOException {
		int var2 = field_27338_a.read(CharBuffer.wrap(var1));
	    for (int i = 0; i < var2; i++) {
	    	func_27332_a(var1[i]);
	    }
	    return var2;
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
