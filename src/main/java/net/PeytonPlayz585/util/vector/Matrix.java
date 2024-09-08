package net.PeytonPlayz585.util.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public abstract class Matrix implements Serializable {

	protected Matrix() {
		super();
	}

	public abstract Matrix setIdentity();

	public abstract Matrix invert();

	public abstract Matrix load(FloatBuffer buf);

	public abstract Matrix loadTranspose(FloatBuffer buf);

	public abstract Matrix negate();

	public abstract Matrix store(FloatBuffer buf);

	public abstract Matrix storeTranspose(FloatBuffer buf);

	public abstract Matrix transpose();

	public abstract Matrix setZero();

	public abstract float determinant();
}