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

import org.heliosphere.drake.base.terminal.device.IDevice;

/**
 * Interface defining the behavior of a terminal. A terminal is a text
 * application where the user can submit commands.
 * <hr>
 * <dl>
 * <dt>Date:</dt>
 * <dd>$Date: 2013-01-28 16:36:32 +0100 (Mon, 28 Jan 2013) $</dd>
 * <dt>Comitter:</dt>
 * <dd>$Author: Christophe Resse $</dd>
 * <dt>Revision:</dt>
 * <dd>$Revision: 138 $</dd>
 * </dl>
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ITerminal extends Runnable
{
    /**
     * Adds a terminal listener that want to be notified when events occured on
     * the terminal.
     * <p>
     * @param listener Listener to add.
     */
    void addListener(final ITerminalListener listener);
    
    /**
     * Removes a terminal listener.
     * <p>
     * @param listener Listener to remove.
     */
    void removeListener(final ITerminalListener listener);
    
    /**
     * Returns the default device for the terminal.
     * <p>
     * @return {@link IDevice}.
     */
    IDevice getDevice();
    
    /**
     * Starts the terminal.
     */
    void start();
    
    /**
     * Stops the terminal by killing the dedicated underlying console thread.
     */
    void stop();
    
    /**
     * Suspends the terminal.
     */
    void suspend();
    
    /**
     * Resumes the terminal.
     */
    void resume();
}
