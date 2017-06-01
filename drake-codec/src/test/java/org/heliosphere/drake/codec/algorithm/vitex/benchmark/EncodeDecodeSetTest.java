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
import java.util.HashSet;
import java.util.Set;

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
 * encoding/decoding collections of type {@link Set}.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodeSet")
public final class EncodeDecodeSetTest extends AbstractDrakeBenchmarkTest
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
	 * Encodes then decodes a set of 127 {@code Byte} objects 1'000 times and
	 * ensure that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeByteSet() throws DataEncodingException, DataDecodingException
	{
		Set<Byte> input = new HashSet<>(Byte.MAX_VALUE);
		Set<Byte> output = null;

		for (int i = 0; i < Byte.MAX_VALUE; i++)
		{
			input.add(Byte.valueOf((byte) i));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Byte>) dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
	}

	/**
	 * Encodes then decodes a set of 1'000 {@code Character}s 1'000 times and
	 * ensure that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeCharSet() throws DataEncodingException, DataDecodingException
	{
		final Set<Character> input = new HashSet<>(ARRAY_SIZE);
		Set<Character> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Character.valueOf((char) i));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Character>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 1'000 {@code Short}s 1'000 times and ensure
	 * that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeShortSet() throws DataEncodingException, DataDecodingException
	{
		final Set<Short> input = new HashSet<>(ARRAY_SIZE);
		Set<Short> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Short.valueOf((short) i));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Short>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 1'000 {@code Integer}s 1'000 times and
	 * ensure that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeIntegerSet() throws DataEncodingException, DataDecodingException
	{
		final Set<Integer> input = new HashSet<>(ARRAY_SIZE);
		Set<Integer> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Integer.valueOf(i));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Integer>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 1'000 {@code Long}s 1'000 times and ensure
	 * that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeLongSet() throws DataEncodingException, DataDecodingException
	{
		final Set<Long> input = new HashSet<>(ARRAY_SIZE);
		Set<Long> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Long.valueOf(i));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Long>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 1'000 {@code Float}s 1'000 times and ensure
	 * that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeFloatSet() throws DataEncodingException, DataDecodingException
	{
		final Set<Float> input = new HashSet<>(ARRAY_SIZE);
		Set<Float> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Float.valueOf(rnd.nextFloat()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Float>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 1'000 {@code Double}s 1'000 times and
	 * ensure that the decoded array is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDoubleSet() throws DataEncodingException, DataDecodingException
	{
		final Set<Double> input = new HashSet<>(ARRAY_SIZE);
		Set<Double> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(Double.valueOf(rnd.nextDouble()));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<Double>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of {@code Enum}s 1'000 times and ensure
	 * that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeEnumSet() throws DataEncodingException, DataDecodingException
	{
		final Set<ElementType> input = new HashSet<>();
		Set<ElementType> output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(ElementType.values()[rnd.nextInt(ElementType.values().length)]);
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<ElementType>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of {@code String}s 1'000 times and ensure
	 * that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeStringList() throws DataEncodingException, DataDecodingException
	{
		final Set<String> input = new HashSet<>();
		Set<String> output = null;

		int MAX_VALUE = ElementType.values().length;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input.add(ElementType.values()[rnd.nextInt(MAX_VALUE)].name());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<String>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 10 {@code PrimitiveObject}s 1'000 times and
	 * ensure that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodePrimitiveObjectSet() throws DataEncodingException, DataDecodingException
	{
		final Set<PrimitiveObject> input = new HashSet<>();
		Set<PrimitiveObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.add(createPrimitiveObject());
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<PrimitiveObject>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 10 {@code DataObject}s 1'000 times and
	 * ensure that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDataObjectSet() throws DataEncodingException, DataDecodingException
	{
		final Set<DataObject> input = new HashSet<>();
		Set<DataObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.add(createDataObject(false, RECURSIVITY));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<DataObject>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}

	/**
	 * Encodes then decodes a set of 10 {@code ComplexObject}s 1'000 times and
	 * ensure that the decoded set is equal to the initial one.
	 * @throws DataEncodingException Thrown if an error occured while encoding
	 * data.
	 * @throws DataDecodingException Thrown if an error occured while decoding
	 * data.
	 */
	@SuppressWarnings({ "static-method", "unchecked" })
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeComplexObjectSet() throws DataEncodingException, DataDecodingException
	{
		final Set<ComplexObject> input = new HashSet<>();
		Set<ComplexObject> output = null;

		for (int i = 0; i < 10; i++)
		{
			input.add(createComplexObject(false));
		}

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			buffer.clear();
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (Set<ComplexObject>) dataCodec.decodeData(buffer);

			Assert.assertEquals(output, input);
		}
	}
}