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

/**
 * This annotation is used to apply a copyright to a type.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Copyright
{
	/**
	 * Copyright.
	 * <hr>
	 * @return The copyright.
	 */
	String copyright() default "";

	/**
	 * Company.
	 * <hr>
	 * @return The company holding the copyright.
	 */
	String company() default "";

	/**
	 * Author.
	 * <hr>
	 * Author name of the copyright.
	 * @return The command author.
	 */
	String author() default "";

	/**
	 * Year.
	 * <hr>
	 * @return The copyright year.
	 */
	int year();
}
