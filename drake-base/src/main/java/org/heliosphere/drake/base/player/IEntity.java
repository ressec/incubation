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
package org.heliosphere.drake.base.player;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.MessageException;

/**
 * Interface that classes being able to send messages over the network must
 * implement.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IEntity
{
	/**
	 * Sends a message over the network to this entity counterpart.
	 * <p>
	 * In case its the client side entity, the message will be sent to the
	 * server side entity and if its the server side entity, the message will be
	 * sent to the client side entity.
	 * <p>
	 * @param message {@link IMessage} to send.
	 * @throws MessageException Thrown if an error occurs while sending the
	 * message.
	 */
	void sendMessage(final IMessage message) throws MessageException;

	/**
	 * Handles a message.
	 * <p>
	 * @param message {@link IMessage} to handle.
	 */
	void handleMessage(final IMessage message);

	/**
	 * Returns the name of the entity.
	 * <p>
	 * @return Entity name.
	 */
	String getName();

	/**
	 * Sets the entity name.
	 * <p>
	 * @param name Entity name.
	 */
	void setName(final String name);
}
