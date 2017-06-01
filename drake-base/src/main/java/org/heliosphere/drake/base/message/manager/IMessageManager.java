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
import java.util.List;

import org.heliosphere.drake.base.codec.type.CodecType;
import org.heliosphere.drake.base.manager.IManager;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.handler.IMessageHandler;
import org.heliosphere.drake.base.message.type.IMessageCategoryType;
import org.heliosphere.drake.base.message.type.IMessageType;

/**
 * Interface defining the behavior of a message manager.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageManager extends IManager
{
	//	/**
	//	 * Sets the data codec algorithm type used by the message manager to
	//	 * encode and decode message content.
	//	 * <p>
	//	 * @param algorithm {@link ICodecAlgorithmType} enumerated value.<br>
	//	 * This enumerated value provides services to access specific data encoders
	//	 * and decoders for primitives, collections and objects.
	//	 * @throws ClassCatalogException Thrown if an error occurs while registering
	//	 * classes.
	//	 * @throws DataEncodingException Thrown if an error occurs while
	//	 * initializing the data encoder.
	//	 * @throws DataDecodingException Thrown if an error occurs while
	//	 * initializing the data decoder.
	//	 */
	//	void setDataCodecAlgorithm(final Enum<? extends ICodecAlgorithmType> algorithm) throws ClassCatalogException, DataEncodingException, DataDecodingException;

	/**
	 * Sets the message encoder type to use.
	 * <p>
	 * @param type Message encoder type to use.
	 */
	void setMessageCodecType(final CodecType type);

	/**
	 * Returns the encoder type used by the message manager.
	 * <p>
	 * @return {@link CodecType}.
	 */
	CodecType getMessageCodecType();

	/**
	 * Returns the message encoder used to encode and decode messages.
	 * <p>
	 * @return {@link IMessageCodec}.
	 * @throws MessageManagerException Thrown if an error occured while
	 * getting the message codec.
	 */
	IMessageCodec getMessageCodec() throws MessageManagerException;

	//	/**
	//	 * Returns the data encoder/decoder used by the message manager.
	//	 * <p>
	//	 * @return {@link ICodec}.
	//	 */
	//	ICodec getDataCodec();

	//	/**
	//	 * Returns the message serializer. Uses the standard Java serialization
	//	 * mechanism to encode/decode messages and their content.
	//	 * <p>
	//	 * @return {@link IMessageCodec}.
	//	 */
	//	IMessageCodec getMessageSerializer();

	//	/**
	//	 * Sets the class catalog used by data encoder/decoder.
	//	 * <p>
	//	 * @param catalog {@link IClassCatalog} to set.
	//	 */
	//	void setClassCatalog(final IClassCatalog catalog);

	/**
	 * Registers a set of messages contained in the given enumeration class.
	 * <p>
	 * @param protocolClass Enumeration containing the message definitions to
	 * register.
	 * @throws MessageManagerException Thrown if an error occured while
	 * registering the messages contained in the given enumeration.
	 */
	void registerMessage(final Class<? extends IMessageType> protocolClass) throws MessageManagerException;

	//	/**
	//	 * Handles the given message.
	//	 * <p>
	//	 * @param encoded Buffer of bytes containing the encoded form of the
	//	 * message.
	//	 * @return Result of the processing of the message.
	//	 * @throws MessageDecodingException Thrown if an error occurs while
	//	 * decoding the message.
	//	 */
	//	Object handleMessage(final ByteBuffer encoded) throws MessageDecodingException;

	/**
	 * Handles the given message.
	 * <p>
	 * @param message Message to handle.
	 * @return Result of the processing of the message.
	 * @throws MessageDecodingException Thrown if an error occurs while
	 * decoding the message.
	 */
	Object handleMessage(final IMessage message) throws MessageDecodingException;

	/**
	 * Returns if a message is already registered with the given message
	 * unique identifier.
	 * <p>
	 * @param messageId Message unique identifier.
	 * @return {@code True} if a message is already registered with the given
	 * message unique identifier, {@code false} otherwise.
	 */
	boolean isRegistered(final int messageId);

	/**
	 * Returns if a message is already registered with the given message
	 * name.
	 * <p>
	 * @param messageName Message name.
	 * @return {@code True} if a message is already registered with the given
	 * message name, {@code false} otherwise.
	 */
	boolean isRegistered(final String messageName);

	/**
	 * Returns a list of registered messages for a given message category.
	 * <p>
	 * @param messageCategory Message category.
	 * @return A list of registered message definitions for the given message
	 * category.
	 */
	List<Enum<? extends IMessageType>> getRegisteredMessageByCategory(final Enum<? extends IMessageCategoryType> messageCategory);

	/**
	 * Returns a list of registered messages for a given message class.
	 * <p>
	 * @param messageClass Message class.
	 * @return A list of registered message definitions for the given message
	 * class.
	 */
	List<Enum<? extends IMessageType>> getRegisteredMessageByMessageClass(final Class<? extends IMessage> messageClass);

	/**
	 * Returns a list of registered messages for a given message data class.
	 * <p>
	 * @param dataClass Message data class.
	 * @return A list of registered message definitions for the given message
	 * data class.
	 */
	List<Enum<? extends IMessageType>> getRegisteredMessageByDataClass(final Class<? extends Serializable> dataClass);

	/**
	 * Returns a list of registered messages for a given message handler class.
	 * <p>
	 * @param handlerClass Message handler class.
	 * @return A list of registered message definitions for the given message
	 * handler class.
	 */
	List<Enum<? extends IMessageType>> getRegisteredMessageByHandlerClass(final Class<? extends IMessageHandler> handlerClass);

	/**
	 * Returns the message defintion based on a given message identifier.
	 * <p>
	 * @param messageId The message identifier.
	 * @return The message definition.
	 * @throws MessageManagerException Thrown if an error occured while
	 * retrieving the message definition.
	 */
	Enum<? extends IMessageType> getMessage(final int messageId) throws MessageManagerException;

	/**
	 * Returns the message defintion based on a given message name.
	 * <p>
	 * @param messageName The message name.
	 * @return The message definition.
	 * @throws MessageManagerException Thrown if an error occured while
	 * retrieving the message definition.
	 */
	Enum<? extends IMessageType> getMessage(final String messageName) throws MessageManagerException;

	//	/**
	//	 * Creates a message based on an encoded message.
	//	 * <p>
	//	 * @param encoded Encoded message.
	//	 * @return Decoded message.
	//	 * @throws MessageDecodingException Thrown in case the decoding of the
	//	 * message failed.
	//	 * @throws MessageManagerException Thrown in case the creation of the
	//	 * message failed.
	//	 */
	//	IMessage createMessage(final ByteBuffer encoded) throws MessageManagerException, MessageDecodingException;

	/**
	 * Creates a new empty message based on a message type.
	 * <p>
	 * @param messageType Message type to create.
	 * @return Returns the newly created message.
	 * @throws MessageManagerException Thrown in case the creation of the
	 * message failed.
	 */
	IMessage createMessage(final Enum<? extends IMessageType> messageType) throws MessageManagerException;

	/**
	 * Creates a new empty message based on a message identifier.
	 * <p>
	 * @param messageId Message identifier to create.
	 * @return Returns the newly created message.
	 * @throws MessageManagerException Thrown in case the creation of the
	 * message failed.
	 */
	IMessage createMessage(final int messageId) throws MessageManagerException;
}
