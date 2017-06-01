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
package org.heliosphere.drake.utility.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class to help create {@code callback} methods.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class Callback
{
	/**
	 * The name of the callback method.
	 */
	private final String methodName;

	/**
	 * The scope of the callback method (parent object).
	 */
	private final Object scope;

	/**
	 * Creates a new callback method for a given object.
	 * <p>
	 * @param scope Scope of the callback (its parent object).
	 * @param methodName Method name.
	 */
	public Callback(final Object scope, final String methodName)
	{
		this.methodName = methodName;
		this.scope = scope;
	}

	/**
	 * Invokes the callback method.
	 * <p>
	 * @param parameters Parameters.
	 * @return Result of the call if one.
	 * @throws InvocationTargetException The invocation target exception.
	 * @throws IllegalAccessException The illegal access exception.
	 * @throws NoSuchMethodException The no such method exception.
	 */
	public Object invoke(final Object... parameters) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException
	{
		final Method method = scope.getClass().getMethod(methodName, getParameterClasses(parameters));
		return method.invoke(scope, parameters);
	}

	/**
	 * Gets the parameter classes.
	 * <p>
	 * @param parameters Parameters.
	 * @return The class[].
	 */
	@SuppressWarnings("static-method")
	private Class<?>[] getParameterClasses(final Object... parameters)
	{
		final Class<?>[] classes = new Class[parameters.length];
		for (int i = 0; i < classes.length; i++)
		{
			classes[i] = parameters[i].getClass();
		}

		return classes;
	}
}
