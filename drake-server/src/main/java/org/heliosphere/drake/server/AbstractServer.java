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
package org.heliosphere.drake.server;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.rmi.ServerException;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.application.AbstractApplication;
import org.heliosphere.drake.base.application.type.ApplicationExitCodeType;
import org.heliosphere.drake.base.application.type.ApplicationStatusType;
import org.heliosphere.drake.base.application.type.ApplicationType;
import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;
import org.heliosphere.drake.base.command.CommandException;
import org.heliosphere.drake.base.game.GameException;
import org.heliosphere.drake.base.game.IGame;
import org.heliosphere.drake.base.resource.ResourceException;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;
import org.heliosphere.drake.server.client.ClientListener;
import org.heliosphere.drake.server.game.IServerGame;
import org.heliosphere.drake.server.player.ServerPlayer;
import org.heliosphere.drake.server.resource.bundle.BundleServer;
import org.heliosphere.drake.server.type.ServerPropertiesType;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.AppListener;
import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.ClientSessionListener;
import com.sun.sgs.app.ManagedReference;

/**
 * Abstract implementation of a server.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Resse Christophe</a>
 * @version 1.0.0
 */
@Log4j
public abstract class AbstractServer extends AbstractApplication implements IServer, AppListener, Serializable
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Game reference.
	 */
	private ManagedReference<IServerGame> gameReference;

	/**
	 * Creates a new abstract server given a properties path name.
	 * @throws ServerInitializationException Thrown if an error occured while
	 * initializing
	 * the server.
	 */
	public AbstractServer() throws ServerInitializationException
	{
		super(ApplicationType.SERVER);
	}

	@Override
	public void initialize(final Configuration configuration) throws ConfigurationException, CommandException
	{
		super.initialize(this.configuration != null ? this.configuration : configuration);

		try
		{
			initializeServer();
			initializeGame();
		}
		catch (final ServerException e)
		{
			log.error(e);
			quit(ApplicationExitCodeType.ERROR);
		}
	}

	@Override
	public final void initialize(final Properties properties)
	{
		try
		{
			initialize(ConfigurationConverter.getConfiguration(properties));
		}
		catch (Exception e)
		{
			log.error(e);
		}
	}

	@Override
	protected void initializeProperty(final String property)
	{
		try
		{
			properties.put(ServerPropertiesType.from(property), configuration.getString(property));
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
			property = properties.get(ServerPropertiesType.from(key));
		}
		catch (IllegalArgumentException e)
		{
			property = super.getProperty(key);
		}

		return property;
	}

	/**
	 * Initializes the server.
	 * <p>
	 * @throws ServerException Thrown if an error occured while
	 * initializing the server.
	 */
	protected void initializeServer() throws ServerException
	{
	}

	/**
	 * Initializes the game.
	 * <p>
	 * @throws ServerException Thrown if an error occured while
	 * initializing the server.
	 */
	protected void initializeGame() throws ServerException
	{
		final String classname = getProperty(ServerPropertiesType.GameClassname);
		Validate.notNull(classname, "Game class name cannot be null");

		try
		{
			final Class<?> c = Class.forName(classname);
			final Constructor<?> ctor = c.getConstructor(IServer.class);
			final IServerGame game = (IServerGame) ctor.newInstance(this);
			gameReference = AppContext.getDataManager().createReference(game);
		}
		catch (final Exception e)
		{
			throw new ServerInitializationException("Cannot create game due to: " + e.getMessage());
		}
	}

	@Override
	protected void registerResourceBundles() throws ResourceException
	{
		super.registerResourceBundles();

		ResourceBundleManager.register(BundleServer.class);
	}

	@Override
	public ClientSessionListener loggedIn(final ClientSession session)
	{
		IServerGame game = gameReference.get();

		final ServerPlayer player = (ServerPlayer) game.getPlayer(session);
		game.join(player);

		/*
		 * Here we can authenticate the user and check that:
		 * 1. User account name exists in database
		 * 2. User password is correct
		 * 3. User has still some time to play
		 * 4. User is not banned
		 * 5. etc...
		 */

		return new ClientListener(player);
	}

	@Override
	public void quit(final Enum<? extends IApplicationExitCodeType> code) // Replace by shutdown
	{
		super.quit(code);

		// Shutdown the game.
		gameReference.get().shutdown();

		status = ApplicationStatusType.STOPPED;
	}

	@Override
	public final IServerGame getGame()
	{
		return gameReference.get();
	}

	@Override
	public void setGame(final IGame game) throws GameException
	{
		// Should not be called! Remove from interface
	}
}
