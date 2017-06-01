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
package org.heliosphere.drake.client;

import org.heliosphere.drake.base.application.AbstractApplication;
import org.heliosphere.drake.base.application.ApplicationException;
import org.heliosphere.drake.base.application.type.ApplicationType;
import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;
import org.heliosphere.drake.base.game.GameException;
import org.heliosphere.drake.base.game.IGame;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;
import org.heliosphere.drake.client.player.ClientPlayer;
import org.heliosphere.drake.client.player.IClientPlayer;
import org.heliosphere.drake.client.resource.bundle.BundleClient;
import org.heliosphere.drake.client.type.ClientPropertiesType;

/**
 * Abstract implementation of a client application.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractClient extends AbstractApplication implements IClient
{
	/**
	 * Player.
	 */
	private IClientPlayer player;

	/**
	 * Game.
	 */
	private IGame game;

	/**
	 * Creates a new client application.
	 */
	public AbstractClient()
	{
		super(ApplicationType.CLIENT);
	}

	@Override
	protected void registerResourceBundles()
	{
		super.registerResourceBundles();

		ResourceBundleManager.register(BundleClient.class);
	}

	@Override
	public void quit(final Enum<? extends IApplicationExitCodeType> code)
	{
		player.disconnect();

		super.quit(code);
	}

	@Override
	protected void initializePlayers() throws ApplicationException
	{
		super.initializePlayers();

		player = new ClientPlayer(this);
	}

	@Override
	protected void initializeProperty(final String property)
	{
		try
		{
			properties.put(ClientPropertiesType.from(property), configuration.getString(property));
		}
		catch (Exception e)
		{
			super.initializeProperty(property);
		}
	}

	@Override
	public String getProperty(final String key)
	{
		String property = null;

		try
		{
			property = properties.get(ClientPropertiesType.from(key));
		}
		catch (IllegalArgumentException e)
		{
			property = super.getProperty(key);
		}

		return property;
	}

	@Override
	public final IClientPlayer getPlayer()
	{
		return player;
	}

	@Override
	public final IGame getGame()
	{
		return game;
	}

	@Override
	public final void setGame(final IGame game) throws GameException
	{
		this.game = game;
	}
}