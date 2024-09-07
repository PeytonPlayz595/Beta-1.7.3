package org.lwjgl.util.vector;

public interface WritableVector4f extends WritableVector3f {

	void setW(float w);

	void set(float x, float y, float z, float w);

}