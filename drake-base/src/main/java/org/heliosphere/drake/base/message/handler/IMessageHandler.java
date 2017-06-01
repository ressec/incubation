package org.heliosphere.drake.base.message.handler;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.message.IMessage;

/**
 * Marker interface for message handlers.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageHandler
{
	/**
	 * Handles the given message.
	 * <p>
	 * @param application Parent application.
	 * @param message Message to handle.
	 */
	void handleMessage(final IApplication application, final IMessage message);
}
