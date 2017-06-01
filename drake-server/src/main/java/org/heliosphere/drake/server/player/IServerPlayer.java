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
package org.heliosphere.drake.server.player;

import org.heliosphere.drake.base.player.IPlayer;
import org.heliosphere.drake.server.client.ClientListener;
import org.heliosphere.drake.server.entity.IServerEntity;
import org.heliosphere.drake.server.game.IServerGame;
import org.heliosphere.drake.server.game.mode.IServerGameMode;

/**
 * Interface defining the bahavior of a server player.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Sep 21, 2011 @ 6:56:49 PM
 */
public interface IServerPlayer extends IServerEntity, IPlayer
{
	/**
	 * Disconnects the player from the server.
	 * <p>
	 * @return {@code True} if the player has been successfully disconnected
	 * from its client session, {@code false} if the player was not connected to
	 * a client session.
	 */
	boolean disconnect();

	/**
	 * Sets the game the player is participating.
	 * <p>
	 * @param game {@link IServerGame}.
	 */
	void setGame(final IServerGame game);

	/**
	 * Makes the player leave the game he is currently participating.
	 */
	void leave();

	/**
	 * Returns the server game the player is currently participating.
	 * <p>
	 * @return {@link IServerGame}.
	 */
	IServerGame getGame();

	/**
	 * Sets the game mode the player is participating.
	 * <p>
	 * @param mode {@link IServerGameMode}.
	 */
	void setGameMode(final IServerGameMode mode);

	/**
	 * Returns the game mode the player is participating.
	 * <p>
	 * @return {@link IServerGameMode}.
	 */
	IServerGameMode getGameMode();

	/**
	 * Sets the client listener for this server side player.
	 * @param listener Client listener.
	 */
	void setClientListener(final ClientListener listener);
}
