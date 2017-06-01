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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.codec.MessageEncodingException;

/**
 * A basic Java serializer for message encoding and decoding using Java built-in
 * serialization mechanism without any special optimization. This serializer has
 * been designed for fast building purpose but is not efficient in a production
 * environment.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public final class JavaMessageSerializer implements IMessageCodec
{
	/**
	 * Byte array input stream.
	 */
	private ByteArrayInputStream bais;

	/**
	 * Byte array output stream.
	 */
	private ByteArrayOutputStream baos;

	/**
	 * Object input stream.
	 */
	private ObjectInputStream ois;

	/**
	 * Array of bytes.
	 */
	private byte[] bytes;

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
			ois = new ObjectInputStream(bais);

			message = (IMessage) ois.readObject();
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
				bais.close();
			}
			catch (final IOException ioe)
			{
				log.error(ioe);
				throw new MessageDecodingException("Cannot decode message due to: " + ioe.getMessage());
			}
			try
			{
				ois.close();
			}
			catch (final IOException ioe)
			{
				log.error(ioe);
				throw new MessageDecodingException("Cannot decode message due to: " + ioe.getMessage());
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
			new ObjectOutputStream(data).writeObject(message);
		}
		catch (final Exception e)
		{
			log.error(e);
			throw new MessageEncodingException("Cannot encode message due to: " + e.getMessage());
		}

		return ByteBuffer.wrap(baos.toByteArray());
	}
}
