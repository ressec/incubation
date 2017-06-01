package org.heliosphere.drake.base.test.message;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.Message;
import org.heliosphere.drake.base.message.handler.IMessageHandler;
import org.heliosphere.drake.base.message.type.IMessageCategoryType;
import org.heliosphere.drake.base.message.type.IMessageType;
import org.heliosphere.drake.base.message.type.MessageCategoryType;

/**
 * Enumeration of message definitions.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum DrakeMessageType implements IMessageType
{
	/**
	 * Echo message.
	 */
	EchoMessage(1, MessageCategoryType.Basic, Message.class, String.class, EchoMessageHandler.class),

	/**
	 * Error message.
	 */
	ErrorMessage(2, MessageCategoryType.System, Message.class, String.class, ErrorMessageHandler.class);

	@SuppressWarnings("nls")
	private DrakeMessageType(final int id, final Enum<? extends IMessageCategoryType> category, final Class<? extends IMessage> messageClass, final Class<? extends Serializable> dataClass, final Class<? extends IMessageHandler> handlerClass)
	{
		Validate.notNull(messageClass, "Message class cannot be null!");
		Validate.notNull(messageClass, "Message data class cannot be null!");
		Validate.notNull(handlerClass, "Message handler class cannot be null!");

		this.id = id;
		this.category = category;
		this.messageClass = messageClass;
		this.dataClass = dataClass;
		this.handlerClass = handlerClass;
	}

	/**
	 * Message identifier.
	 */
	private int id;

	/**
	 * Mesage category.
	 */
	private Enum<? extends IMessageCategoryType> category;

	/**
	 * Message class.
	 */
	private Class<? extends IMessage> messageClass;

	/**
	 * Data class (data embedded in the message).
	 */
	private Class<? extends Serializable> dataClass;

	/**
	 * Handler class.
	 */
	private Class<? extends IMessageHandler> handlerClass;

	@Override
	public final int getMessageId()
	{
		return id;
	}

	@Override
	public final Enum<? extends IMessageCategoryType> getCategory()
	{
		return category;
	}

	@Override
	public final Class<? extends IMessage> getMessageClass()
	{
		return messageClass;
	}

	@Override
	public final Class<? extends Serializable> getDataClass()
	{
		return dataClass;
	}

	@Override
	public Class<? extends IMessageHandler> getHandlerClass()
	{
		return handlerClass;
	}
}
