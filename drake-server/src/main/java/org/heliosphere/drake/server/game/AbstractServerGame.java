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

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.game.mode.GameModeException;
import org.heliosphere.drake.server.IServer;
import org.heliosphere.drake.server.entity.AbstractServerEntity;
import org.heliosphere.drake.server.game.mode.IServerGameMode;
import org.heliosphere.drake.server.game.mode.IServerGameModeType;
import org.heliosphere.drake.server.game.mode.ServerGameModeFactory;
import org.heliosphere.drake.server.game.mode.ServerGameModeType;
import org.heliosphere.drake.server.player.IServerPlayer;
import org.heliosphere.drake.server.player.ServerPlayer;
import org.heliosphere.drake.server.type.ServerPropertiesType;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.ManagedReference;
import com.sun.sgs.app.NameNotBoundException;

/**
 * Basic implementation of a game running on the server side.
 * <p>
 * As only one game can run on a given server, it is considered that all players
 * logged in on a server are participating to the game running on this server.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public abstract class AbstractServerGame extends AbstractServerEntity implements IServerGame
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Player binding prefix.
	 */
	@SuppressWarnings("nls")
	protected final static String BINDING_PREFIX = "drake:game.";

	/**
	 * Collection of game modes managed by the game.
	 */
	private Map<Enum<? extends IServerGameModeType>, ManagedReference<? extends IServerGameMode>> modes;

	/**
	 * Server the game is running on.
	 */
	private IServer server = null;

	/**
	 * Creates a new abstract server game.
	 * <p>
	 * @param server {@link IServer} being the server the game is running on.
	 */
	public AbstractServerGame(final IServer server)
	{
		super(BINDING_PREFIX, server.getProperty(ServerPropertiesType.GameName));

		this.server = server;

		if (modes == null)
		{
			modes = new HashMap<>();
		}
	}

	/**
	 * Initializes the game.
	 * <p>
	 * @throws GameModeException Thrown if an error occured while initializing
	 * the game, generally when creating and/or registering its game mode(s).
	 */
	protected abstract void initialize() throws GameModeException;

	@Override
	public IServerPlayer getPlayer(final ClientSession session)
	{
		final ServerPlayer player = (ServerPlayer) getPlayer(session.getName());
		player.setSession(session);

		return player;
	}

	@Override
	public final IServerPlayer getPlayer(final String name)
	{
		IServerPlayer player = new ServerPlayer(name);
		final String binding = player.getBindingName();

		try
		{
			player = (IServerPlayer) AppContext.getDataManager().getBinding(binding);
		}
		catch (final NameNotBoundException nnbe)
		{
			AppContext.getDataManager().setBinding(binding, player);
		}

		return player;
	}

	@Override
	public void join(final IServerPlayer player)
	{
		ManagedReference<? extends IServerGameMode> modeReference = null;

		// By default, a player joining a game is also joining the WELCOME game mode.
		if (!modes.containsKey(ServerGameModeType.WELCOME))
		{
			IServerGameMode mode = ServerGameModeFactory.get(ServerGameModeType.WELCOME);
			modeReference = AppContext.getDataManager().createReference(mode);
		}
		else
		{
			modeReference = modes.get(ServerGameModeType.WELCOME);
		}

		modeReference.get().join(player);
		modes.put(ServerGameModeType.WELCOME, modeReference);
	}

	@Override
	public final IServerGameMode getMode(final Enum<? extends IServerGameModeType> type)
	{
		return modes.get(type).get();
	}

	@Override
	public final IServer getApplication()
	{
		return server;
	}

	@Override
	public void shutdown()
	{
		// Notify players participating to the game that the game is shutting down. 
	}
}
