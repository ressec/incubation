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
 * This annotation is used to indicate that a method declares a command and its
 * associated handler. This annotation is to be put on a method that will be the
 * handler of the declared command.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Command
{
	/**
	 * Command name.
	 * <p>
	 * If {@code null}, the real method name will be used.
	 * @return The command name.
	 */
	String name() default "";

	/**
	 * Command description.
	 * <p>
	 * Can be {@code null}.
	 * @return The command description.
	 */
	String description() default "";

	/**
	 * Command prefix.
	 * <p>
	 * Can be {@code null} or one of the predefined prefix ; i.e. '#', '/', '.',
	 * ';', ':'.
	 * @return The command prefix.
	 */
	String prefix() default "";

	/**
	 * Command copyright.
	 * <p>
	 * Can be {@code null}.
	 * @return The command copyright.
	 */
	String copyright() default "";

	/**
	 * Command author.
	 * <p>
	 * Can be {@code null}.
	 * @return The command author.
	 */
	String author() default "";

	/**
	 * Command version.
	 * <p>
	 * Can be {@code null}.
	 * @return The command version.
	 */
	String version() default "";

	/**
	 * Command aliases.
	 * <p>
	 * Can be {@code null}.
	 * @return The command aliases.
	 */
	String[] aliases() default "";

	/**
	 * Command examples.
	 * <p>
	 * Can be {@code null}.
	 * @return The command examples.
	 */
	String[] examples() default "";
}
