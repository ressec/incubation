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
package org.heliosphere.drake.server.resource.bundle;

import org.heliosphere.drake.base.resource.bundle.IBundle;
import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration of the server resource bundles. Each enumerated value maps to a
 * property in the server resource bundle files.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public enum BundleServer implements IBundle
{
	/**
	 * Bundle name.
	 * <p>
	 * This enumerated value should always be created and must point to the name
	 * of the resource bundle so that the {@link ResourceBundleManager} can load
	 * and register the resource bundle files of the {@link BundleServer} class.
	 */
	BundleFilename("bundle.drake-server"),

	/**
	 * Root for all messages of the {@code BundleServer} component.
	 * <p>
	 * <b>Note:</b><br>
	 * Must have the name of the project artifact followed by the dot (.)
	 * character.
	 */
	BundleRoot("drake-server."),

	/*
	 * ---------- Test.* ----------
	 */

	/**
	 * A dummy message from component: drake-server.
	 */
	TestDummy("test.dummy"),

	/*
	 * ---------- Properties.* ----------
	 */

	/**
	 * {@code SGS} server application name.
	 */
	PropertyApplicationName("com.sun.sgs.app.name"),

	/**
	 * {@code SGS} server tcp listening port number.
	 */
	PropertyPortNumber("com.sun.sgs.impl.transport.tcp.listen.port"),

	/**
	 * {@code SGS} server application listener.
	 */
	PropertyApplicationListener("com.sun.sgs.app.listener"),

	/**
	 * {@code SGS} server application root directory.
	 */
	PropertyApplicationRoot("com.sun.sgs.app.root"),

	/**
	 * {@code SGS} server services.
	 */
	PropertyServices("com.sun.sgs.services"),

	/**
	 * {@code SGS} server managers.
	 */
	PropertyManagers("com.sun.sgs.managers"),

	/**
	 * {@code SGS} server task delay.
	 */
	PropertyTaskDelay("com.sun.sgs.task.delay"),

	/**
	 * {@code SGS} server task period.
	 */
	PropertyTaskPeriod("com.sun.sgs.task.period"),

	/**
	 * {@code SGS} transaction timeout.
	 */
	PropertyTransactionTimeout("com.sun.sgs.txn.timeout"),

	/**
	 * Server version.
	 */
	PropertyServerVersion("org.heliosphere.drake.application.version"),

	/**
	 * Server copyright.
	 */
	PropertyServerCopyright("org.heliosphere.drake.application.copyright"),

	/**
	 * {@code SGS} server message protocol class name.
	 */
	PropertyMessageProtocolClassname("property.message.protocol.classname"),

	/**
	 * {@code SGS} server message encoder type.
	 */
	PropertyMessageEncoderType("property.message.encoder"),

	/**
	 * {@code SGS} server game class name.
	 */
	PropertyGameClassname("property.game.classname"),

	/**
	 * {@code SGS} server game name.
	 */
	PropertyGameName("property.game.name");

	/**
	 * Resource bundle key.
	 */
	private final String key;

	/**
	 * Creates a new enumerated value based on the resource bundle key.
	 * <p>
	 * @param key Resource bundle key.
	 */
	private BundleServer(final String key)
	{
		this.key = key;
	}

	@Override
	public final String getKey()
	{
		if (!key.equals(BundleServer.BundleRoot.key) && !key.equals(BundleServer.BundleFilename.key))
		{
			return BundleServer.BundleRoot.getKey() + key;
		}

		return key;
	}

	@Override
	public final String getValue()
	{
		return ResourceBundleManager.getMessage(this);
	}
}
