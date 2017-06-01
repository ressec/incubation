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
package org.heliosphere.drake.base.terminal;

import org.heliosphere.drake.base.command.annotation.IMetaCommand;

/**
 * Listener interface for receiving {@link ITerminal} events.
 * <p>
 * The class that is interested in processing {@link ITerminal} events must
 * implement this interface and the object created with that class is registered
 * with the {@link ITerminal} object using the
 * {@link ITerminal#addListener(ITerminalListener)} method.
 * <p>
 * When an {@link ITerminal} event occurs, the object's appropriate method is
 * invoked.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ITerminalListener
{
	/**
	 * Invoked each time a command is entered on the terminal.
	 * <p>
	 * @param command Command entered on the terminal.
	 */
	void onCommand(final IMetaCommand command);

	/**
	 * Invoked each time an exception occured on the terminal.
	 * <p>
	 * @param e Exception.
	 */
	void onException(final Exception e);
}
