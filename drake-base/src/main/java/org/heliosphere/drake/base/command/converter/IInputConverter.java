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
package org.heliosphere.drake.base.command.converter;

/**
 * Interface defining the behavior of an input converter used for converting
 * command parameters.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IInputConverter
{
	/**
	 * Converts a command input parameter.
	 * <p>
	 * @param value Command parameter value to convert.
	 * @param clazz Class of the object the parameter value must be converted
	 * to.
	 * @return Converted paremeter.
	 * @throws Exception Thrown if an error occurs while trying to convert the
	 * parameter value.
	 */
	Object convert(final String value, final Class<?> clazz) throws Exception;
}