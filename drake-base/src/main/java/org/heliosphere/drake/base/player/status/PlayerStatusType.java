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
package org.heliosphere.drake.base.player.status;

/**
 * Enumeration of the several possible player status types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum PlayerStatusType implements IPlayerStatusType
{
	/**
	 * Player is disconnected.
	 */
	Disconnected,

	/**
	 * Player is banned.
	 */
	Banned,

	/**
	 * Player is authenticating.
	 */
	Authenticating,

	/**
	 * Player is authenticated.
	 */
	Authenticated,

	/**
	 * Player is connecting.
	 */
	Connecting,

	/**
	 * Player is connected.
	 */
	Connected;
}
