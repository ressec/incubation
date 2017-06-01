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
package org.heliosphere.drake.server.client;

import java.io.Serializable;
import java.nio.ByteBuffer;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.server.player.IServerPlayer;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.ClientSessionListener;
import com.sun.sgs.app.ManagedReference;

/**
 * A client session listener created for a given server side player. The
 * player's game proxy or game mode proxy is used to handle incoming messages.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Sep 21, 2011 @ 6:55:00 PM
 */
@Log4j
public final class ClientListener implements ClientSessionListener, Serializable
{
	/**
	 * Default serialization identifer.
	 */
	private static final long serialVersionUID = 1;

	/**
	 * Reference to the player.
	 */
	private final ManagedReference<? extends IServerPlayer> reference;

	/**
	 * Creates a new client listener for the given server player.
	 * <p>
	 * @param player {@link IServerPlayer} associated with this client session
	 * listener.
	 */
	public ClientListener(final IServerPlayer player)
	{
		reference = AppContext.getDataManager().createReference(player);
		AppContext.getDataManager().setBinding(player.getBindingName(), player);

		player.setClientListener(this);
	}

	@Override
	public void disconnected(final boolean graceful)
	{
		reference.get().disconnect();
	}

	@SuppressWarnings("nls")
	@Override
	public void receivedMessage(final ByteBuffer message)
	{
		final IServerPlayer player = reference.get();

		try
		{
			player.handleMessage(player.getMessageCodec().decodeMessage(message));
		}
		catch (final MessageDecodingException e)
		{
			log.error("Cannot decode message due to: " + e.getMessage());
		}
	}
}