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

import lombok.EqualsAndHashCode;

/**
 * Test class representing an object containing other objects.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@EqualsAndHashCode
public class DataObject implements Serializable
{
	/**
	 * Serialization version identifier.
	 */
	public final static long serialVersionUID = 1L;

	/**
	 * A {@link PrimitiveObject}.
	 */
	protected PrimitiveObject object1 = null;

	/**
	 * A {@link PrimitiveObject}.
	 */
	protected PrimitiveObject object2 = null;

	/**
	 * A {@link DataObject}.
	 */
	protected DataObject object3 = null;

	/**
	 * Creates a new data object.
	 */
	public DataObject()
	{
	}

	/**
	 * Returns the object #1.
	 * <p>
	 * @return {@link PrimitiveObject}.
	 */
	public final PrimitiveObject getObject1()
	{
		return object1;
	}

	/**
	 * Sets the object #1.
	 * <p>
	 * @param o {@link PrimitiveObject} to set.
	 */
	public final void setObject1(final PrimitiveObject o)
	{
		object1 = o;
	}

	/**
	 * Returns the object #2.
	 * <p>
	 * @return {@link PrimitiveObject}.
	 */
	public final PrimitiveObject getObject2()
	{
		return object2;
	}

	/**
	 * Sets the object #2.
	 * <p>
	 * @param o {@link PrimitiveObject} to set.
	 */
	public final void setObject2(final PrimitiveObject o)
	{
		object2 = o;
	}

	/**
	 * Returns the object #3.
	 * <p>
	 * @return {@link DataObject}.
	 */
	public final DataObject getObject3()
	{
		return object3;
	}

	/**
	 * Sets the object #3.
	 * <p>
	 * @param o {@link DataObject} to set.
	 */
	public final void setObject3(final DataObject o)
	{
		object3 = o;
	}
}