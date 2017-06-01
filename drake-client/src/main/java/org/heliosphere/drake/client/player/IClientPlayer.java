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
package org.heliosphere.drake.client.player;

import java.io.IOException;

import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.player.IPlayer;

import com.sun.sgs.client.simple.SimpleClient;
import com.sun.sgs.client.simple.SimpleClientListener;

/**
 * Interface defining the behavior of player located on the client side.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IClientPlayer extends IPlayer, SimpleClientListener
{
	/**
	 * Connects the player to a server given a user identifier and a password
	 * for authentication.
	 * <p>
	 * @param identifier User identifier.
	 * @param password User password.
	 * @throws IOException Thrown if an error occured while trying to
	 * log the client player to the server.
	 */
	void connect(final String identifier, final String password) throws IOException;

	/**
	 * Disconnects the player from the server.
	 */
	void disconnect();

	/**
	 * Returns the client session associated to the player.
	 * <p>
	 * @return {@link SimpleClient}.
	 */
	SimpleClient getSession();

	/**
	 * Sets the authentication vault.
	 * <p>
	 * @param identifier User identifier.
	 * @param password User password.
	 */
	void setAuthentication(final String identifier, final String password);

	/**
	 * Returns if the player is connected.
	 * <p>
	 * @return {@code True} if the player is connected, {@code false} otherwise.
	 */
	boolean isConnected();

	/**
	 * Returns the message codec used to encode and decode messages and their
	 * content.
	 * <p>
	 * @return {@link IMessageCodec}.
	 */
	IMessageCodec getMessageCodec();
}
