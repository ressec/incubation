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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to indicates that a method parameter is a command parameter.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Target({ java.lang.annotation.ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter
{
	/**
	 * Parameter name.
	 * <p>
	 * @return The parameter name.
	 */
	String name() default "";

	/**
	 * Parameter description.
	 * <p>
	 * Can be {@code null}.
	 * @return The parameter description.
	 */
	String description() default "";

	/**
	 * Is this command parameter required ({@code true}) or optional (
	 * {@code false})?
	 * @return If the parameter is required?
	 */
	boolean required() default false;
}
