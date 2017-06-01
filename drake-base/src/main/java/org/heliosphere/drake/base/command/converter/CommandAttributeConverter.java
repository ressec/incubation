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
package org.heliosphere.drake.base.command.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A command parameter converter to convert from a {@link String} to a
 * {@link CommandAttribute}.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class CommandAttributeConverter implements IInputConverter
{
	/**
	 * Regular expression.
	 */
	@SuppressWarnings("nls")
	private final String expression = "-{1}([a-z]{1})(([a-zA-Z0-9*?%]+):([a-zA-Z0-9*?%]+)|[a-zA-Z0-9*?%]+)";

	@SuppressWarnings("nls")
	@Override
	public Object convert(final String value, final Class<?> clazz) throws Exception
	{
		Object result = null;

		if (clazz == CommandAttribute.class)
		{
			if (value != null && !value.isEmpty())
			{
				Pattern pattern = Pattern.compile(expression);
				Matcher matcher = pattern.matcher(value);

				if (matcher.matches())
				{
					result = new CommandAttribute();

					// Group #1 = Option
					if (matcher.group(1) != null)
					{
						((CommandAttribute) result).setOption(new String(matcher.group(1)));
					}

					// Group #2 = Value
					if (matcher.group(1) != null && !matcher.group(2).contains(":"))
					{
						((CommandAttribute) result).setMinimum(new String(matcher.group(2)));
					}
					else
					{
						// Group #3 = Minimum value
						if (matcher.group(3) != null)
						{
							((CommandAttribute) result).setMinimum(new String(matcher.group(3)));
						}

						// Group #4 = Maximum value
						if (matcher.group(4) != null)
						{
							((CommandAttribute) result).setMaximum(new String(matcher.group(4)));
						}
					}
				}
			}
		}

		return result;
	}
}
