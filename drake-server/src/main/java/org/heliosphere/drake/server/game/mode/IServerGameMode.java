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
package org.heliosphere.drake.server.game.mode;

import org.heliosphere.drake.server.entity.IServerEntity;
import org.heliosphere.drake.server.player.IServerPlayer;

/**
 * Interface defining a specific mode of play within the game. Typical examples
 * would be a lobby, a field for combat, an auction house, etc.<br>
 * A player may only be actively part of a single game mode at a time. A
 * player's active mode will define how any message from the client are
 * processed, and how that player interacts with all other players in the same
 * mode.
 * <p>
 * Typically a game mode will represent some common aspect of play and
 * interaction. In the case of a mode that has some notion of spacial, physical
 * interaction it will contain one or more region(s) to manage these
 * interactions. A game mode does not, however, have to use any region (for
 * instance, a lobby or match-making mode may not have any notion of interaction
 * except for chat).
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Resse Christophe</a>
 * @version 1.0.0
 */
public interface IServerGameMode extends IServerEntity
{
	/**
	 * Makes the given player join the game mode.
	 * <p>
	 * @param player {@link IServerPlayer} to join the game mode.
	 */
	void join(final IServerPlayer player);
}
