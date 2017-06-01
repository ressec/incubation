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
package org.heliosphere.drake.base.message.listener;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.MessageException;


/**
 * Listener interface for being notified of messages.
 * <p>
 * Classes interested in processing messages must implement this interface and
 * the object created with these classes are registered with the
 * {@link IMessage} object using the
 * <p>
 * When an {@link IMessage} is received, the object's appropriate method is
 * invoked.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageListener
{
	/**
	 * Queues a message each time a listener receives a message.
	 * <p>
	 * @param message Message to queue.
	 * @throws MessageException Thrown if an error occurs while processing the
	 * message.
	 */
	void queueMessage(final IMessage message) throws MessageException;

	/**
	 * Invoked each time a listener receives a message.
	 * <p>
	 * @param message Message to process.
	 */
	void onMessage(final IMessage message);

	/**
	 * Returns the next message waiting to be processed.
	 * <p>
	 * @return Message to process.
	 */
	IMessage getNextMessage();
}
