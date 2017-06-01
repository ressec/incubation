package org.heliosphere.drake.message.handler;

import org.heliosphere.drake.base.application.IApplication;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.handler.IMessageHandler;

public class EchoMessageHandler implements IMessageHandler
{
	@Override
	public void handleMessage(final IApplication application, final IMessage message)
	{
		System.out.println(message.getContent());
	}
}
