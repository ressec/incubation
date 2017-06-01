/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.demeter.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.heliosphere.demeter.base.resource.bundle.IBundle;
import com.heliosphere.demeter.base.resource.bundle.ResourceBundleManager;

/**
 * This annotation is used to make a resource bundle enumeration file to be auto registered
 * by the {@link ResourceBundleManager}. This annotation is intended to be placed on enumeration 
 * types inheriting the {@link IBundle} interface.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BundleEnumRegister
{
	/**
	 * Priority to load the resource bundle enumeration. Lowest priority is loaded first ; 
	 * default is {@code 100}. 
	 * <hr>
	 * @return Priority to load the resource bundle enumeration.
	 */
	int priority() default 100;
}
