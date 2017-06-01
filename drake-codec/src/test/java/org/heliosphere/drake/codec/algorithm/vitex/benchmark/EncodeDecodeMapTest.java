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

import static org.junit.Assert.fail;

import java.lang.annotation.ElementType;
import java.nio.ByteBuffer;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
 * A benchmarking test case using the {@link CodecType#KRYO} codec for
 * encoding/decoding collections of type {@link Map}.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodeMap")
public final class EncodeDecodeMapTest extends AbstractDrakeBenchmarkTest
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
	 * Encodes then decodes a map of {@code Byte} objects 1'000 times and ensure
	 * that the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeByteMap()
	{
		Map<Integer, Byte> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Byte> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Byte.valueOf((byte) rnd.nextInt(Byte.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Byte>) dataCodec.decodeData(buffer);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes a map of {@code Character}s 1'000 times and ensure
	 * that the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeCharMap()
	{
		Map<Integer, Character> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Character> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Character.valueOf((char) rnd.nextInt(Short.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Character>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code Short}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeShortMap()
	{
		Map<Integer, Short> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Short> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Short.valueOf((short) rnd.nextInt(Short.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Short>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code Integer}s 1'000 times and ensure
	 * that the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeIntegerMap()
	{
		Map<Integer, Integer> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Integer> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Integer.valueOf(rnd.nextInt(Integer.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Integer>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code Long}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeLongMap()
	{
		Map<Integer, Long> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Long> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Long.valueOf(rnd.nextLong()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Long>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code Float}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeFloatMap()
	{
		Map<Integer, Float> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Float> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Float.valueOf(rnd.nextFloat()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Float>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code Double}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDoubleMap()
	{
		Map<Integer, Double> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Double> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), Double.valueOf(rnd.nextDouble()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Double>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code Enum}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeEnumMap()
	{
		Map<Integer, ElementType> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, ElementType> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), ElementType.values()[ElementType.values().length]);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, ElementType>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code String}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings("unchecked")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeStringMap()
	{
		Map<Integer, String> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, String> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.put(Integer.valueOf(i), strings[rnd.nextInt(2)]);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, String>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of {@code null}s 1'000 times and ensure that
	 * the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeMapWithNull()
	{
		Map<Integer, Integer> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, Integer> output = null;

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, Integer>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of 10 {@code PrimitiveObject}s 1'000 times and
	 * ensure that the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodePrimitiveObjectMap()
	{
		Map<Integer, PrimitiveObject> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, PrimitiveObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.put(Integer.valueOf(i), createPrimitiveObject());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, PrimitiveObject>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of 10 {@code DataObject}s 1'000 times and
	 * ensure that the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDataObjectMap()
	{
		Map<Integer, DataObject> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, DataObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.put(Integer.valueOf(i), createDataObject(false, RECURSIVITY));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, DataObject>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Encodes then decodes a map of 10 {@code ComplexObject}s 1'000 times and
	 * ensure that the decoded map is equal to the initial one.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeComplexObjectMap()
	{
		Map<Integer, ComplexObject> input = new HashMap<>(ARRAY_SIZE);
		Map<Integer, ComplexObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.put(Integer.valueOf(i), createComplexObject(false));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (Map<Integer, ComplexObject>) dataCodec.decodeData(buffer);

				Assert.assertEquals(output, input);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}
		}
	}
}