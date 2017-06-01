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

import java.nio.ByteBuffer;
import java.util.GregorianCalendar;

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
 * A benchamrking test case for the {@code Drake - Vitex} codec for objects.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodeObject")
public final class EncodeDecodeObjectTest extends AbstractDrakeBenchmarkTest
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

		buffer = ByteBuffer.allocate(BUFFER_ALLOCATION_SIZE);

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
	@SuppressWarnings("static-method")
	@Before
	public void setUp() throws Exception
	{
		rnd.setSeed(new GregorianCalendar().getTimeInMillis());
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
	 * Encodes then decodes a {@link PrimitiveObject} 1'000 times and ensure
	 * that the decoded object is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodePrimitiveObject()
	{
		final PrimitiveObject input = createPrimitiveObject();
		PrimitiveObject output = null;

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (PrimitiveObject) dataCodec.decodeData(buffer);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}

			Assert.assertTrue(input.equals(output));
		}
	}

	/**
	 * Encodes then decodes a {@link DataObject} 1'000 times and ensure that the
	 * decoded object is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeDataObject()
	{
		final DataObject input = createDataObject(false, RECURSIVITY);
		DataObject output = null;

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (DataObject) dataCodec.decodeData(buffer);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}

			Assert.assertTrue(input.equals(output));
		}
	}

	/**
	 * Encodes then decodes a {@link ComplexObject} 1'000 times and ensure that
	 * the decoded object is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@BenchmarkOptions(benchmarkRounds = 3, warmupRounds = 1)
	@Test
	public void encodeDecodeComplexObject()
	{
		final ComplexObject input = createComplexObject(false);
		ComplexObject output = null;

		for (int i = 0; i < ITERATIONS_1K; i++)
		{
			try
			{
				buffer.clear();
				buffer = dataCodec.encodeData(input);
				buffer.rewind();
				output = (ComplexObject) dataCodec.decodeData(buffer);
			}
			catch (final Exception e)
			{
				fail(e.getMessage());
			}

			Assert.assertTrue(input.equals(output));
		}
	}
}