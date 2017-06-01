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
package org.heliosphere.drake.server.game;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.game.mode.GameModeException;
import org.heliosphere.drake.server.IServer;

@Log4j
public class ServerGame extends AbstractServerGame
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new game on the server side.
	 * <p>
	 * @param server {@link IServer} being the server the game is running on.
	 */
	public ServerGame(final IServer server)
	{
		super(server);
	}

	@Override
	protected void initialize() throws GameModeException
	{
	}
}
