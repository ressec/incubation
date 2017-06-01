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
package org.heliosphere.drake.server.game.mode;

/**
 * Enumeration of the several server game mode types.
 * <p>
 * @author <a href="mailto:christophe.resse@hotmail.com">Resse Christophe</a>
 * @version 1.0.0
 */
public enum ServerGameModeType implements IServerGameModeType
{

	/**
	 * Login game mode.
	 */
	LOGIN,

	/**
	 * Welcome game mode.
	 */
	WELCOME,

	/**
	 * Logout game mode.
	 */
	LOGOUT;
}
