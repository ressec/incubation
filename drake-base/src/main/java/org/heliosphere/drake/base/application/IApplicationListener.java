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
package org.heliosphere.drake.base.application;

import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;

/**
 * Listener interface for receiving {@link IApplication} events.
 * <p>
 * The class that is interested in processing {@link IApplication} events must
 * implement this interface and the object created with that class is registered
 * with the {@link IApplication} object using the
 * {@link IApplication#addListener(IApplicationListener)} method.
 * <p>
 * When an {@link IApplication} event occurs, the object's appropriate method is
 * invoked.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IApplicationListener
{
	/**
	 * Invoked before an application starts.
	 * <p>
	 * When invoked, the terminal is available.
	 */
	void onStart();

	/**
	 * Invoked before an application quits. Event generally triggered when an
	 * {@code exit} command has been received.
	 * <p>
	 * @param code Application exit code.
	 */
	void onQuit(final Enum<? extends IApplicationExitCodeType> code);
}
