package org.heliosphere.drake.server.message;

import org.heliosphere.drake.base.message.manager.MessageManager;

public class ServerMessageManager extends MessageManager
{
	private static MessageService service = null;

	public ServerMessageManager(final MessageService service)
	{
		this.service = service;
	}
}
