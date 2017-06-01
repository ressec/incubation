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

import org.heliosphere.drake.base.event.type.IEventType;

/**
 * Interface representing an event which is some generic notification
 * mechanism.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IEvent
{
	/**
	 * Returns the event type.
	 * <p>
	 * @return Event type.
	 */
	public Enum<? extends IEventType> getType();

	/**
	 * Sets a property for this event.
	 * <p>
	 * @param key Property key.
	 * @param value Property value.
	 */
	public void setProperty(final String key, final Object value);

	/**
	 * Retrieves a given property value.
	 * <p>
	 * @param key Property key.
	 * @return Property value.
	 */
	public Object getProperty(final String key);
}
