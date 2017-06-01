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
package org.heliosphere.drake.server.player;

import java.util.Locale;

import lombok.ToString;
import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.MessageException;
import org.heliosphere.drake.base.player.status.IPlayerStatusType;
import org.heliosphere.drake.base.player.status.PlayerStatusType;
import org.heliosphere.drake.server.client.ClientListener;
import org.heliosphere.drake.server.entity.AbstractServerEntity;
import org.heliosphere.drake.server.game.IServerGame;
import org.heliosphere.drake.server.game.mode.IServerGameMode;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.ManagedReference;
import com.sun.sgs.app.ObjectNotFoundException;

/**
 * Abstract implementation of a player located on the server side.
 * <p>
 * @author Resse Christophe, Heliosphere Corp. 2010-2011
 * @version 1.0.0
 * @since Apr 5, 2011 @ 3:30:11 PM
 */
@Log4j
@ToString
public abstract class AbstractServerPlayer extends AbstractServerEntity implements IServerPlayer
{
	/**
	 * Serialization identifier.
	 */
	private final static long serialVersionUID = 1L;

	/**
	 * Player binding prefix.
	 */
	@SuppressWarnings("nls")
	protected final static String BINDING_PREFIX = "relic:player.";

	/**
	 * Server player status.
	 */
	private Enum<? extends IPlayerStatusType> status = PlayerStatusType.Disconnected;

	/**
	 * Player locale.
	 */
	private Locale locale;

	/**
	 * Client listener.
	 */
	private transient ClientListener listener;

	/**
	 * Client session reference.
	 */
	protected ManagedReference<? extends ClientSession> sessionReference = null;

	/**
	 * Game reference.
	 */
	protected ManagedReference<? extends IServerGame> gameReference = null;

	/**
	 * Game mode reference.
	 */
	protected ManagedReference<? extends IServerGameMode> modeReference = null;

	/**
	 * Creates a new player with a given name.
	 * <p>
	 * @param name Player name to set.
	 */
	protected AbstractServerPlayer(final String name)
	{
		super(BINDING_PREFIX, name);

		status = PlayerStatusType.Connected;
	}

	/**
	 * Creates a new player.
	 * <p>
	 * @param name Player name.
	 * @param locale Player {@link Locale}.
	 */
	protected AbstractServerPlayer(final String name, final Locale locale)
	{
		this(name);
		this.locale = locale;
	}

	@Override
	public final Locale getLocale()
	{
		return locale;
	}

	@Override
	public final void setLocale(final Locale locale)
	{
		this.locale = locale;
	}

	@Override
	public final Enum<? extends IPlayerStatusType> getStatus()
	{
		return status;
	}

	@Override
	public final void setStatus(final Enum<? extends IPlayerStatusType> status)
	{
		this.status = status;
	}

	/**
	 * Returns the client session for this player.
	 * <p>
	 * @return {@link ClientSession} for this player or {@code null} if the
	 * player has no session, i.e. player not connected.
	 */
	public ClientSession getSession()
	{
		try
		{
			return sessionReference == null ? null : sessionReference.get();
		}
		catch (final ObjectNotFoundException onfe)
		{
			AppContext.getDataManager().markForUpdate(this);
			sessionReference = null;

			return null;
		}
	}

	@Override
	public void handleMessage(final IMessage message)
	{
		log.info("Message received [type=" + message.getMessageType() + "]");
	}

	@Override
	public final IServerGame getGame()
	{
		return gameReference == null ? null : gameReference.get();
	}

	@Override
	public final void setGame(final IServerGame game)
	{
		AppContext.getDataManager().markForUpdate(this);
		gameReference = AppContext.getDataManager().createReference(game);
	}

	@Override
	public final IServerGameMode getGameMode()
	{
		return modeReference == null ? null : modeReference.get();
	}

	@Override
	public final void setGameMode(final IServerGameMode mode)
	{
		AppContext.getDataManager().markForUpdate(this);
		modeReference = AppContext.getDataManager().createReference(mode);
	}

	/**
	 * Sets the player session.
	 * <p>
	 * @param session {@link ClientSession} to associate to this player.
	 */
	public void setSession(final ClientSession session)
	{
		final DataManager data = AppContext.getDataManager();

		data.markForUpdate(this);
		sessionReference = session == null ? null : data.createReference(session);

		if (session != null)
		{
			log.info("User [name=" + getName() + ", status=connected]");
			setStatus(PlayerStatusType.Connected);
		}
		else
		{
			log.info("User [name=" + getName() + ", status=disconnected]");
			setStatus(PlayerStatusType.Disconnected);
		}
	}

	@Override
	public final void sendMessage(final IMessage message) throws MessageException
	{
		try
		{
			log.info("Sending message [type=" + message.getMessageType() + ", recipient=" + getName() + ", timestamp=" + message.getCreationTime() + ", content=" + message.getContent().toString() + "]");
			getSession().send(getMessageCodec().encodeMessage(message));
		}
		catch (final Exception e)
		{
			log.error(e);
			throw new MessageException(e);
		}
	}

	@Override
	public boolean disconnect()
	{
		if (getSession() == null)
		{
			//			log.debug(DrakeManager.getManager(ResourceBundleManager.class).getMessage(BundleServerBase.PlayerNotConnected, getName()));

			return false;
		}

		setSession(null);

		return true;
	}

	@Override
	public void leave()
	{
	}

	@Override
	public final void setClientListener(final ClientListener listener)
	{
		this.listener = listener;
	}
}