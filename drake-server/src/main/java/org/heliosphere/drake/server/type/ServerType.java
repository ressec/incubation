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
package org.heliosphere.drake.server.type;

/**
 * Enumeration of the server types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ServerType implements IServerType
{
	/**
	 * Invalid or unknown server type.
	 */
	INVALID,

	/**
	 * Authentication server.
	 */
	AUTHENTICATION,

	/**
	 * Realm server.
	 */
	REALM,

	/**
	 * Instance server.
	 */
	INSTANCE,

	/**
	 * Raid server.
	 */
	RAID,

	/**
	 * Battleground server.
	 */
	BATTLEGROUND,

	/**
	 * Arena server.
	 */
	ARENA;
}
