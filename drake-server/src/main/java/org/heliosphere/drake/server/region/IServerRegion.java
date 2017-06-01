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
package org.heliosphere.drake.server.region;

import com.sun.sgs.app.ManagedObject;

/**
 * Interface defining an abstract space where {@code IManagedPlayer}s reside. A
 * typical example is a two-dimensional open space, but implementations could be
 * created for any kind of area where a {@code IManagedPlayer} can be. The
 * general idea is that a {@code IManagedPlayer} can join or leave a
 * {@code IRegion}, and while joined to a {@code IRegion} can try to move around
 * or interact with other {@code IManagedPlayer}s and {@code IManagedItem}s in
 * the {@code IRegion}.<br>
 * A {@code IRegion} implementation should manage the legality of moves, handle
 * updating {@code IManagedPlayer}s as movement or state changes happen, etc.,
 * but delegate to the {@code IGameMode} that created it events that are part of
 * the game-specific logic (e.g. what happens, if anything, on collision,
 * results of stepping on specific spaces, etc.).
 * <p>
 * TODO: Should there be proxy for player to better lookup region details?
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Apr 7, 2011 @ 5:12:16 PM
 */
public interface IServerRegion extends ManagedObject
{
}
