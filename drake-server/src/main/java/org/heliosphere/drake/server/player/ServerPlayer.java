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
package org.heliosphere.drake.server.player;

import java.util.Locale;

import org.apache.log4j.Logger;

import com.sun.sgs.app.ClientSession;

/**
 * A server player.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Sep 22, 2011 @ 1:03:02 PM
 */
public class ServerPlayer extends AbstractServerPlayer
{
	/**
	 * Serialization identifer.
	 */
	protected static final long serialVersionUID = 1L;

	/**
	 * Dedicated logger.
	 */
	protected static Logger log = Logger.getLogger(ServerPlayer.class);

	/**
	 * Creates a new server player given a client session.
	 * <p>
	 * @param session {@link ClientSession} to associate to the player.
	 */
	public ServerPlayer(final ClientSession session)
	{
		this(session.getName());
		setSession(session);
	}

	/**
	 * Creates a new server player given a name.
	 * <p>
	 * @param name Player's name.
	 */
	public ServerPlayer(final String name)
	{
		super(name);
	}

	/**
	 * Creates a new server player given a name and a locale.
	 * <p>
	 * @param name Player's name.
	 * @param locale Player's {@link Locale}.
	 */
	public ServerPlayer(final String name, final Locale locale)
	{
		super(name, locale);
	}
}
