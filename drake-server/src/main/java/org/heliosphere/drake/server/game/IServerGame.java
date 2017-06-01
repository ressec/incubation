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
package org.heliosphere.drake.server.game;

import java.io.Serializable;
import java.util.Properties;

import org.heliosphere.drake.base.game.IGame;
import org.heliosphere.drake.base.player.IPlayer;
import org.heliosphere.drake.server.entity.IServerEntity;
import org.heliosphere.drake.server.game.mode.IServerGameMode;
import org.heliosphere.drake.server.game.mode.IServerGameModeType;
import org.heliosphere.drake.server.player.IServerPlayer;
import org.heliosphere.drake.server.region.IServerRegionFactory;

import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.ManagedObject;

/**
 * Interface representing the root logic of a game. When the application first
 * starts, it will construct a single game that drives all game-specific logic.
 * All implementations of game must also implement {@link Serializable}, and may
 * implement {@link ManagedObject}. It is strongly suggested that
 * implementations be immutable and contain fairly minimal state directly, and
 * that {@link ManagedObject} should only be implemented if mutable state must
 * be included.
 * <p>
 * All implementations must have a constructor that takes two parameters. A
 * {@link Properties} and a {@link IServerRegionFactory}. This is how the game
 * is initialized. The constructor is invoked in the context of an unbounded
 * transaction, so that it may take as much time as it needs to setup.
 * <p>
 * An implementation represents top-level game-specific logic, and provides
 * access to all player instances thereby getting to decide what specific
 * implementation of player is used. It may have its own state, but more likely
 * a game will be made up of many game modes, and will do little more than
 * create these modes and handle moving of the players between these modes.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Sep 21, 2011 @ 6:48:46 PM
 */
public interface IServerGame extends IGame, IServerEntity
{
	/**
	 * Returns the player corresponding to the given name.
	 * <p>
	 * @param name Name of the player.
	 * @return {@link IPlayer}.
	 */
	IServerPlayer getPlayer(final String name);

	/**
	 * Returns the player associated with the given client session.
	 * <p>
	 * @param session {@link ClientSession}.
	 * @return {@link IPlayer}.
	 */
	IServerPlayer getPlayer(final ClientSession session);

	/**
	 * Makes a player join the game.
	 * <p>
	 * @param player {@link IServerPlayer}.
	 */
	void join(final IServerPlayer player);

	/**
	 * Returns the game mode of the given type.
	 * <p>
	 * @param type Game mode type.
	 * @return {@link IServerGameMode}.
	 */
	IServerGameMode getMode(final Enum<? extends IServerGameModeType> type);
}
