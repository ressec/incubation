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

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.nio.ByteBuffer;
import java.util.Properties;

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.MessageException;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.heliosphere.drake.base.player.AbstractPlayer;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;
import org.heliosphere.drake.client.IClient;
import org.heliosphere.drake.client.resource.bundle.BundleClient;
import org.heliosphere.drake.client.type.ClientPropertiesType;

import com.sun.sgs.client.ClientChannel;
import com.sun.sgs.client.ClientChannelListener;
import com.sun.sgs.client.simple.SimpleClient;

import lombok.extern.log4j.Log4j;

/**
 * Abstract implementation of a player located on the client side.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j
public abstract class AbstractClientPlayer extends AbstractPlayer implements IClientPlayer
{
	/**
	 * Client session used to communicate with the server.
	 */
	protected SimpleClient session = null;

	/**
	 * Authentication vault.
	 */
	private PasswordAuthentication authentication;

	/**
	 * Parent application (being a client).
	 */
	protected IClient application = null;

	/**
	 * Message codec.
	 */
	private IMessageCodec codec = null;

	/**
	 * Creates a new abstract player located on the client side.
	 * <p>
	 * @param application {@link IClient} being the parent application.
	 */
	@SuppressWarnings("nls")
	public AbstractClientPlayer(final IClient application)
	{
		Validate.notNull(application, "Client application cannot be null!");

		this.application = application;

		try
		{
			codec = Manager.getMessageManager().getMessageCodec();
		}
		catch (MessageManagerException e)
		{
			// TODO Throw an ApplicationInitializationException here!
		}

		initialize();
	}

	/**
	 * Initializes the client player.
	 */
	protected void initialize()
	{
		session = new SimpleClient(this);
	}

	@Override
	public void sendMessage(final IMessage message) throws MessageException
	{
		try
		{
			session.send(codec.encodeMessage(message));
		}
		catch (IOException e)
		{
			throw new MessageException(e);
		}
	}

	@Override
	public final PasswordAuthentication getPasswordAuthentication()
	{
		return authentication;
	}

	@Override
	public void setAuthentication(final String identifier, final String password)
	{
		authentication = new PasswordAuthentication(identifier, password.toCharArray());
	}

	@SuppressWarnings("nls")
	@Override
	public void loggedIn()
	{
		String message = ResourceBundleManager.getMessage(
				BundleClient.UserConnected,
				authentication.getUserName(),
				application.getProperty(ClientPropertiesType.ServerHostName),
				application.getProperty(ClientPropertiesType.ServerPortNumber));

		log.info(message);
		application.getTerminal().getDevice().printf("%s", message);
	}

	@SuppressWarnings("nls")
	@Override
	public void loginFailed(final String reason)
	{
		String message = ResourceBundleManager.getMessage(
				BundleClient.UserLoginFailed,
				authentication.getUserName(),
				application.getProperty(ClientPropertiesType.ServerHostName),
				application.getProperty(ClientPropertiesType.ServerPortNumber),
				reason);

		log.error(message);
		application.getTerminal().getDevice().printf("%s", message);
	}

	@Override
	public ClientChannelListener joinedChannel(final ClientChannel channel)
	{
		log.info(ResourceBundleManager.getMessage(BundleClient.UserJoinedChannel, channel.getName()));

		return null;
	}

	@Override
	public void receivedMessage(final ByteBuffer encoded)
	{
		try
		{
			handleMessage(codec.decodeMessage(encoded));
		}
		catch (MessageDecodingException e)
		{
			log.error(e);
		}
	}

	@SuppressWarnings("nls")
	@Override
	public void reconnecting()
	{
		String message = ResourceBundleManager.getMessage(
				BundleClient.UserReconnecting,
				authentication.getUserName(),
				application.getProperty(ClientPropertiesType.ServerHostName),
				application.getProperty(ClientPropertiesType.ServerPortNumber));

		log.info(message);
		application.getTerminal().getDevice().printf("%s", message);
	}

	@SuppressWarnings("nls")
	@Override
	public void reconnected()
	{
		String message = ResourceBundleManager.getMessage(
				BundleClient.UserReconnected,
				authentication.getUserName(),
				application.getProperty(ClientPropertiesType.ServerHostName),
				application.getProperty(ClientPropertiesType.ServerPortNumber));

		log.info(message);
		application.getTerminal().getDevice().printf("%s", message);
	}

	@SuppressWarnings("nls")
	@Override
	public void disconnected(final boolean graceful, final String reason)
	{
		final String message = ResourceBundleManager.getMessage(
				BundleClient.UserDisconnected,
				authentication.getUserName(),
				application.getProperty(ClientPropertiesType.ServerHostName),
				application.getProperty(ClientPropertiesType.ServerPortNumber),
				Boolean.valueOf(graceful),
				reason);

		log.info(message);
		application.getTerminal().getDevice().printf("%s", message);
	}

	@SuppressWarnings("nls")
	@Override
	public void connect(final String identifier, final String password) throws IOException
	{
		if (!isConnected())
		{
			authentication = new PasswordAuthentication(identifier, password.toCharArray());
			setName(authentication.getUserName());

			log.info(ResourceBundleManager.getMessage(
					BundleClient.UserConnecting,
					authentication.getUserName(),
					application.getProperty(ClientPropertiesType.ServerHostName),
					application.getProperty(ClientPropertiesType.ServerPortNumber)));

			final Properties properties = new Properties();
			properties.put("host", application.getProperty(ClientPropertiesType.ServerHostName));
			properties.put("port", application.getProperty(ClientPropertiesType.ServerPortNumber));

			session.login(properties);
		}
	}

	@Override
	public void handleMessage(final IMessage message)
	{
		log.info("Message received [type=" + message.getMessageType() + ", content=" + message.getContent().toString() + "]");
	}

	@Override
	public void disconnect()
	{
		if (session.isConnected())
		{
			session.logout(false);
		}
	}

	@Override
	public final SimpleClient getSession()
	{
		return session;
	}

	@Override
	public final boolean isConnected()
	{
		return session.isConnected();
	}

	@Override
	public final IMessageCodec getMessageCodec()
	{
		return codec;
	}
}
