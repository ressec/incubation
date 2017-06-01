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
package org.heliosphere.drake.base.application.type;

/**
 * Enumeration of the application status types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ApplicationStatusType implements IApplicationStatusType
{
	/**
	 * Application in error.
	 */
	ERROR,

	/**
	 * Application is initializing.
	 */
	INITIALIZING,

	/**
	 * Application is initialized.
	 */
	INITIALIZED,

	/**
	 * Application is running.
	 */
	RUNNING,

	/**
	 * Application is updating.
	 */
	UPDATING,

	/**
	 * Application is waiting.
	 */
	WAITING,

	/**
	 * Application is busy.
	 */
	BUSY,

	/**
	 * Application is stopping.
	 */
	STOPPING,

	/**
	 * Application is stopped.
	 */
	STOPPED,

	/**
	 * Application is quitting.
	 */
	QUITTING;
}
