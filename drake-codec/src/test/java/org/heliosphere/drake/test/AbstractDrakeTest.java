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
package org.heliosphere.drake.test;

import java.lang.management.MemoryType;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.heliosphere.drake.base.codec.ICodec;
import org.heliosphere.drake.base.exception.AbstractCheckedException;
import org.heliosphere.drake.base.exception.AbstractUncheckedException;
import org.heliosphere.drake.base.exception.IExceptionType;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.test.data.ComplexObject;
import org.heliosphere.drake.base.test.data.DataObject;
import org.heliosphere.drake.base.test.data.PrimitiveObject;
import org.junit.Assert;

/**
 * An abstract test class for the codecs.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class AbstractDrakeTest
{
	/**
	 * Buffer of bytes.
	 */
	protected static ByteBuffer buffer = null;

	/**
	 * Codec used to encode/decode messages.
	 */
	protected static IMessageCodec messageCodec = null;

	/**
	 * Codec used to encode/decode data.
	 */
	protected static ICodec dataCodec = null;

	/**
	 * Random number generator.
	 */
	protected final static Random rnd = new Random();

	/**
	 * Buffer allocation default size {@code = 32'000}.
	 */
	protected static int BUFFER_ALLOCATION_SIZE = 32000;

	/**
	 * Number of iterations {@code = 1'000}.
	 */
	protected static int ITERATIONS_1K = 1000;

	/**
	 * Number of iterations {@code = 10'000}.
	 */
	protected static int ITERATIONS_10K = 10000;

	/**
	 * Number of iterations {@code = 100'000}.
	 */
	protected static int ITERATIONS_100K = 100000;

	/**
	 * Number of iterations {@code = 1'000'000}.
	 */
	protected static int ITERATIONS_1000K = 1000000;

	/**
	 * Collection size.
	 */
	protected static int COLLECTION_SIZE = 3;

	/**
	 * Array size {@code = 1'000}.
	 */
	protected static int ARRAY_SIZE = 1000;

	/**
	 * Recursivity for object creation {@code = 2}.
	 */
	protected static int RECURSIVITY = 2;

	/**
	 * Test array of strings.
	 */
	@SuppressWarnings("nls")
	protected final String[] strings = { "A simple test", "ïéàèäöü HJH JK iW 565675671 .:,-_", "jka ds jdahkhshda 9'3'23 {}¨!èè'?^ôîâ" };

	/**
	 * Create a {@code PrimitiveObject} containing a {@code boolean}, a
	 * {@code byte}, a {@code short}, an {@code int}, an {@code enum}, a
	 * {@code char}, a {@code long}, a {@code double}, a {@code float} and a
	 * {@link String}.
	 * <p>
	 * Each of these fields have random values except for the string.
	 * <p>
	 * @return {@link PrimitiveObject}.
	 */
	@SuppressWarnings("nls")
	protected final static PrimitiveObject createPrimitiveObject()
	{
		final PrimitiveObject data = new PrimitiveObject();

		data.setBoolean(rnd.nextBoolean());
		data.setByte((byte) rnd.nextInt(Byte.MAX_VALUE));
		data.setShort((short) rnd.nextInt(Short.MAX_VALUE));
		data.setChar((char) rnd.nextInt(Short.MAX_VALUE));
		data.setInteger(rnd.nextInt(Integer.MAX_VALUE));
		data.setEnum(MemoryType.values()[rnd.nextInt(2)]);
		data.setLong(rnd.nextLong());
		data.setDouble(rnd.nextDouble());
		data.setFloat(rnd.nextFloat());
		data.setString("A simple string with special characters ,.-ï¿½ï¿½ï¿½'90'9875642354316ï¿½%&/*'`^!ï¿½ï¿½ï¿½ï¿½ï¿½%&");

		return data;
	}

	/**
	 * Create a {@code DataObject} containing 3 objects ; 2
	 * {@link PrimitiveObject} and 1 {@link DataObject}.
	 * @param withNull Tells if embedded objects can be null or not.
	 * @param recursivity Recursivity level for object imbrication.
	 * <p>
	 * @return {@link DataObject}.
	 */
	protected final static DataObject createDataObject(final boolean withNull, final int recursivity)
	{
		final DataObject data = new DataObject();

		data.setObject1(withNull == true ? (rnd.nextBoolean() == false ? null : createPrimitiveObject()) : createPrimitiveObject());
		data.setObject2(withNull == true ? (rnd.nextBoolean() == false ? null : createPrimitiveObject()) : createPrimitiveObject());
		if (recursivity > 0)
		{
			data.setObject3(withNull == true ? (rnd.nextBoolean() == false ? null : createDataObject(withNull, recursivity - 1)) : createDataObject(withNull, recursivity - 1));
		}

		return data;
	}

	/**
	 * Create a {@code ComplexObject} containing 1 {@link PrimitiveObject}, an
	 * {@code int}, a {@code long} and a list of {@link DataObject}.
	 * @param withNull Tells if embedded objects can be null or not.
	 * <p>
	 * @return {@link ComplexObject}.
	 */
	protected final static ComplexObject createComplexObject(final boolean withNull)
	{
		final ComplexObject data = new ComplexObject();

		data.setObject(withNull == true ? (rnd.nextBoolean() == false ? null : createPrimitiveObject()) : createPrimitiveObject());
		data.setInteger(rnd.nextInt());
		data.setLong(Long.valueOf(rnd.nextLong()));

		final List<DataObject> list = new ArrayList<>(1);
		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			list.add(withNull == true ? (rnd.nextBoolean() == false ? null : createDataObject(withNull, RECURSIVITY)) : createDataObject(withNull, RECURSIVITY));
		}
		data.setList(list);

		return data;
	}

	/**
	 * Checks an exception against an expected exception key. If the key of the
	 * received exception do not match the expected exception key the related
	 * test is considered as failed.
	 * <p>
	 * @param e Exception.
	 * @param key Key of the expected exception.
	 */
	@SuppressWarnings("nls")
	protected final static void checkException(final Exception e, final Enum<? extends IExceptionType> key)
	{
		Enum<? extends IExceptionType> value = null;

		if (e instanceof AbstractCheckedException)
		{
			value = ((AbstractCheckedException) e).getKey();
		}
		if (e instanceof AbstractUncheckedException)
		{
			value = ((AbstractUncheckedException) e).getKey();
		}

		if (value != key)
		{
			Assert.fail("Expected exception: " + key.getDeclaringClass().getSimpleName() + "." + key.name());
		}
	}
}
