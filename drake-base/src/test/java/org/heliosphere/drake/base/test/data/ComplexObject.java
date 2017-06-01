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
package org.heliosphere.drake.base.test.data;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;

/**
 * Test class representing an object containing primitives, other objects and
 * collections of objects.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@EqualsAndHashCode
public class ComplexObject implements Serializable
{
	/**
	 * Serialization version identifier.
	 */
	public final static long serialVersionUID = 1L;

	/**
	 * Object containing only primitives.
	 */
	protected PrimitiveObject object = null;

	/**
	 * Integer primitive value.
	 */
	protected int integer = 0;

	/**
	 * Long object.
	 */
	protected Long longValue = null;

	/**
	 * List of objects.
	 */
	protected List<DataObject> list = null;

	/**
	 * Creates a new object.
	 */
	public ComplexObject()
	{
	}

	/**
	 * Returns the object.
	 * <p>
	 * @return {@link PrimitiveObject}.
	 */
	public final PrimitiveObject getObject()
	{
		return object;
	}

	/**
	 * Sets the object.
	 * <p>
	 * @param o {@link PrimitiveObject} to set.
	 */
	public final void setObject(final PrimitiveObject o)
	{
		object = o;
	}

	/**
	 * Returns the integer value.
	 * <p>
	 * @return Integer value.
	 */
	public final int getInteger()
	{
		return integer;
	}

	/**
	 * Sets the integer value.
	 * <p>
	 * @param value Integer value to set.
	 */
	public final void setInteger(final int value)
	{
		integer = value;
	}

	/**
	 * Returns the long value.
	 * <p>
	 * @return Long value.
	 */
	public final Long getLong()
	{
		return longValue;
	}

	/**
	 * Sets the long value.
	 * <p>
	 * @param value Long value to set.
	 */
	public final void setLong(final Long value)
	{
		longValue = value;
	}

	/**
	 * Returns the list.
	 * <p>
	 * @return List.
	 */
	public final List<DataObject> getList()
	{
		return list;
	}

	/**
	 * Sets the list.
	 * <p>
	 * @param list List to set.
	 */
	public final void setList(final List<DataObject> list)
	{
		this.list = list;
	}
}