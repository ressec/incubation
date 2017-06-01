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
package org.heliosphere.drake.base.game;

import org.heliosphere.drake.base.application.IApplication;

/**
 * Interface defining the behaviour of a game.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 */
public interface IGame
{
	/**
	 * Returns the name of the game.
	 * <p>
	 * @return Name of the game.
	 */
	String getName();

	/**
	 * Sets the name of the game.
	 * <p>
	 * @param name Game name.
	 */
	void setName(final String name);

	/**
	 * Returns the application being the owner of the game.
	 * <p>
	 * @return Owner application.
	 */
	IApplication getApplication();

	/**
	 * Shutdowns the game.
	 */
	void shutdown();
}
