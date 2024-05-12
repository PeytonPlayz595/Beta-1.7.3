package net.minecraft.src;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class MD5String {
	private String field_27370_a;

	public MD5String(String var1) {
		this.field_27370_a = var1;
	}
	
	public String func_27369_a(String var1) {
        String var2 = this.field_27370_a + var1;
        String md5Hash = calculateMD5(var2);
        return md5Hash;
    }

    private String calculateMD5(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        BigInteger no = new BigInteger(1, bytes);
        String hash = no.toString(16);
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash;
    }
}
