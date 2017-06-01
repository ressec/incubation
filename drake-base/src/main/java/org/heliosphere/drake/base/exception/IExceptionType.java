/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.exception;

/**
 * Interface to be implemented by all exception enumeration classes. This
 * interface allows to create exceptions based on an enumerated value such as in
 * the following example:
 * <p>
 * <code>
 * throw new ApplicationException(ApplicationExceptionType.AlreadyRunning);
 * </code>
 * <p>
 * The use of this syntax requires that the {@code ApplicationExceptionType}
 * enumeration class implements the {@code IExceptionEnum} interface.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IExceptionType
{
	/**
	 * Returns the messsage.
	 * <p>
	 * @return Message.
	 */
	public String getMessage();
}
