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
package org.heliosphere.drake.base.message;

import java.io.Serializable;
import java.nio.charset.Charset;

import org.heliosphere.drake.base.exception.NotImplementedException;
import org.heliosphere.drake.base.message.type.IMessageType;
import org.heliosphere.drake.base.player.IEntity;

/**
 * Describes the behavior of a message.
 * <p>
 * A message can be seen as anything that is communicated from one player to
 * another or to a group, or to the application logic. On both sides (server and
 * client), messages can be created through a {@code IMessageManager}, which
 * handles both message encoding and message decoding.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessage extends Serializable
{
	//	/**
	//	 * Encodes the message contents into a form that can be sent over the
	//	 * network.
	//	 * <p>
	//	 * @return Buffer containing the encoded message.
	//	 * @throws MessageEncodingException Thrown if an error occurs while
	//	 * trying to encode the message.
	//	 */
	//	public ByteBuffer encodeMessage() throws MessageEncodingException;

	/**
	 * Returns the message creation timestamp.
	 * <p>
	 * @return Message creation timestamp expressed in milliseconds.
	 */
	public long getCreationTime();

	/**
	 * Returns the message type.
	 * <p>
	 * @return Message type.
	 */
	public Enum<? extends IMessageType> getMessageType();

	/**
	 * Returns the sender of this message if one was specified. Messages do not
	 * have to include the sender.
	 * <p>
	 * @return {@link IEntity} being the sender of the message or {@code null}
	 * if the sender is not identified.
	 * @throws NotImplementedException Thrown when the feature is not yet
	 * implemented.
	 */
	public IEntity getSender() throws NotImplementedException;

	/**
	 * Returns the content of the message.
	 * <p>
	 * @return Content of the message ; i.e. its data.
	 */
	public Object getContent();

	/**
	 * Sets the content of the message.
	 * <p>
	 * @param content Content of the message to set.
	 */
	public void setContent(final Object content);

	/**
	 * Returns the charset used for string encoding/decoding.
	 * <p>
	 * @return {@link Charset} used for string encoding/decoding.
	 */
	public Charset getContentCharset();

	/**
	 * Sets the charset used for string encoding/decoding.
	 * <p>
	 * @param charset {@link Charset} used for string encoding/decoding.
	 */
	public void setContentCharset(final Charset charset);
}
