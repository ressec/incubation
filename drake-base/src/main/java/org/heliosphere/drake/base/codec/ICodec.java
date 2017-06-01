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
package org.heliosphere.drake.base.codec;

import java.nio.ByteBuffer;

import com.esotericsoftware.kryo.Kryo;

/**
 * Interface for encoding and decoding data.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICodec
{
	/**
	 * Returns the Kryo underlying serializer.
	 * <p>
	 * @return {@link Kryo}.
	 */
	Kryo getSerializer();

	/**
	 * Decodes data.
	 * <p>
	 * @param encoded {@link ByteBuffer} containing the encoded form of the
	 * data to decode.
	 * @return Decoded data.
	 * @throws DataDecodingException Thrown if an error occurs while trying
	 * to decode the data.
	 */
	Object decodeData(final ByteBuffer encoded) throws DataDecodingException;

	/**
	 * Encodes data.
	 * <p>
	 * @param data to encode.
	 * @return A {@link ByteBuffer} containing the encoded form of the data.
	 * @throws DataEncodingException Thrown if an error occurs while trying
	 * to encode the data.
	 */
	ByteBuffer encodeData(final Object data) throws DataEncodingException;
}