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
 * Used by the {@code IGame} and the {@code IGameMode}s it creates to
 * instantiate {@code IRegion}s. Currently this is just a placeholder.
 * <p>
 * TODO What does the query interface look like? How do we abstract this such
 * that it's actually more useful than directly creating {@code IRegion}
 * instances as needed? How do we not make it too hard to describe the type of
 * region and the associated parameters? Do all implementations of
 * {@code IRegion} need to take the same set of parameters?
 * <p>
 * <b>NOTE:</b><br>
 * This object could simply be transient, passed only to {@code IGame} at
 * startup and then never used again. This wouldn't support games that need to
 * create new {@code IRegion} instances as the game progresses. For this reason
 * the factory extends {@code ManagedObject} so that if a {@code IGame} needs to
 * create new {@code IRegion}s after initialization it can keep a reference to
 * the factory. Another approach would be to have a {@code Manager} provide
 * access, which would provide more flexibility, but also makes it less clear
 * who should use this factory and to what end.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Apr 7, 2011 @ 5:12:16 PM
 */
public interface IServerRegionFactory extends ManagedObject
{
}
