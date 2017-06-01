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

import java.util.List;

/**
 * Defines the behavior of a command information. A command information is a
 * meta object that contains vital information of the command.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandInfo
{
	/**
	 * Returns the command prefix.
	 * <p>
	 * @return Command prefix.
	 */
	String getPrefix();

	/**
	 * Sets the prefix of the command.
	 * <p>
	 * @param prefix Command prefix.
	 */
	void setPrefix(final String prefix);

	/**
	 * Returns the command name.
	 * <p>
	 * @return Command name.
	 */
	String getName();

	/**
	 * Sets the name of the command.
	 * <p>
	 * @param name Command name.
	 */
	void setName(final String name);

	/**
	 * Returns the command aliases.
	 * <p>
	 * @return Command aliases.
	 */
	String[] getAliases();

	/**
	 * Returns the command's author.
	 * <p>
	 * @return Command author.
	 */
	String getAuthor();

	/**
	 * Sets the author of the command.
	 * <p>
	 * @param author Command author.
	 */
	void setAuthor(final String author);

	/**
	 * Returns the command description.
	 * <p>
	 * @return Command description.
	 */
	String getDescription();

	/**
	 * Sets the description of the command.
	 * <p>
	 * @param description Command description.
	 */
	void setDescription(final String description);

	/**
	 * Returns the command version.
	 * <p>
	 * @return Command version.
	 */
	String getVersion();

	/**
	 * Sets the version of the command.
	 * <p>
	 * @param version Command version.
	 */
	void setVersion(final String version);

	/**
	 * Returns the command arity.
	 * <p>
	 * @return Command arity.
	 */
	int getArity();

	/**
	 * Sets the arity of the command.
	 * <p>
	 * @param arity Command arity.
	 */
	void setArity(final int arity);

	/**
	 * Returns the information of a given command parameter.
	 * <p>
	 * @param position Parameter position.
	 * @return Command parameter information.
	 */
	IParameterInfo getParameterInfo(final int position);

	/**
	 * Returns a list of all the parameters' information of the command.
	 * <p>
	 * @return List of parameters' information of the command.
	 */
	List<IParameterInfo> getParameterInfo();
}
