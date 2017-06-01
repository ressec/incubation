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

import lombok.Getter;
import lombok.Setter;

/**
 * Contains public information for a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Getter
@Setter
public final class CommandInfo implements ICommandInfo
{
	/**
	 * Command name.
	 */
	private String name;

	/**
	 * Command prefix.
	 */
	private String prefix;

	/**
	 * Command version.
	 */
	private String version;

	/**
	 * Command author.
	 */
	private String author;

	/**
	 * Command copyright.
	 */
	private String copyright;

	/**
	 * Command description.
	 */
	private String description;

	/**
	 * Command aliases.
	 */
	private String[] aliases;

	/**
	 * Command arity.
	 */
	private int arity;

	@Override
	public IParameterInfo getParameterInfo(final int position)
	{
		return null;
	}

	@Override
	public List<IParameterInfo> getParameterInfo()
	{
		return null;
	}
}
