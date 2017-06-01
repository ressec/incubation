/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project artefact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.test.data;

import java.io.Serializable;
import java.lang.management.MemoryType;
import java.util.Random;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Test class representing an object containing primitives.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@ToString(exclude = { "rnd" })
@EqualsAndHashCode
public class PrimitiveObject implements Serializable
{
	/**
	 * Serialization version identifier.
	 */
	public final static long serialVersionUID = 1L;

	/**
	 * Random number generator.
	 */
	private final transient Random rnd = new Random();

	/**
	 * An integer primitive data.
	 */
	protected int intValue = 0;

	/**
	 * A byte primitive data.
	 */
	protected byte byteValue = 0;

	/**
	 * A short primitive data.
	 */
	protected short shortValue = 0;

	/**
	 * A long primitive data.
	 */
	protected long longValue = 0L;

	/**
	 * A boolean primitive data.
	 */
	protected boolean boolValue = false;

	/**
	 * A double primitive data.
	 */
	protected double doubleValue = 0.0;

	/**
	 * A float primitive data.
	 */
	protected float floatValue = 0.0f;

	/**
	 * A char primitive data.
	 */
	protected char charValue = 0x0;

	/**
	 * A string data.
	 */
	protected String stringValue = null;

	/**
	 * An enum primitive data.
	 */
	protected MemoryType enumValue = MemoryType.HEAP;

	/**
	 * Creates a new object.
	 */
	public PrimitiveObject()
	{
		byteValue = (byte) rnd.nextInt();
		shortValue = (short) rnd.nextInt();
		intValue = rnd.nextInt();
		longValue = rnd.nextLong();
		doubleValue = rnd.nextDouble();
		floatValue = rnd.nextFloat();
		enumValue = MemoryType.NON_HEAP;
	}

	/**
	 * Returns the {@code long} value.
	 * <p>
	 * @return Return the {@code long}.
	 */
	public final long getLong()
	{
		return longValue;
	}

	/**
	 * Sets the {@code long} value.
	 * <p>
	 * @param data The {@code long} value to set.
	 */
	public final void setLong(final long data)
	{
		longValue = data;
	}

	/**
	 * Returns the {@code byte} value.
	 * <p>
	 * @return Return the {@code byte}.
	 */
	public final byte getByte()
	{
		return byteValue;
	}

	/**
	 * Sets the {@code byte} value.
	 * <p>
	 * @param data The {@code byte} value to set.
	 */
	public final void setByte(final byte data)
	{
		byteValue = data;
	}

	/**
	 * Returns the {@code int} value.
	 * <p>
	 * @return Return the {@code int}.
	 */
	public final int getInteger()
	{
		return intValue;
	}

	/**
	 * Sets the {@code int} value.
	 * <p>
	 * @param data The {@code int} value to set.
	 */
	public final void setInteger(final int data)
	{
		intValue = data;
	}

	/**
	 * Returns the {@code short} value.
	 * <p>
	 * @return Return the {@code short}.
	 */
	public final short getShort()
	{
		return shortValue;
	}

	/**
	 * Sets the {@code short} value.
	 * <p>
	 * @param data The {@code short} value to set.
	 */
	public final void setShort(final short data)
	{
		shortValue = data;
	}

	/**
	 * Returns the {@code double} value.
	 * <p>
	 * @return Return the {@code double}.
	 */
	public final double getDouble()
	{
		return doubleValue;
	}

	/**
	 * Sets the {@code double} value.
	 * <p>
	 * @param data The {@code double} value to set.
	 */
	public final void setDouble(final double data)
	{
		doubleValue = data;
	}

	/**
	 * Returns the {@code float} value.
	 * <p>
	 * @return Return the {@code float}.
	 */
	public final float getFloat()
	{
		return floatValue;
	}

	/**
	 * Sets the {@code float} value.
	 * <p>
	 * @param data The {@code float} value to set.
	 */
	public final void setFloat(final float data)
	{
		floatValue = data;
	}

	/**
	 * Returns the {@code boolean} value.
	 * <p>
	 * @return Return the {@code boolean}.
	 */
	public final boolean getBoolean()
	{
		return boolValue;
	}

	/**
	 * Sets the {@code boolean} value.
	 * <p>
	 * @param data The {@code boolean} value to set.
	 */
	public final void setBoolean(final boolean data)
	{
		boolValue = data;
	}

	/**
	 * Returns the {@code char} value.
	 * <p>
	 * @return Return the {@code char}.
	 */
	public final char getChar()
	{
		return charValue;
	}

	/**
	 * Sets the {@code char} value.
	 * <p>
	 * @param data The {@code char} value to set.
	 */
	public final void setChar(final char data)
	{
		charValue = data;
	}

	/**
	 * Returns the {@code string} value.
	 * <p>
	 * @return Return the {@code string}.
	 */
	public final String getString()
	{
		return stringValue;
	}

	/**
	 * Sets the {@code string} value.
	 * <p>
	 * @param data The {@code string} value to set.
	 */
	public final void setString(final String data)
	{
		stringValue = data;
	}

	/**
	 * Returns the {@code enum} value.
	 * <p>
	 * @return Return the {@code enum}.
	 */
	public final MemoryType getEnum()
	{
		return enumValue;
	}

	/**
	 * Sets the {@code enum} value.
	 * <p>
	 * @param data The {@code enum} value to set.
	 */
	public final void setEnum(final MemoryType data)
	{
		enumValue = data;
	}
}