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
package org.heliosphere.drake.base.player;

import java.util.Locale;

import org.heliosphere.drake.base.player.status.IPlayerStatusType;

/**
 * Interface defining the behavior of a player.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IPlayer extends IEntity
{
	/**
	 * Returns the player locale.
	 * <p>
	 * @return Player's {@link Locale}.
	 */
	Locale getLocale();

	/**
	 * Sets the player locale.
	 * <p>
	 * @param locale Player locale.
	 */
	void setLocale(final Locale locale);

	/**
	 * Returns the player status.
	 * <p>
	 * @return Player status.
	 */
	Enum<? extends IPlayerStatusType> getStatus();

	/**
	 * Sets the player status.
	 * <p>
	 * @param status Player status.
	 */
	void setStatus(final Enum<? extends IPlayerStatusType> status);
}
