/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.codec.algorithm.vitex.benchmark;

import java.lang.annotation.ElementType;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.GregorianCalendar;

import org.heliosphere.drake.base.codec.DataDecodingException;
import org.heliosphere.drake.base.codec.DataEncodingException;
import org.heliosphere.drake.base.codec.serializer.KryoDataSerializer;
import org.heliosphere.drake.base.codec.type.CodecType;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.test.data.ComplexObject;
import org.heliosphere.drake.base.test.data.DataObject;
import org.heliosphere.drake.base.test.data.PrimitiveObject;
import org.heliosphere.drake.test.AbstractDrakeBenchmarkTest;
import org.heliosphere.drake.test.BenchmarkHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

/**
 * A benchamrking test case for the {@code Drake - Vitex} codec for the
 * {@link Array} collection.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodeArray")
public final class EncodeDecodeArrayTest extends AbstractDrakeBenchmarkTest
{
	/**
	 * Initialization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the initialization.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		System.setProperty(BenchmarkHelper.JUB_PROPERTY_CONSUMER, BenchmarkHelper.JUB_PROPERTY_CONSUMER_VALUE);
		System.setProperty(BenchmarkHelper.JUB_PROPERTY_DBFILE, BenchmarkHelper.JUB_PROPERTY_DBFILE_VALUE);
		System.setProperty(BenchmarkHelper.JUB_PROPERTY_CHARTDIR, BenchmarkHelper.JUB_PROPERTY_CHARTDIR_VALUE);

		BUFFER_ALLOCATION_SIZE = 4000000;
		buffer = ByteBuffer.allocate(BUFFER_ALLOCATION_SIZE);

		rnd.setSeed(new GregorianCalendar().getTimeInMillis());

		Manager.getMessageManager().setMessageCodecType(CodecType.KRYO);
		messageCodec = Manager.getMessageManager().getMessageCodec();
		dataCodec = new KryoDataSerializer();
	}

	/**
	 * Finalization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the finalization.
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		// Empty
	}

	/**
	 * Sets up the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the setup phase.
	 */
	@Before
	public void setUp() throws Exception
	{
		// Empty
	}

	/**
	 * Tears down the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the tear down phase.
	 */
	@After
	public void tearDown() throws Exception
	{
		// Empty
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Byte} objects 1'000 times
	 * and ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeByteObjectArray() throws DataEncodingException, DataDecodingException
	{
		Byte[] input = new Byte[ARRAY_SIZE];
		Byte[] output = null;

		dataCodec.getSerializer().register(Byte.class);

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = Byte.valueOf((byte) rnd.nextInt(Byte.MAX_VALUE));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Byte[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code byte}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeByteArray() throws DataEncodingException, DataDecodingException
	{
		byte[] input = new byte[ARRAY_SIZE];
		byte[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = (byte) rnd.nextInt(Byte.MAX_VALUE);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (byte[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code char}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeCharArray() throws DataEncodingException, DataDecodingException
	{
		final char[] input = new char[ARRAY_SIZE];
		char[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = (char) rnd.nextInt(Short.MAX_VALUE);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (char[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code short}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeShortArray() throws DataEncodingException, DataDecodingException
	{
		final short[] input = new short[ARRAY_SIZE];
		short[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = (short) rnd.nextInt(Short.MAX_VALUE);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (short[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Short}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeShortObjectArray() throws DataEncodingException, DataDecodingException
	{
		final Short[] input = new Short[ARRAY_SIZE];
		Short[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = Short.valueOf((short) rnd.nextInt(Short.MAX_VALUE));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Short[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code int}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeIntegerArray() throws DataEncodingException, DataDecodingException
	{
		final int[] input = new int[ARRAY_SIZE];
		int[] output = null;

		dataCodec.getSerializer().register(int[].class);

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = rnd.nextInt(Integer.MAX_VALUE);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (int[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Integer}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeIntegerObjectArray() throws DataEncodingException, DataDecodingException
	{
		final Integer[] input = new Integer[ARRAY_SIZE];
		Integer[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = Integer.valueOf(rnd.nextInt(Integer.MAX_VALUE));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Integer[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code long}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeLongArray() throws DataEncodingException, DataDecodingException
	{
		final long[] input = new long[ARRAY_SIZE];
		long[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = rnd.nextLong();
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (long[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Long}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeLongObjectArray() throws DataEncodingException, DataDecodingException
	{
		final Long[] input = new Long[ARRAY_SIZE];
		Long[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = Long.valueOf(rnd.nextLong());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Long[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code float}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeFloatArray() throws DataEncodingException, DataDecodingException
	{
		final float[] input = new float[ARRAY_SIZE];
		float[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = rnd.nextFloat();
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (float[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input, 0.0f);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Float}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeFloatObjectArray() throws DataEncodingException, DataDecodingException
	{
		final Float[] input = new Float[ARRAY_SIZE];
		Float[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = Float.valueOf(rnd.nextFloat());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Float[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code double}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDoubleArray() throws DataEncodingException, DataDecodingException
	{
		final double[] input = new double[ARRAY_SIZE];
		double[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = rnd.nextDouble();
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (double[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input, 0.0d);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Double}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDoubleObjectArray() throws DataEncodingException, DataDecodingException
	{
		final Double[] input = new Double[ARRAY_SIZE];
		Double[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = Double.valueOf(rnd.nextDouble());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Double[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code Enum}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeEnumObjectArray() throws DataEncodingException, DataDecodingException
	{
		final ElementType[] input = (ElementType[]) Array.newInstance(ElementType.class, ARRAY_SIZE);
		ElementType[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = ElementType.values()[rnd.nextInt(7)];
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (ElementType[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code String}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeStringArray() throws DataEncodingException, DataDecodingException
	{
		final String[] input = new String[ARRAY_SIZE];
		String[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = strings[rnd.nextInt(2)];
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (String[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 1'000 {@code null}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeArrayWithNull() throws DataEncodingException, DataDecodingException
	{
		final Integer[] input = new Integer[ARRAY_SIZE];
		Integer[] output = null;

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Integer[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes an array of 10 {@code PrimitiveObject} 1'000 times
	 * and ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodePrimitiveObjectArray() throws DataEncodingException, DataDecodingException
	{
		PrimitiveObject[] input = new PrimitiveObject[10];
		PrimitiveObject[] output = null;

		for (int i = 0; i < 10; i++)
		{
			input[i] = createPrimitiveObject();
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (PrimitiveObject[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes an array of 10 {@code DataObject} 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDataObjectArray() throws DataEncodingException, DataDecodingException
	{
		DataObject[] input = new DataObject[10];
		DataObject[] output = null;

		for (int i = 0; i < 10; i++)
		{
			input[i] = createDataObject(false, RECURSIVITY);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (DataObject[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes an array of 10 {@code ComplexObject} 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeComplexObjectArray() throws DataEncodingException, DataDecodingException
	{
		ComplexObject[] input = new ComplexObject[10];
		ComplexObject[] output = null;

		for (int i = 0; i < 10; i++)
		{
			input[i] = createComplexObject(false);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (ComplexObject[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
	}
}