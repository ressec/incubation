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

import java.math.BigInteger;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.codec.IMessageCodec;
import org.heliosphere.drake.base.message.manager.MessageManagerException;

import com.sun.sgs.app.AppContext;

/**
 * Abstract implementation of a server entity.
 * <hr>
 * <dl>
 * <dt>Date:</dt>
 * <dd>10 avr. 2013 - 13:50:29</dd>
 * </dl>
 * @author <a href="mailto:christophe.resse@hotmail.com">Resse Christophe</a>
 * @version 1.0.0
 */
@Log4j
public abstract class AbstractServerEntity implements IServerEntity
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Entity name.
	 */
	private String name;

	/**
	 * Message codec.
	 */
	private transient IMessageCodec codec;

	/**
	 * Binding prefix for this entity.
	 */
	@SuppressWarnings("nls")
	private String BINDING_PREFIX = "NULL";

	/**
	 * Creates a new abstract server entity.
	 * <p>
	 * @param bindingPrefix Binding prefix.
	 * @param name Entity name.
	 */
	public AbstractServerEntity(final String bindingPrefix, final String name)
	{
		BINDING_PREFIX = bindingPrefix;
		this.name = name;

		try
		{
			codec = Manager.getMessageManager().getMessageCodec();
		}
		catch (MessageManagerException e)
		{
			log.error(e);
		}
	}

	@Override
	public final String getName()
	{
		return name;
	}

	@Override
	public final void setName(final String name)
	{
		this.name = name;
	}

	@Override
	public final String getBindingName()
	{
		return BINDING_PREFIX + getName();
	}

	@SuppressWarnings("nls")
	@Override
	public final BigInteger getId()
	{
		try
		{
			return AppContext.getDataManager().getObjectId(this);
		}
		catch (final Exception e)
		{
			log.error("Cannot retrieve OID for: " + getBindingName() + " due to: " + e.getMessage());

			return BigInteger.valueOf(0L); // Invalid OID
		}
	}

	@Override
	public final IMessageCodec getMessageCodec()
	{
		return codec;
	}
}
