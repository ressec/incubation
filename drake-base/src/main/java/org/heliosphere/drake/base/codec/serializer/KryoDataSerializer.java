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
package org.heliosphere.drake.base.codec.serializer;

import java.nio.ByteBuffer;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.codec.DataDecodingException;
import org.heliosphere.drake.base.codec.DataEncodingException;
import org.heliosphere.drake.base.codec.ICodec;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * A {@code Kryo} serializer for data encoding and decoding.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public final class KryoDataSerializer implements ICodec
{
	/**
	 * Default buffer allocation size.
	 */
	protected static int BUFFER_ALLOCATION_SIZE = 32000;

	/**
	 * Output stream.
	 */
	private Output output = new Output(BUFFER_ALLOCATION_SIZE);

	/**
	 * Input stream.
	 */
	private Input input = new Input(BUFFER_ALLOCATION_SIZE);

	/**
	 * Kryo object.
	 */
	private Kryo kryo;

	/**
	 * Creates a new Kryo serializer.
	 */
	public KryoDataSerializer()
	{
		kryo = new Kryo();
	}

	@SuppressWarnings("nls")
	@Override
	public final Object decodeData(final ByteBuffer encoded) throws DataDecodingException
	{
		Object data = null;

		try
		{
			input.setBuffer(encoded.array());
			data = kryo.readClassAndObject(input);
			input.close();
		}
		catch (final Exception e)
		{
			log.error(e);
			throw new DataDecodingException("Cannot decode data due to: " + e.getMessage());
		}

		return data;
	}

	@SuppressWarnings("nls")
	@Override
	public final ByteBuffer encodeData(final Object data) throws DataEncodingException
	{
		try
		{
			output.clear();
			kryo.writeClassAndObject(output, data);
		}
		catch (final Exception e)
		{
			log.error(e);
			throw new DataEncodingException("Cannot encode data due to: " + e.getMessage());
		}

		return ByteBuffer.wrap(output.toBytes());
	}

	@Override
	public final Kryo getSerializer()
	{
		return kryo;
	}
}
