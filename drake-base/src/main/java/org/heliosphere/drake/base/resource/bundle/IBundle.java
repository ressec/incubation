/*
 * Copyright(c) 2010-2013 Heliosphere.
 * ---------------------------------------------------------------------------
 * This file is part of the RELIC foundation project which is licensed under the
 * Apache license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources ; if not visit: http://www.apache.org/licenses/LICENSE-2.0.html
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.resource.bundle;

/**
 * Marker interface for resource bundle enumerations.
 * <p>
 * Each new resource bundle enumeration must implements this marker interface to
 * be handled by the {@link ResourceBundleManager}.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IBundle
{
	/**
	 * Returns the resource bundle key corresponding to the enumerated value.
	 * <p>
	 * @return Resource bundle key.
	 */
	String getKey();

	/**
	 * Returns the resource bundle value according to the current locale.
	 * <p>
	 * @return Resource bundle value.
	 */
	String getValue();
}
