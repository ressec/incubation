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
package org.heliosphere.drake.base.message.manager;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.application.type.ApplicationPropertiesType;
import org.heliosphere.drake.base.codec.type.CodecType;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.handler.IMessageHandler;
import org.heliosphere.drake.base.message.serializer.JavaMessageSerializer;
import org.heliosphere.drake.base.message.serializer.KryoMessageSerializer;
import org.heliosphere.drake.base.message.type.IMessageCategoryType;
import org.heliosphere.drake.base.message.type.IMessageType;

/**
 * Manager providing access to registered messages.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public class MessageManager implements IMessageManager
{
	/**
	 * Collection of registered messages by their identifier.
	 */
	Map<Integer, Enum<? extends IMessageType>> byMessageId = new HashMap<>();

	/**
	 * Collection of registered messages by their name.
	 */
	Map<String, Enum<? extends IMessageType>> byMessageName = new HashMap<>();

	/**
	 * Collection of registered messages by their category.
	 */
	Map<Enum<? extends IMessageCategoryType>, List<Enum<? extends IMessageType>>> byMessageCategory = new HashMap<>();

	/**
	 * Collection of registered messages by their message class.
	 */
	Map<Class<? extends IMessage>, List<Enum<? extends IMessageType>>> byMessageClass = new HashMap<>();

	/**
	 * Collection of registered messages by their message data class.
	 */
	Map<Class<? extends Serializable>, List<Enum<? extends IMessageType>>> byDataClass = new HashMap<>();

	/**
	 * Collection of registered messages by their message handler class.
	 */
	Map<Class<? extends IMessageHandler>, List<Enum<? extends IMessageType>>> byHandlerClass = new HashMap<>();

	/**
	 * Message codec (used to encode and decode messages).
	 */
	private IMessageCodec messageCodec = null;

	/**
	 * Default message codec to use.
	 */
	private CodecType messageCodecType = CodecType.KRYO;

	//	/**
	//	 * Class catalog.
	//	 */
	//	private IClassCatalog catalog = null;
	//
	//	/**
	//	 * Data codec (used to encode and decode message data).
	//	 */
	//	private ICodec dataCodec = null;
	//
	//	/**
	//	 * Message serializer.
	//	 */
	//	private IMessageCodec serializer = new MessageSerializer();

	/**
	 * Initializes the messsage manager from a properties file.
	 * <p>
	 * @param configuration {@link Configuration} containing the
	 * properties necessary to initialize message manager.
	 * @throws MessageManagerException Thrown if an error occured while trying
	 * to initialize the message manager.
	 */
	public final void initialize(final Configuration configuration) throws MessageManagerException
	{
		//			initializeDataCodec(configuration);
		//			initializeMessageCodec(configuration);
		initializeMessageProtocol(configuration);
	}

	//	/**
	//	 * Initializes the data codec to use for encoding/decoding message data.
	//	 * <p>
	//	 * @param configuration {@link Configuration} containing the
	//	 * properties necessary to initialize the data codec algorithm.
	//	 * @throws MessageManagerException Thrown if an error occured while trying
	//	 * to initialize the data codec.
	//	 */
	//	@SuppressWarnings({ "unchecked", "rawtypes" })
	//	private final void initializeDataCodec(final Configuration configuration) throws MessageManagerException
	//	{
	//		try
	//		{
	//			Class<Enum> codecClass = (Class<Enum>) Class.forName(configuration.getString(ApplicationPropertiesType.ApplicationDataEncoderAlgorithmClassname.getValue()));
	//			Enum<?> algorithm = Enum.valueOf(codecClass, configuration.getString(ApplicationPropertiesType.ApplicationDataEncoderAlgorithmType.getValue()));
	//
	//			setDataCodecAlgorithm((Enum<? extends ICodecAlgorithmType>) algorithm);
	//
	//			initializeDataCodecRuleSet(configuration);
	//		}
	//		catch (Exception e)
	//		{
	//			throw new MessageManagerException(e);
	//		}
	//	}

	//	/**
	//	 * Initializes the encoding/decoding rules to apply to the data codec.
	//	 * <p>
	//	 * @param configuration {@link Configuration} containing the
	//	 * rules to apply to the data codec.
	//	 * @throws MessageManagerException Thrown if an error occured while trying
	//	 * to initialize data codec rules.
	//	 */
	//	private final void initializeDataCodecRuleSet(final Configuration configuration) throws MessageManagerException
	//	{
	//		ICodec codec = getDataCodec();
	//		codec.add(CodecStrategyType.PrimitiveClassNotNull); //TODO Change to initailize the rules from the properties file.
	//	}

	//	private final void initializeMessageCodec(final Configuration configuration) throws MessageManagerException
	//	{
	//		try
	//		{
	//			Enum<?> algorithm = Enum.valueOf(MessageCodecType.class, configuration.getString(ApplicationPropertiesType.ApplicationMessageEncoderType.getValue()));
	//			setMessageCodecType((MessageCodecType) algorithm);
	//		}
	//		catch (Exception e)
	//		{
	//			throw new MessageManagerException(e);
	//		}
	//	}

	@SuppressWarnings("unchecked")
	private final void initializeMessageProtocol(final Configuration configuration) throws MessageManagerException
	{
		try
		{
			Class<?> messageProtocolClass = Class.forName(configuration.getString(ApplicationPropertiesType.ApplicationMessageProtocolClassname.getValue()));
			registerMessage((Class<? extends IMessageType>) messageProtocolClass);
		}
		catch (Exception e)
		{
			throw new MessageManagerException(e);
		}
	}

	//	@Override
	//	public final Object handleMessage(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		IMessage message = decodeMessage(encoded);
	//
	//		try
	//		{
	//			IMessageManager manager = Manager.getManager(IMessageManager.class);
	//			return manager.handleMessage(message);
	//		}
	//		catch (Exception e)
	//		{
	//			throw new MessageDecodingException(e.getMessage());
	//		}
	//	}

	@SuppressWarnings("nls")
	@Override
	public synchronized final Object handleMessage(final IMessage message) throws MessageDecodingException
	{
		Object object = null;
		Method method = null;
		Object result = null;

		Class<? extends IMessageHandler> handlerClass = ((IMessageType) message.getMessageType()).getHandlerClass();

		try
		{
			object = handlerClass.getConstructor().newInstance();
			method = object.getClass().getMethod("handleMessage", IApplication.class, IMessage.class);
			if (method != null)
			{
				result = method.invoke(object, Manager.getApplication(), message);
			}
		}
		catch (Exception e)
		{
			throw new MessageDecodingException(e.getMessage());
		}

		return result;
	}

	//	/**
	//	 * Decodes the message.
	//	 * <p>
	//	 * @param encoded Buffer of bytes containing the encoded form of the
	//	 * message.
	//	 * @return Decoded message.
	//	 * @throws MessageDecodingException Thrown if an error occurs while
	//	 * decoding the message.
	//	 */
	//	private final static IMessage decodeMessage(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		IMessage message = null;
	//
	//		encoded.rewind(); // Ensure we are at the beginning of the buffer
	//
	//		IMessageManager manager = Manager.getManager(IMessageManager.class);
	//		Enum<? extends IMessageType> messageType;
	//		try
	//		{
	//			messageType = manager.getMessage(encoded.getInt());
	//			final Class<? extends IMessage> messageClass = ((IMessageType) messageType).getMessageClass();
	//
	//			try
	//			{
	//				final Constructor<?> ctor = messageClass.getConstructor(ByteBuffer.class);
	//				message = (IMessage) ctor.newInstance(encoded);
	//			}
	//			catch (final Exception e)
	//			{
	//				throw new MessageDecodingException(e.getMessage());
	//			}
	//		}
	//		catch (MessageManagerException me)
	//		{
	//			throw new MessageDecodingException(me.getMessage());
	//		}
	//
	//		return message;
	//	}

	@SuppressWarnings({ "nls", "unchecked" })
	@Override
	public final void registerMessage(final Class<? extends IMessageType> protocolClass) throws MessageManagerException
	{
		Validate.notNull(protocolClass, "Message enumeration class cannot be null");
		Validate.isTrue(protocolClass.isEnum(), "Message enumeration class: " + protocolClass.getName() + " is not an enumeration!");

		int id = 0;
		Enum<? extends IMessageCategoryType> category;
		Class<? extends IMessage> messageClass;
		Class<? extends Serializable> dataClass;
		Class<? extends IMessageHandler> handlerClass;

		/*
		 * For the given enumeration, all the enumerated values are supposed to
		 * be message definitions, so register all enumerated values.
		 */

		Enum<? extends IMessageType>[] types = (Enum<? extends IMessageType>[]) protocolClass.getEnumConstants();
		for (Enum<? extends IMessageType> type : types)
		{
			// Does this message definition in conflict with another entry?

			id = ((IMessageType) type).getMessageId();
			category = ((IMessageType) type).getCategory();
			messageClass = ((IMessageType) type).getMessageClass();
			dataClass = ((IMessageType) type).getDataClass();
			handlerClass = ((IMessageType) type).getHandlerClass();

			// Ensure the message identifier is not already registered
			if (byMessageId.containsKey(Integer.valueOf(id)))
			{
				throw new MessageManagerException("Cannot register message enumeration class: " + protocolClass + " because message identifier: " + id + " is already registered!");
			}

			// Ensure the enumerated value name is not already registered
			if (byMessageName.containsKey(type.name()))
			{
				throw new MessageManagerException("Cannot register message enumeration class: " + protocolClass + " because message name: " + type.name() + " is already registered!");
			}

			// Register message by its identifier.
			byMessageId.put(Integer.valueOf(id), type);

			// Register message by its name.
			byMessageName.put(type.name(), type);

			// Register the message by its category.
			List<Enum<? extends IMessageType>> listByCategory = byMessageCategory.get(category);
			if (listByCategory == null)
			{
				listByCategory = new ArrayList<>();
			}
			listByCategory.add(type);
			byMessageCategory.put(category, listByCategory);

			// Register the message by its message class.
			List<Enum<? extends IMessageType>> listByMessageClass = byMessageClass.get(messageClass);
			if (listByMessageClass == null)
			{
				listByMessageClass = new ArrayList<>();
			}
			listByMessageClass.add(type);
			byMessageClass.put(messageClass, listByMessageClass);

			// Register the message by its data class.
			List<Enum<? extends IMessageType>> listByDataClass = byDataClass.get(dataClass);
			if (listByDataClass == null)
			{
				listByDataClass = new ArrayList<>();
			}
			listByDataClass.add(type);
			byDataClass.put(dataClass, listByDataClass);

			// Register the message by its handler class.
			List<Enum<? extends IMessageType>> listByHandlerClass = byHandlerClass.get(dataClass);
			if (listByHandlerClass == null)
			{
				listByHandlerClass = new ArrayList<>();
			}
			listByHandlerClass.add(type);
			byHandlerClass.put(handlerClass, listByHandlerClass);

			log.info("Message registered [id=" + id + ", name=" + type.name() + ", category=" + category + ", message.class=" + messageClass.getName() + ", data.class=" + dataClass.getName() + ", handler.class=" + handlerClass.getName() + "]");
		}
	}

	@Override
	public final boolean isRegistered(final int messageId)
	{
		return byMessageId.containsKey(Integer.valueOf(messageId));
	}

	@Override
	public final boolean isRegistered(final String messageName)
	{
		return byMessageName.containsKey(messageName);
	}

	@Override
	public final void setMessageCodecType(final CodecType codec)
	{
		messageCodecType = codec;

		switch (codec)
		{
			case JAVA:
				messageCodec = new JavaMessageSerializer();
				break;

			case KRYO:
				messageCodec = new KryoMessageSerializer();
				break;
		}
	}

	//	@SuppressWarnings("nls")
	//	@Override
	//	public final void setDataCodecAlgorithm(final Enum<? extends ICodecAlgorithmType> algorithm) throws ClassCatalogException, DataEncodingException, DataDecodingException
	//	{
	//		Validate.notNull(algorithm, "Data encoder algorithm type cannot be null");
	//
	//		catalog = new ClassCatalog();
	//
	//		// Register the algorithm classes.
	//		catalog.register(((ICodecAlgorithmType) algorithm).getObjectCodecClass());
	//
	//		dataCodec = new Codec((ICodecAlgorithmType) algorithm, catalog);
	//	}

	@Override
	public final CodecType getMessageCodecType()
	{
		return messageCodecType;
	}

	@Override
	public final synchronized IMessageCodec getMessageCodec() throws MessageManagerException
	{
		switch (messageCodecType)
		{
			case JAVA:
				return new JavaMessageSerializer();

			case KRYO:
				return new KryoMessageSerializer();
		}

		throw new MessageManagerException("No message codec type defined!");
	}

	//	@Override
	//	public final ICodec getDataCodec()
	//	{
	//		return dataCodec;
	//	}

	//	@Override
	//	public final IMessageCodec getMessageSerializer()
	//	{
	//		return serializer;
	//	}

	//	@Override
	//	public final void setClassCatalog(final IClassCatalog catalog)
	//	{
	//		dataCodec.setClassCatalog(catalog);
	//	}

	//	public final IClassCatalog getClassCatalog()
	//	{
	//		return catalog;
	//	}

	@Override
	public final List<Enum<? extends IMessageType>> getRegisteredMessageByCategory(final Enum<? extends IMessageCategoryType> messageCategory)
	{
		return Collections.unmodifiableList(byMessageCategory.get(messageCategory));
	}

	@Override
	public final List<Enum<? extends IMessageType>> getRegisteredMessageByMessageClass(final Class<? extends IMessage> messageClass)
	{
		return Collections.unmodifiableList(byMessageClass.get(messageClass));
	}

	@Override
	public final List<Enum<? extends IMessageType>> getRegisteredMessageByDataClass(final Class<? extends Serializable> dataClass)
	{
		return Collections.unmodifiableList(byDataClass.get(dataClass));
	}

	@Override
	public final List<Enum<? extends IMessageType>> getRegisteredMessageByHandlerClass(final Class<? extends IMessageHandler> handlerClass)
	{
		return Collections.unmodifiableList(byHandlerClass.get(handlerClass));
	}

	@Override
	public final Enum<? extends IMessageType> getMessage(final int messageId) throws MessageManagerException
	{
		return byMessageId.get(Integer.valueOf(messageId));
	}

	@Override
	public final Enum<? extends IMessageType> getMessage(final String messageName) throws MessageManagerException
	{
		return byMessageName.get(messageName);
	}

	//	@Override
	//	public final IMessage createMessage(final ByteBuffer encoded) throws MessageDecodingException
	//	{
	//		return messageCodec.decodeMessage(encoded);
	//	}

	@Override
	public final synchronized IMessage createMessage(final int messageId) throws MessageManagerException
	{
		return createMessage(byMessageId.get(Integer.valueOf(messageId)));
	}

	@SuppressWarnings("nls")
	@Override
	public final synchronized IMessage createMessage(final Enum<? extends IMessageType> messageType) throws MessageManagerException
	{
		IMessage message = null;
		int messageId;
		String messageName;
		Enum<? extends IMessageCategoryType> messageCategory;
		Class<? extends IMessage> messageClass;
		Class<? extends Serializable> dataClass;
		Class<? extends IMessageHandler> handlerClass;

		// Does the message definition registered?
		int id = ((IMessageType) messageType).getMessageId();
		Enum<? extends IMessageType> type = byMessageId.get(Integer.valueOf(id));
		if (type != null)
		{
			messageId = ((IMessageType) type).getMessageId();
			messageCategory = ((IMessageType) type).getCategory();
			messageClass = ((IMessageType) type).getMessageClass();
			dataClass = ((IMessageType) type).getDataClass();
			handlerClass = ((IMessageType) type).getHandlerClass();
			messageName = type.name();

			// Categories must be the same 
			if (messageCategory != ((IMessageType) messageType).getCategory())
			{
				throw new MessageManagerException("Cannot create message of type: " + messageType.getClass().getName() + " ; " + messageType.name());
			}

			// Message class must be the same 
			if (messageClass != ((IMessageType) messageType).getMessageClass())
			{
				throw new MessageManagerException("Cannot create message of type: " + messageType.getClass().getName() + " ; " + messageType.name());
			}

			// Data class must be the same 
			if (dataClass != ((IMessageType) messageType).getDataClass())
			{
				throw new MessageManagerException("Cannot create message of type: " + messageType.getClass().getName() + " ; " + messageType.name());
			}

			// Handler class must be the same 
			if (handlerClass != ((IMessageType) messageType).getHandlerClass())
			{
				throw new MessageManagerException("Cannot create message of type: " + messageType.getClass().getName() + " ; " + messageType.name());
			}

			// Message name must be the same 
			if (!messageName.equals(messageType.name()))
			{
				throw new MessageManagerException("Cannot create message of type: " + messageType.getClass().getName() + " ; " + messageType.name());
			}
		}
		else
		{
			throw new MessageManagerException("Cannot create message of type: " + messageType.getClass().getName() + " ; " + messageType.name() + ". The message type is not registered!");
		}

		try
		{
			Constructor<?> ctor = messageClass.getConstructor(int.class);
			message = (IMessage) ctor.newInstance(Integer.valueOf(messageId));
		}
		catch (Exception e)
		{
			throw new MessageManagerException(e.getMessage());
		}

		return message;
	}
}
