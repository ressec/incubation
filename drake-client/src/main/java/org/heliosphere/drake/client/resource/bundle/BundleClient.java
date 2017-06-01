/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.client.resource.bundle;

import org.heliosphere.drake.base.resource.bundle.IBundle;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration of the client resource bundles. Each enumerated value maps to a
 * property in the client resource bundle files.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public enum BundleClient implements IBundle
{
	/**
	 * Bundle name.
	 * <p>
	 * This enumerated value should always be created and must point to the name
	 * of the resource bundle so that the {@link ResourceBundleManager} can load
	 * and register the resource bundle files of the {@link BundleClient} class.
	 */
	BundleFilename("bundle.drake-client"),

	/**
	 * Root for all messages of the {@code BundleClient} component.
	 * <p>
	 * <b>Note:</b><br>
	 * Must have the name of the project artifact followed by the dot (.)
	 * character.
	 */
	BundleRoot("drake-client."),

	/*
	 * ---------- Test.* ----------
	 */

	/**
	 * A dummy message from component: drake-client.
	 */
	TestDummy("test.dummy"),

	/*
	 * ---------- User.* ----------
	 */

	/**
	 * Message indicating a user is connecting to a server.
	 */
	UserConnecting("user.connecting"),

	/**
	 * Message indicating a user is connected to a server.
	 */
	UserConnected("user.connected"),

	/**
	 * Message indicating a user is disconnected from a server.
	 */
	UserDisconnected("user.disconnected"),

	/**
	 * Message indicating a user joined a channel.
	 */
	UserJoinedChannel("user.joined.channel"),

	/**
	 * Message indicating a user login failed.
	 */
	UserLoginFailed("user.loginFailed"),

	/**
	 * Message indicating a user is reconnecting.
	 */
	UserReconnecting("user.reconnecting"),

	/**
	 * Message indicating a user is reconnected.
	 */
	UserReconnected("user.reconnected"),

	/**
	 * Message indicating a user is already connected.
	 */
	UserAlreadyConnected("user.alreadyConnected"),

	/*
	 * ---------- Properties.* ----------
	 */

	/**
	 * Hostname of the server to connect to.
	 */
	PropertyServerHostname("property.server.hostname"),

	/**
	 * Port number of the server to connect to.
	 */
	PropertyServerPort("property.server.port");

	/**
	 * Resource bundle key.
	 */
	private final String key;

	/**
	 * Creates a new enumerated value based on the resource bundle key.
	 * <p>
	 * @param key Resource bundle key.
	 */
	private BundleClient(final String key)
	{
		this.key = key;
	}

	@Override
	public final String getKey()
	{
		if (!key.equals(BundleClient.BundleRoot.key) && !key.equals(BundleClient.BundleFilename.key))
		{
			return BundleClient.BundleRoot.getKey() + key;
		}

		return key;
	}

	@Override
	public final String getValue()
	{
		return ResourceBundleManager.getMessage(this);
	}
}
