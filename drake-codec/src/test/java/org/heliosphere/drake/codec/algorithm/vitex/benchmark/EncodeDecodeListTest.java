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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
 * A benchmarking test case using the {@link CodecType#KRYO} codec for
 * encoding/decoding collections of type {@link List}.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodeList")
public final class EncodeDecodeListTest extends AbstractDrakeBenchmarkTest
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
	 * Encodes then decodes a list of {@code Byte} objects 1'000 times and
	 * ensure that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeByteList() throws DataEncodingException, DataDecodingException
	{
		List<Byte> input = new ArrayList<>(ARRAY_SIZE);
		List<Byte> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Byte.valueOf((byte) rnd.nextInt(Byte.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Byte>) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Character}s 1'000 times and ensure
	 * that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeCharList() throws DataEncodingException, DataDecodingException
	{
		final List<Character> input = new ArrayList<>(ARRAY_SIZE);
		List<Character> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Character.valueOf((char) rnd.nextInt(Short.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Character>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Short}s 1'000 times and ensure that
	 * the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeShortList() throws DataEncodingException, DataDecodingException
	{
		final List<Short> input = new ArrayList<>();
		List<Short> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Short.valueOf((short) rnd.nextInt(Short.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Short>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Integer}s 1'000 times and ensure
	 * that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeIntegerList() throws DataEncodingException, DataDecodingException
	{
		final List<Integer> input = new ArrayList<>();
		List<Integer> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Integer.valueOf(rnd.nextInt(Integer.MAX_VALUE)));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Integer>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Long}s 1'000 times and ensure that
	 * the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeLongList() throws DataEncodingException, DataDecodingException
	{
		final List<Long> input = new ArrayList<>();
		List<Long> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Long.valueOf(rnd.nextLong()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Long>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Float}s 1'000 times and ensure that
	 * the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeFloatList() throws DataEncodingException, DataDecodingException
	{
		final List<Float> input = new ArrayList<>();
		List<Float> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Float.valueOf(rnd.nextFloat()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Float>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Double}s 1'000 times and ensure
	 * that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDoubleList() throws DataEncodingException, DataDecodingException
	{
		final List<Double> input = new ArrayList<>();
		List<Double> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Double.valueOf(rnd.nextDouble()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Double>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code Enum}s 1'000 times and ensure that
	 * the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeEnumList() throws DataEncodingException, DataDecodingException
	{
		final List<ElementType> input = new ArrayList<>();
		List<ElementType> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(ElementType.values()[ElementType.values().length]);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<ElementType>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code String}s 1'000 times and ensure
	 * that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings("unchecked")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeStringList() throws DataEncodingException, DataDecodingException
	{
		final List<String> input = new ArrayList<>();
		List<String> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(strings[rnd.nextInt(2)]);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<String>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of {@code null}s 1'000 times and ensure that
	 * the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeNullList() throws DataEncodingException, DataDecodingException
	{
		final List<Integer> input = new ArrayList<>();
		List<Integer> output = null;

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<Integer>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of 10 {@code PrimitiveObject}s 1'000 times
	 * and ensure that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodePrimitiveObjectList() throws DataEncodingException, DataDecodingException
	{
		final List<PrimitiveObject> input = new ArrayList<>();
		List<PrimitiveObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.add(createPrimitiveObject());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<PrimitiveObject>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of 10 {@code DataObject}s 1'000 times and
	 * ensure that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDataObjectList() throws DataEncodingException, DataDecodingException
	{
		final List<DataObject> input = new ArrayList<>();
		List<DataObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.add(createDataObject(false, RECURSIVITY));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<DataObject>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a list of 10 {@code ComplexObject}s 1'000 times and
	 * ensure that the decoded list is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeComplexObjectList() throws DataEncodingException, DataDecodingException
	{
		final List<ComplexObject> input = new ArrayList<>();
		List<ComplexObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.add(createComplexObject(false));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (List<ComplexObject>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}
}