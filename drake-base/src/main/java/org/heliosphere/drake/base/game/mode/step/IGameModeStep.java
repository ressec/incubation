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
package org.heliosphere.drake.base.game.mode.step;

import org.heliosphere.drake.base.game.mode.IGameMode;

/**
 * Interface defining the behavior of a game mode step.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IGameModeStep extends Runnable
{
	/**
	 * Returns the parent game mode.
	 * <p>
	 * @return Parent game mode.
	 */
	IGameMode getMode();

	/**
	 * Initializes the game mode step.
	 */
	void initialize();

	/**
	 * Sends an event to the parent game mode.
	 * <p>
	 * @param event Event to send.
	 */
	void sendEvent(final IGameModeStepEvent event);
}
