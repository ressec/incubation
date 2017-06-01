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
package org.heliosphere.drake.base.command.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import lombok.Data;

/**
 * A meta command parameter entered using a terminal.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
public final class MetaParameter implements IMetaParameter
{
	/**
	 * Parameter information.
	 */
	private IParameterInfo info;

	/**
	 * Parameter value class.
	 */
	private Class<?> valueClass;

	/**
	 * Creates a new command parameter.
	 * <p>
	 * @param name Parameter name.
	 * @param optional Is parameter optional?
	 * @param valueClass Parameter value class.
	 * @param description Parameter description.
	 * @param position Parameter position.
	 */
	public MetaParameter(final String name, final boolean optional, final Class<?> valueClass, final String description, final int position)
	{
		info = new ParameterInfo(name, optional, description, position);

		this.valueClass = valueClass;
	}

	/**
	 * Extracts the command parameters related to the given method.
	 * <p>
	 * @param method Method.
	 * @return Array of {@link IMetaParameter}.
	 */
	@SuppressWarnings("nls")
	public static IMetaParameter[] forMethod(final Method method)
	{
		Class<?>[] types = method.getParameterTypes();
		IMetaParameter[] result = new MetaParameter[method.getParameterTypes().length];
		Annotation[][] annotations = method.getParameterAnnotations();
		assert (annotations.length == result.length);
		for (int i = 0; i < result.length; i++)
		{
			Parameter paramAnnotation = null;
			for (Annotation a : annotations[i])
			{
				if ((a instanceof Parameter))
				{
					paramAnnotation = (Parameter) a;
					break;
				}
			}
			if (paramAnnotation != null)
			{
				assert (!paramAnnotation.name().isEmpty()) : "@Param.name must not be empty";
				result[i] = new MetaParameter(paramAnnotation.name(), paramAnnotation.required(), types[i], paramAnnotation.description(), i);
			}
			else
			{
				result[i] = new MetaParameter(String.format("p%d", new Object[] { Integer.valueOf(i + 1) }), false, types[i], "", i);
			}
		}

		return result;
	}

	@Override
	public final IParameterInfo getInfo()
	{
		return info;
	}
}
