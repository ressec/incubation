/*
 * Copyright(c) 2010-2012 Heliosphere.
 * ---------------------------------------------------------------------------
 * This file is part of the RELIC foundation project which is licensed
 * under the Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project artefact
 * binaries and/or sources ; if not visit:
 * http://www.apache.org/licenses/LICENSE-2.0.html
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.server.message;

import java.util.Properties;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.manager.IMessageManager;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.kernel.ComponentRegistry;
import com.sun.sgs.service.Service;
import com.sun.sgs.service.TransactionProxy;

@Log4j
public class MessageService implements Service
{
	private Properties properties;

	public MessageService(final Properties properties, final ComponentRegistry registry, final TransactionProxy proxy)
	{
		this.properties = properties;
	}

	@Override
	public final String getName()
	{
		return getClass().getSimpleName();
	}

	@Override
	public final void ready() throws Exception
	{
		log.info("Service: " + getClass().getName() + " started");

		// Replace the message manager by the server message manager.
		Manager.replaceManager(IMessageManager.class, AppContext.getManager(IMessageManager.class));
	}

	@Override
	public final void shutdown()
	{
		log.info("Service: " + getClass().getName() + " terminated");
	}
}
