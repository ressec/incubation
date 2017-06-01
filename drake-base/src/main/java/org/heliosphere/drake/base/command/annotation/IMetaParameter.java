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

/**
 * Interface defining the behavior of a meta-parameter. A meta parameter is a
 * command parameter created from a method variable annotated with the
 * {@code Parameter} annotation.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMetaParameter
{
	/**
	 * Returns the command parameter informations.
	 * <p>
	 * @return Parameter informations.
	 */
	IParameterInfo getInfo();

	/**
	 * Returns the command parameter value class.
	 * <p>
	 * @return Parameter value class.
	 */
	Class<?> getValueClass();
}
