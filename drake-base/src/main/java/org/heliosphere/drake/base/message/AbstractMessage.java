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
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import lombok.EqualsAndHashCode;

import org.apache.log4j.Logger;
import org.heliosphere.drake.base.exception.NotImplementedException;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.manager.IMessageManager;
import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.heliosphere.drake.base.message.type.IMessageType;
import org.heliosphere.drake.base.player.IEntity;
import org.joda.time.Instant;

/**
 * Abstract implementation of a message.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@EqualsAndHashCode
public abstract class AbstractMessage implements IMessage
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Buffer allocation default size = 32K.
	 */
	private static final transient short BUFFER_ALLOCATION_SIZE = 32000;

	/**
	 * Logger.
	 */
	protected transient static final Logger log = Logger.getLogger(AbstractMessage.class);

	/**
	 * Encoded form of the message.
	 */
	protected transient ByteBuffer buffer = null;

	/**
	 * Content of the message.
	 */
	protected Object content = null;

	/**
	 * Message type (its definition).
	 */
	private Enum<? extends IMessageType> type;

	/**
	 * Message creation time stamp.
	 */
	private long creationTime = 0L;

	/**
	 * Default immutable charset for encoding/decoding strings in message
	 * header.
	 */
	@SuppressWarnings("nls")
	private final transient Charset headerCharset = Charset.forName("UTF-8");

	/**
	 * Default charset for encoding/decoding strings in message content.
	 */
	@SuppressWarnings("nls")
	private transient Charset contentCharset = Charset.forName("UTF-16LE");

	/**
	 * Creates a new message.
	 */
	protected AbstractMessage()
	{
		buffer = null;
		creationTime = new Instant().getMillis();
	}

	/**
	 * Creates a new message.
	 * <p>
	 * @param messageType Message type.
	 * @throws MessageException Thrown if an error occured while creating
	 * the new message.
	 */
	public AbstractMessage(final Enum<? extends IMessageType> messageType) throws MessageException
	{
		this();
		type = messageType;
		initializeMessageData();
	}

	/**
	 * Creates a new message.
	 * <p>
	 * @param messageId Message identifier.
	 * @throws MessageException Thrown if an error occurs while creating the
	 * message.
	 * @throws MessageManagerException Thrown if an error occured while creating
	 * the new message.
	 */
	public AbstractMessage(final int messageId) throws MessageException, MessageManagerException
	{
		this(((IMessageManager) Manager.getManager(IMessageManager.class)).getMessage(messageId));
		initializeMessageData();
	}

	/**
	 * Creates a new message given a message identifier and a message time
	 * stamp.
	 * <p>
	 * @param messageId Message unique identifier.
	 * @param timeStamp Message time stamp.
	 * @throws MessageException Thrown if an error occured while trying
	 * to create the message.
	 * @throws MessageManagerException Thrown if an error occured while creating
	 * the new message.
	 */
	public AbstractMessage(final int messageId, final long timeStamp) throws MessageException, MessageManagerException
	{
		this(messageId);

		creationTime = timeStamp;
	}

	//	/**
	//	 * Creates a new message based on its encoded form.
	//	 * <p>
	//	 * @param encoded Buffer containing the encoded form of the message.
	//	 * @throws MessageDecodingException Thrown if en error occurs while trying
	//	 * to decode the message.
	//	 */
	//	protected AbstractMessage(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		this();
	//		decodeMessage(encoded);
	//		buffer = null;
	//	}

	/**
	 * Initializes the data associated to the message.
	 * <p>
	 * @throws MessageException Thrown if an error occurs while initializing the
	 * message.
	 */
	private final void initializeMessageData() throws MessageException
	{
		final Class<? extends Serializable> dataClass = ((IMessageType) type).getDataClass();

		try
		{
			content = dataClass.getConstructor().newInstance();
		}
		catch (final Exception e)
		{
			throw new MessageException(e.getMessage());
		}
	}

	//	@Override
	//	public ByteBuffer encodeMessage() throws MessageEncodingException
	//	{
	//		encodeHeader();
	//		encodeContent();
	//
	//		return buffer;
	//	}

	//	/**
	//	 * Decodes the message buffer.
	//	 * <p>
	//	 * @param encoded Buffer containing the encoded form of the message.
	//	 * @throws MessageDecodingException Thrown if an error occurs while trying
	//	 * to
	//	 * decode the encoded form of the message.
	//	 */
	//	protected void decodeMessage(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		buffer = encoded;
	//		buffer.rewind();
	//
	//		decodeHeader();
	//		decodeContent();
	//	}

	//	/**
	//	 * Decodes the message header.
	//	 * <p>
	//	 * The message header contains:<br>
	//	 * <ul>
	//	 * <li>Message protocol identifier (integer)</li>
	//	 * <li>Message identifier (integer)</li>
	//	 * <li>Message creation timestamp (long)</li>
	//	 * <li>Length of the charset name (short)</li>
	//	 * <li>Charset name for string encoding/decoding</li>
	//	 * </ul>
	//	 * <p>
	//	 * @throws MessageDecodingException Thrown if en error occurs while trying
	//	 * to decode the message header.
	//	 */
	//	@SuppressWarnings("nls")
	//	private void decodeHeader() throws MessageDecodingException
	//	{
	//		buffer.position(0);
	//
	//		final int uid = buffer.getInt();
	//
	//		IMessageManager manager = Manager.getManager(IMessageManager.class);
	//
	//		try
	//		{
	//			if (manager.isRegistered(uid))
	//			{
	//				creationTime = buffer.getLong();
	//				contentCharset = Charset.forName(new String(EncodingHelper.getBytes(buffer, buffer.getShort()), headerCharset));
	//				type = manager.getMessage(uid);
	//
	//				initializeMessageData();
	//			}
	//			else
	//			{
	//				throw new MessageException("Message type with uid=" + uid + " is not registered");
	//			}
	//		}
	//		catch (Exception e)
	//		{
	//			throw new MessageDecodingException(e.getMessage());
	//		}
	//	}

	//	/**
	//	 * Decode content.
	//	 * 
	//	 * @throws MessageDecodingException The message decoding exception.
	//	 */
	//	protected abstract void decodeContent() throws MessageDecodingException;

	//	/**
	//	 * Prepares the buffer of bytes for the encoding process and encode the
	//	 * header of the message.
	//	 * <p>
	//	 * The message header contains:<br>
	//	 * <ul>
	//	 * <li>Message protocol identifier (integer)</li>
	//	 * <li>Message identifier (integer)</li>
	//	 * <li>Message creation timestamp (long)</li>
	//	 * <li>Length of the charset name (short)</li>
	//	 * <li>Charset name for string encoding/decoding</li>
	//	 * </ul>
	//	 * @throws MessageEncodingException Thrown if an error occurs while
	//	 * encoding the message header.
	//	 */
	//	protected void encodeHeader() throws MessageEncodingException
	//	{
	//		try
	//		{
	//			buffer = ByteBuffer.allocate(BUFFER_ALLOCATION_SIZE);
	//			buffer.putInt(((IMessageType) type).getMessageId());
	//			buffer.putLong(creationTime);
	//
	//			buffer.putShort((short) contentCharset.name().length());
	//			buffer.put(contentCharset.name().getBytes(headerCharset));
	//		}
	//		catch (final Exception e)
	//		{
	//			throw new MessageEncodingException(e);
	//		}
	//	}

	//	/**
	//	 * Encode content.
	//	 * 
	//	 * @throws MessageEncodingException The message encoding exception.
	//	 */
	//	protected abstract void encodeContent() throws MessageEncodingException;

	/**
	 * Returns the encoded form of the message.
	 * <p>
	 * @return {@link ByteBuffer} containing the encoded form of the message.
	 */
	public final ByteBuffer getEncodedForm()
	{
		return buffer;
	}

	/**
	 * Sets the encoded form of the message.
	 * <p>
	 * @param encoded {@link ByteBuffer} containing the encoded form of the
	 * message to set.
	 */
	public final void setEncodedForm(final ByteBuffer encoded)
	{
		buffer = encoded;
	}

	@Override
	public final long getCreationTime()
	{
		return creationTime;
	}

	/**
	 * Sets the creation timestamp. Used in case a message needs to be created
	 * from a serialized form.
	 * <p>
	 * @param creationTime Message creation time expressed in milliseconds.
	 */
	protected final void setCreationTime(final long creationTime)
	{
		this.creationTime = creationTime;
	}

	/**
	 * Shrinks the buffer.
	 * <p>
	 * @return Shrinked buffer.
	 */
	protected final ByteBuffer shrink()
	{
		final int length = buffer.position();
		buffer.position(0);
		final ByteBuffer array = ByteBuffer.allocate(length);
		buffer.get(array.array(), 0, length);

		buffer = null;

		return array;
	}

	@Override
	public final Charset getContentCharset()
	{
		return contentCharset;
	}

	@Override
	public final void setContent(final Object content)
	{
		this.content = content;
	}

	@Override
	public final void setContentCharset(final Charset charset)
	{
		assert charset != null;

		contentCharset = charset;
	}

	@Override
	public Object getContent()
	{
		return content;
	}

	@Override
	public IEntity getSender() throws NotImplementedException
	{
		throw new NotImplementedException();
	}

	@Override
	public final Enum<? extends IMessageType> getMessageType()
	{
		return type;
	}
}