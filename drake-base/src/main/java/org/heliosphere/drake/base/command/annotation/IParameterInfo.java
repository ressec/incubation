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
 * Interface defining the behavior of an information command parameter. These
 * informations are gathered from annotations placed on command parameters.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IParameterInfo
{
	/**
	 * Returns the command parameter name.
	 * <p>
	 * @return Parameter name.
	 */
	String getName();

	/**
	 * Returns the command parameter description.
	 * <p>
	 * @return Parameter description.
	 */
	String getDescription();

	/**
	 * Returns the command parameter position.
	 * <p>
	 * @return Parameter position.
	 */
	int getPosition();

	/**
	 * Returns if the command parameter is optional?
	 * <p>
	 * @return {@code True} if the command parameter is optional, {@code false}
	 * otherwise.
	 */
	boolean isOptional();
}
