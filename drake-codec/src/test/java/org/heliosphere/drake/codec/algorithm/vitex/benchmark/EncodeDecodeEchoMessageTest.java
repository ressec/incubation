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

import java.nio.ByteBuffer;
import java.util.GregorianCalendar;

import org.heliosphere.drake.base.codec.serializer.KryoDataSerializer;
import org.heliosphere.drake.base.codec.type.CodecType;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.codec.MessageEncodingException;
import org.heliosphere.drake.base.message.manager.IMessageManager;
import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.heliosphere.drake.base.test.message.DrakeMessageType;
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
 * A benchmark test case to encode and decode
 * {@link DrakeMessageType#EchoMessage} using the {@link CodecType#PROCESSOR} to
 * encode/decode messages and the {@link DrakeCodecAlgorithmType#VITEX} codec to
 * encode/decode message data.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EncodeDecodeEchoMessage")
public final class EncodeDecodeEchoMessageTest extends AbstractDrakeBenchmarkTest
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
	 * Encodes and decodes {@link DrakeMessageType#EchoMessage}s 100'000 times
	 * and ensure that the decoded message is equal to the initial one.
	 * @throws MessageManagerException Thrown if an error occured while creating
	 * a new message.
	 * @throws MessageEncodingException Thrown if an error occured while
	 * encoding
	 * a message.
	 * @throws MessageDecodingException Thrown if an error occured while
	 * decoding
	 * a message.
	 */
	@SuppressWarnings({ "static-method", "nls" })
	@BenchmarkOptions(benchmarkRounds = 2, warmupRounds = 1)
	@Test
	public void encodeDecodeEchoMessage() throws MessageManagerException, MessageEncodingException, MessageDecodingException
	{
		IMessage input = null;
		IMessage output = null;

		IMessageManager manager = Manager.getManager(IMessageManager.class);

		input = manager.createMessage(DrakeMessageType.EchoMessage);
		input.setContent("A simple echo message!");

		for (int i = 0; i < ITERATIONS_100K; i++)
		{
			buffer.clear();
			buffer = messageCodec.encodeMessage(input);
			output = messageCodec.decodeMessage(buffer);

			Assert.assertEquals(input, output);
		}
	}
}