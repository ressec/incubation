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
package org.heliosphere.drake.server.entity;

import java.io.Serializable;
import java.math.BigInteger;

import org.heliosphere.drake.base.message.codec.IMessageCodec;

import com.sun.sgs.app.ManagedObject;

/**
 * Interface defining the behavior of server side classes being persisted by
 * {@code SGS} server and being able to communicate over the network using
 * messages.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Resse Christophe</a>
 * @version 1.0.0
 */
public interface IServerEntity extends ManagedObject, Serializable
{
	/**
	 * Returns the name of the server entity.
	 * <p>
	 * @return Entity name.
	 */
	String getName();

	/**
	 * Sets the name of the server entity.
	 * <p>
	 * @param name Entity name.
	 */
	void setName(final String name);

	/**
	 * Returns the binding name of the server entity.
	 * <p>
	 * @return Binding name.
	 */
	String getBindingName();

	/**
	 * Returns the object/entity identifier also called the {@code OID}.
	 * <p>
	 * @return Object identifier.
	 * @throws Exception In case an error occured while getting the object
	 * identifier.
	 */
	BigInteger getId() throws Exception;

	/**
	 * Returns the message codec used to serialize/de-serialize messages and
	 * their content.
	 * <p>
	 * @return {@link IMessageCodec}.
	 */
	IMessageCodec getMessageCodec();
}
