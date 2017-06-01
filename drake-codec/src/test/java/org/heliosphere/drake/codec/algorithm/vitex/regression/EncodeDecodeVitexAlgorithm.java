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
package org.heliosphere.drake.codec.algorithm.vitex.regression;

import java.lang.management.MemoryType;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.heliosphere.drake.base.codec.serializer.KryoDataSerializer;
import org.heliosphere.drake.base.codec.type.CodecType;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.manager.IMessageManager;
import org.heliosphere.drake.base.test.data.ComplexObject;
import org.heliosphere.drake.base.test.data.DataObject;
import org.heliosphere.drake.base.test.data.PrimitiveObject;
import org.heliosphere.drake.base.test.message.DrakeMessageType;
import org.heliosphere.drake.test.AbstractDrakeTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A regression test case for the {@code Drake - Vitex} codec.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class EncodeDecodeVitexAlgorithm extends AbstractDrakeTest
{
	/**
	 * Initialization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the initialization.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
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
		buffer = ByteBuffer.allocate(400000);
	}

	/**
	 * Tears down the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the tear down phase.
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Encodes and decodes a {@link Byte} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeByte()
	{
		final Byte input = Byte.valueOf((byte) rnd.nextInt(Byte.MAX_VALUE));
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}

		Assert.assertEquals(input, output);
	}

	/**
	 * Encodes and decodes a {@link Short} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeShort()
	{
		final Short input = Short.valueOf((byte) rnd.nextInt(Short.MAX_VALUE));
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Integer} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeInteger()
	{
		final Integer input = Integer.valueOf((byte) rnd.nextInt(Integer.MAX_VALUE));
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Boolean} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeBoolean()
	{
		final Boolean input = Boolean.valueOf(rnd.nextBoolean());
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Enum} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeEnum()
	{
		final MemoryType input = MemoryType.values()[rnd.nextInt(2)];
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Long} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeLong()
	{
		final Long input = Long.valueOf((byte) rnd.nextLong());
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Double} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeDouble()
	{
		final Double input = Double.valueOf((byte) rnd.nextDouble());
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Float} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeFloat()
	{
		final Float input = Float.valueOf((byte) rnd.nextFloat());
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Character} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeCharacter()
	{
		final Character input = new Character((char) rnd.nextInt(Short.MAX_VALUE));
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link String} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	@Test
	public void encodeDecodeString()
	{
		final String input = new String("sahjkha5647357434_:,..-ïéàèôî");
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link DrakeMessageType#ErrorMessage} and ensure
	 * that the decoded message is equal to the initial one.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	@Test
	public void encodeDecodeMessage()
	{
		Object output = null;

		IMessageManager manager = Manager.getManager(IMessageManager.class);

		try
		{
			manager.registerMessage(DrakeMessageType.class);

			IMessage input = manager.createMessage(DrakeMessageType.ErrorMessage);
			input.setContent("Unable to decode message");

			buffer = messageCodec.encodeMessage(input);
			output = messageCodec.decodeMessage(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link PrimitiveObject} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodePrimitiveObject()
	{
		final PrimitiveObject input = createPrimitiveObject();
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link DataObject} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeDataObject()
	{
		final DataObject input = createDataObject(true, RECURSIVITY);
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link ComplexObject} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeComplexObject()
	{
		final ComplexObject input = createComplexObject(true);
		Object output = null;

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link Byte} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListByte()
	{
		final List<Byte> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? Byte.valueOf(Integer.valueOf(rnd.nextInt()).byteValue()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link Short} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListShort()
	{
		final List<Short> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? Short.valueOf(Integer.valueOf(rnd.nextInt()).shortValue()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link Integer} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListInteger()
	{
		final List<Integer> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? Integer.valueOf(rnd.nextInt()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link Long} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListLong()
	{
		final List<Long> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? Long.valueOf(rnd.nextLong()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link Double} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListDouble()
	{
		final List<Double> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? Double.valueOf(rnd.nextDouble()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link Float} and ensure that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListFloat()
	{
		final List<Float> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? Float.valueOf(rnd.nextFloat()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link PrimitiveObject} and ensure
	 * that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListPrimitiveObject()
	{
		final List<PrimitiveObject> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? createPrimitiveObject() : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link DataObject} and ensure that
	 * the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListDataObject()
	{
		final List<DataObject> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? createDataObject(true, RECURSIVITY) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link List} of {@link ComplexObject} and ensure
	 * that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeListComplexObject()
	{
		final List<ComplexObject> input = new ArrayList<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(rnd.nextBoolean() == true ? createComplexObject(true) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link Integer} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetInteger()
	{
		final Set<Integer> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}

			input.add(Integer.valueOf(rnd.nextInt()));
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link Long} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetLong()
	{
		final Set<Long> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}

			input.add(Long.valueOf(rnd.nextLong()));
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link Double} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetDouble()
	{
		final Set<Double> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}

			input.add(Double.valueOf(rnd.nextDouble()));
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link Float} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetFloat()
	{
		final Set<Float> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}

			input.add(Float.valueOf(rnd.nextFloat()));
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link PrimitiveObject} nd ensure
	 * that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetPrimitiveObject()
	{
		final Set<PrimitiveObject> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false; // only one null per set!

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}
			input.add(createPrimitiveObject());
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link DataObject} and ensure that
	 * the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetDataObject()
	{
		final Set<DataObject> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}
			input.add(createDataObject(true, RECURSIVITY));
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Set} of {@link ComplexObject} and ensure
	 * that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeSetComplexObject()
	{
		final Set<ComplexObject> input = new HashSet<>();
		Object output = null;
		boolean nullValue = false;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			if (!nullValue && rnd.nextBoolean() == true)
			{
				input.add(null);
				nullValue = true;
			}
			input.add(createComplexObject(true));
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}

		Assert.assertEquals(input, output);
	}

	/**
	 * Encodes and decodes a {@link Deque} of {@link Integer} and ensure that
	 * the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings({ "unchecked", "static-method" })
	@Test
	public void encodeDecodeDequeInteger()
	{
		final Deque<Integer> input = new ArrayDeque<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(Integer.valueOf(rnd.nextInt())); // No nulls for Deque
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Object[] o = input.toArray();
			for (Object e : o)
			{
				Assert.assertEquals(e, ((Deque<Integer>) output).pollFirst());
			}
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Deque} of {@link PrimitiveObject} and ensure
	 * that the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings({ "unchecked", "static-method" })
	@Test
	public void encodeDecodeDequePrimitiveObject()
	{
		final Deque<PrimitiveObject> input = new ArrayDeque<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(createPrimitiveObject()); // No nulls in Deque
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Object[] o = input.toArray();
			for (Object e : o)
			{
				Assert.assertEquals(e, ((Deque<PrimitiveObject>) output).pollFirst());
			}
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Deque} of {@link DataObject} and ensure that
	 * the
	 * decoded data is equal to the initial one.
	 */
	@SuppressWarnings({ "unchecked", "static-method" })
	@Test
	public void encodeDecodeDequeDataObject()
	{
		final Deque<DataObject> input = new ArrayDeque<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(createDataObject(false, RECURSIVITY)); // No nulls in Deque
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Object[] o = input.toArray();
			for (Object e : o)
			{
				Assert.assertEquals(e, ((Deque<DataObject>) output).pollFirst());
			}
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Deque} of {@link ComplexObject} and ensure
	 * that the decoded data is equal to the initial one.
	 */
	@SuppressWarnings({ "unchecked", "static-method" })
	@Test
	public void encodeDecodeDequeComplexObject()
	{
		final Deque<ComplexObject> input = new ArrayDeque<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.add(createComplexObject(false)); // No nulls in Deque
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Object[] o = input.toArray();
			for (Object e : o)
			{
				Assert.assertEquals(e, ((Deque<ComplexObject>) output).pollFirst());
			}
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link Integer} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapInteger()
	{
		final Map<Integer, Integer> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? Integer.valueOf(rnd.nextInt()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link Long} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapLong()
	{
		final Map<Integer, Long> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? Long.valueOf(rnd.nextLong()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link Double} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapDouble()
	{
		final Map<Integer, Double> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? Double.valueOf(rnd.nextDouble()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link Float} and ensure that the
	 * decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapFloat()
	{
		final Map<Integer, Float> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? Float.valueOf(rnd.nextFloat()) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link PrimitiveObject} and ensure
	 * that the decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapPrimitiveObject()
	{
		final Map<Integer, PrimitiveObject> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? createPrimitiveObject() : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link DataObject} and ensure that
	 * the decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapDataObject()
	{
		final Map<Integer, DataObject> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? createDataObject(true, RECURSIVITY) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes a {@link Map} of {@link ComplexObject} and ensure
	 * that the decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeMapComplexObject()
	{
		final Map<Integer, ComplexObject> input = new HashMap<>();
		Object output = null;

		for (int i = 0; i < COLLECTION_SIZE; i++)
		{
			input.put(Integer.valueOf(i), rnd.nextBoolean() == true ? createComplexObject(true) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = dataCodec.decodeData(buffer);

			Assert.assertEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Array} of {@link PrimitiveObject} and
	 * ensure that the decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeArrayPrimitiveObject()
	{
		final PrimitiveObject[] input = new PrimitiveObject[ARRAY_SIZE];
		PrimitiveObject[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = (rnd.nextBoolean() == true ? createPrimitiveObject() : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (PrimitiveObject[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Array} of {@link DataObject} and ensure
	 * that the decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeArrayDataObject()
	{
		final DataObject[] input = new DataObject[ARRAY_SIZE];
		DataObject[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = (rnd.nextBoolean() == true ? createDataObject(true, RECURSIVITY) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (DataObject[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Encodes and decodes an {@link Array} of {@link ComplexObject} and ensure
	 * that the decoded data is equal to the initial one.
	 * <p>
	 * {@code Null} objects are also tested.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void encodeDecodeArrayComplexObject()
	{
		final ComplexObject[] input = new ComplexObject[ARRAY_SIZE];
		ComplexObject[] output = null;

		for (int i = 0; i < ARRAY_SIZE; i++)
		{
			input[i] = (rnd.nextBoolean() == true ? createComplexObject(true) : null);
		}

		try
		{
			buffer = dataCodec.encodeData(input);
			buffer.rewind();
			output = (ComplexObject[]) dataCodec.decodeData(buffer);

			Assert.assertArrayEquals(input, output);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
}