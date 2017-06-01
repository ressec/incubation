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
package org.heliosphere.drake.base.message.type;

import java.io.Serializable;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.handler.IMessageHandler;

/**
 * Interface that message type enumerations must implement.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageType extends Serializable
{
	/**
	 * Returns the message identifier (must be unique).
	 * <p>
	 * @return Message identifier.
	 */
	int getMessageId();

	/**
	 * Returns the message category type.
	 * <p>
	 * @return Message category type.
	 */
	Enum<? extends IMessageCategoryType> getCategory();

	/**
	 * Returns the message class.
	 * <p>
	 * @return Message class.
	 */
	Class<? extends IMessage> getMessageClass();

	/**
	 * Returns the message data class.
	 * <p>
	 * @return Message data class.
	 */
	Class<? extends Serializable> getDataClass();

	/**
	 * Returns the message handler class.
	 * <p>
	 * @return Message handler class.
	 */
	Class<? extends IMessageHandler> getHandlerClass();
}
