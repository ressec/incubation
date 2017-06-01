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
import java.nio.ByteBuffer;
import java.util.GregorianCalendar;

import org.heliosphere.drake.base.codec.DataDecodingException;
import org.heliosphere.drake.base.codec.DataEncodingException;
import org.heliosphere.drake.base.codec.serializer.KryoDataSerializer;
import org.heliosphere.drake.base.codec.type.CodecType;
import org.heliosphere.drake.base.manager.Manager;
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
 * A benchmarking test case using the {@link CodecType#KRYO} codec for
 * encoding/decoding primitives.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodePrimitive")
public final class EncodeDecodePrimitiveTest extends AbstractDrakeBenchmarkTest
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
	 * Encodes and decodes random {@code Byte}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeByte() throws DataEncodingException, DataDecodingException
	{
		Byte input = null;
		Byte output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Byte.valueOf((byte) rnd.nextInt(Byte.MAX_VALUE));
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Byte) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Boolean}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeBoolean() throws DataEncodingException, DataDecodingException
	{
		Boolean input = null;
		Boolean output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Boolean.valueOf(rnd.nextBoolean());
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Boolean) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Short}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeShort() throws DataEncodingException, DataDecodingException
	{
		Short input = null;
		Short output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Short.valueOf((short) rnd.nextInt(Short.MAX_VALUE));
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Short) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Integer}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeInteger() throws DataEncodingException, DataDecodingException
	{
		Integer input = null;
		Integer output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Integer.valueOf(rnd.nextInt(Integer.MAX_VALUE));
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Integer) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Long}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeLong() throws DataEncodingException, DataDecodingException
	{
		Long input = null;
		Long output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Long.valueOf(rnd.nextLong());
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Long) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Double}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeDouble() throws DataEncodingException, DataDecodingException
	{
		Double input = null;
		Double output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Double.valueOf(rnd.nextDouble());
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Double) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Float}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeFloat() throws DataEncodingException, DataDecodingException
	{
		Float input = null;
		Float output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = Float.valueOf(rnd.nextFloat());
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Float) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code Enum}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeEnum() throws DataEncodingException, DataDecodingException
	{
		ElementType input = null;
		ElementType output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = ElementType.values()[ElementType.values().length];
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (ElementType) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes and decodes random {@code String}s 100'000 times and ensure that
	 * the decoded value is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeString() throws DataEncodingException, DataDecodingException
	{
		String input = null;
		String output = null;

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			input = ElementType.values()[ElementType.values().length].name();
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (String) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}
}