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
package org.heliosphere.drake.client.player;

import org.heliosphere.drake.base.application.ApplicationException;
import org.heliosphere.drake.client.IClient;

/**
 * Implementation of a player located on the client side.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class ClientPlayer extends AbstractClientPlayer
{
	/**
	 * Creates a new player located on the client side.
	 * <p>
	 * @param application Parent application (the client application).
	 * @throws ApplicationException Thrown in case an error occured while
	 * initializing the player.
	 */
	public ClientPlayer(final IClient application) throws ApplicationException
	{
		super(application);
	}
}
