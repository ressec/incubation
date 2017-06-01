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
package org.heliosphere.drake.base.event;

import java.util.Hashtable;
import java.util.Map;

import org.heliosphere.drake.base.event.type.IEventType;

/**
 * Abstract implementation of an event.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractEvent implements IEvent
{
	/**
	 * Event type.
	 */
	protected Enum<? extends IEventType> type = null;

	/**
	 * Event properties collection.
	 */
	private Map<String, Object> properties = null;

	/**
	 * Creates a new abstract event type.
	 * <p>
	 * @param type Event type.
	 */
	public AbstractEvent(final Enum<? extends IEventType> type)
	{
		this.type = type;
	}

	@Override
	public Enum<? extends IEventType> getType()
	{
		return type;
	}

	@Override
	public final void setProperty(final String key, final Object value)
	{
		if (properties == null)
		{
			properties = new Hashtable<>();
		}

		properties.put(key, value);
	}

	@Override
	public final Object getProperty(final String key)
	{
		if (properties != null)
		{
			return properties.get(key);
		}

		return null;
	}
}
