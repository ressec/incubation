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
package org.heliosphere.drake.client.type;

import org.heliosphere.drake.base.application.type.IApplicationExitCodeType;

/**
 * Marker interface for client exit code type enumerations.
 * <p>
 * If a client application exit code type enumeration must be created, the new
 * created enumeration must to implement the {@link IClientExitCodeType} marker
 * interface.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IClientExitCodeType extends IApplicationExitCodeType
{
}
