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
package org.heliosphere.drake.base.message.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.Message;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.codec.MessageEncodingException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * A {@code Kryo} serializer for message encoding and decoding.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public final class KryoMessageSerializer implements IMessageCodec
{
	/**
	 * Byte array input stream.
	 */
	private ByteArrayInputStream bais;

	/**
	 * Byte array output stream.
	 */
	private ByteArrayOutputStream baos;

	//	/**
	//	 * Object input stream.
	//	 */
	//	private ObjectInputStream ois;

	/**
	 * Array of bytes.
	 */
	private byte[] bytes;

	/**
	 * Output stream.
	 */
	Output output;

	/**
	 * Input stream.
	 */
	Input input;

	/**
	 * Kryo object.
	 */
	private Kryo kryo;

	/**
	 * Creates a new Kryo serializer.
	 */
	public KryoMessageSerializer()
	{
		kryo = new Kryo();
	}

	@SuppressWarnings("nls")
	@Override
	public IMessage decodeMessage(final ByteBuffer encoded) throws MessageDecodingException
	{
		IMessage message = null;

		try
		{
			bytes = new byte[encoded.remaining()];
			encoded.get(bytes, 0, bytes.length);

			bais = new ByteArrayInputStream(bytes);
			input = new Input(bais);

			message = kryo.readObject(input, Message.class);
		}
		catch (final Exception e)
		{
			log.error(e);
			throw new MessageDecodingException("Cannot decode message due to: " + e.getMessage());
		}
		finally
		{
			try
			{
				input.close();
				bais.close();
			}
			catch (final IOException ioe)
			{
				// Do nothing.
			}
		}

		return message;
	}

	@SuppressWarnings("nls")
	@Override
	public ByteBuffer encodeMessage(final IMessage message) throws MessageEncodingException
	{
		try
		{
			baos = new ByteArrayOutputStream();
			final DataOutputStream data = new DataOutputStream(baos);
			output = new Output(data);
			kryo.writeObject(output, message);
		}
		catch (final Exception e)
		{
			log.error(e);
			throw new MessageEncodingException("Cannot encode message due to: " + e.getMessage());
		}

		return ByteBuffer.wrap(output.toBytes());
	}
}
