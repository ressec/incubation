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

import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.heliosphere.drake.base.message.type.IMessageType;

import lombok.EqualsAndHashCode;

/**
 * Implementation of a basic message.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
public final class Message extends AbstractMessage
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1;

	/**
	 * Creates a new message.
	 */
	public Message()
	{
		super();
	}

	/**
	 * Creates a new message given a message identifier.
	 * <p>
	 * @param messageId Message unique identifier.
	 * @throws MessageException Thrown if an error occured while trying
	 * to create the message.
	 * @throws MessageManagerException Thrown if an error occurs while trying
	 * to create the message.
	 */
	public Message(final int messageId) throws MessageException, MessageManagerException
	{
		super(messageId);
	}

	/**
	 * Creates a new message given a message identifier and a message time
	 * stamp.
	 * <p>
	 * @param messageId Message unique identifier.
	 * @param timeStamp Message time stamp.
	 * @throws MessageException Thrown if an error occured while trying
	 * to create the message.
	 * @throws MessageManagerException Thrown if an error occurs while trying
	 * to create the message.
	 */
	public Message(final int messageId, final long timeStamp) throws MessageException, MessageManagerException
	{
		super(messageId, timeStamp);
	}

	/**
	 * Creates a new message given a message type.
	 * <p>
	 * @param messageType Message type.
	 * @throws MessageException Thrown if an error occured while trying
	 * to create the message.
	 */
	public Message(final Enum<? extends IMessageType> messageType) throws MessageException
	{
		super(messageType);
	}

	//	/**
	//	 * Creates a new message given an encoded form of the message.
	//	 * <p>
	//	 * @param encoded Encoded form of the message.
	//	 * @throws MessageDecodingException Thrown if an error occurs while trying
	//	 * to decode the encoded form of the message.
	//	 */
	//	public Message(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		super(encoded);
	//	}

	//	@Override
	//	protected void decodeMessage(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		super.decodeMessage(encoded);
	//	}

	//	@Override
	//	public ByteBuffer encodeMessage() throws MessageEncodingException
	//	{
	//		buffer = super.encodeMessage();
	//
	//		return shrink();
	//	}

	//	@Override
	//	protected void decodeContent() throws MessageDecodingException
	//	{
	//		//		IMessageManager manager = Manager.getManager(IMessageManager.class);
	//		//
	//		//		try
	//		//		{
	//		//			setContent(manager.getDataCodec().decode(buffer));
	//		//		}
	//		//		catch (DataDecodingException e)
	//		//		{
	//		//			throw new MessageDecodingException(e.getMessage());
	//		//		}
	//	}

	//	@Override
	//	protected void encodeContent() throws MessageEncodingException
	//	{
	//		//		IMessageManager manager = Manager.getManager(IMessageManager.class);
	//		//
	//		//		try
	//		//		{
	//		//			buffer = manager.getDataCodec().encode(buffer, content);
	//		//		}
	//		//		catch (DataEncodingException e)
	//		//		{
	//		//			throw new MessageEncodingException(e.getMessage());
	//		//		}
	//	}
}