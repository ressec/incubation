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
package org.heliosphere.drake.base.message.codec;

import java.nio.ByteBuffer;

import org.heliosphere.drake.base.message.IMessage;

/**
 * Interface for encoding and decoding data.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageCodec
{
	/**
	 * Decodes a message.
	 * <p>
	 * @param encoded {@link ByteBuffer} containing the encoded form of the
	 * message to decode.
	 * @return Decoded {@link IMessage}.
	 * @throws MessageDecodingException Thrown if an error occurs while trying
	 * to decode the message.
	 */
	IMessage decodeMessage(final ByteBuffer encoded) throws MessageDecodingException;

	/**
	 * Encodes a message.
	 * <p>
	 * @param message {@link IMessage} to encode.
	 * @return A {@link ByteBuffer} containing the encoded form of the message.
	 * @throws MessageEncodingException Thrown if an error occurs while trying
	 * to encode the message.
	 */
	ByteBuffer encodeMessage(final IMessage message) throws MessageEncodingException;
}