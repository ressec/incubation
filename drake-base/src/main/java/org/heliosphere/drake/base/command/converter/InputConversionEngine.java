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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.heliosphere.drake.base.command.CommandException;
import org.heliosphere.drake.base.command.annotation.IMetaCommand;
import org.heliosphere.drake.base.command.token.Token;
import org.heliosphere.drake.base.command.token.TokenException;

/**
 * Engine to convert command attribute.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class InputConversionEngine
{
	/**
	 * Converters
	 */
	private List<IInputConverter> converters = new ArrayList<>();

	/**
	 * Adds the converter.
	 * <p>
	 * @param converter Input converter to add.
	 */
	public void addConverter(final IInputConverter converter)
	{
		Validate.notNull(converter, "Converter cannot be null");

		converters.add(converter);
	}

	/**
	 * Removes the given input converter.
	 * <p>
	 * @param converter The converter.
	 * @return Returns <code>True</code> if success, <code>False</code>
	 * otherwise.
	 */
	public boolean removeConverter(final IInputConverter converter)
	{
		Validate.notNull(converter, "Converter cannot be null");

		return converters.remove(converter);
	}

	/**
	 * Converts input.
	 * <p>
	 * @param value The value.
	 * @param clazz The clazz.
	 * @return Returns the object.
	 * @throws Exception The exception.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	public Object convertInput(final String value, final Class<?> clazz) throws Exception
	{
		for (IInputConverter converter : converters)
		{
			Object result = converter.convert(value, clazz);

			if (result != null)
			{
				if (!clazz.isAssignableFrom(result.getClass()))
				{
					throw new CommandException("Registered converter: " + converter.getClass().getName() + " returns wrong result");
				}

				return result;
			}
		}

		return convertArgToElementaryType(value, clazz);
	}

	/**
	 * Convert to parameters.
	 * <p>
	 * @param tokens The tokens.
	 * @param command The command.
	 * @return Returns the object[].
	 * @throws TokenException The token exception.
	 */
	@SuppressWarnings("nls")
	public final Object[] convertToParameters(final List<Token> tokens, final IMetaCommand command) throws TokenException
	{
		Class<?>[] paramClasses = command.getMethod().getParameterTypes();

		assert ((command.getMethod().isVarArgs()) || (paramClasses.length == tokens.size() - 1));

		Object[] parameters = new Object[paramClasses.length];
		for (int i = 0; i < parameters.length - 1; i++)
		{
			try
			{
				parameters[i] = convertInput(tokens.get(i + 1).getString(), paramClasses[i]);
			}
			catch (CommandException ex)
			{
				throw new TokenException(tokens.get(i + 1).getString() + ". " + ex.getMessage());
			}
			catch (Exception e)
			{
				throw new TokenException(tokens.get(i + 1).getString(), e);
			}
		}
		int lastIndex = paramClasses.length - 1;
		if (command.getMethod().isVarArgs())
		{
			Class<?> varClass = paramClasses[lastIndex];
			assert (varClass.isArray());
			Class<?> elemClass = varClass.getComponentType();
			Object theArray = Array.newInstance(elemClass, tokens.size() - paramClasses.length);
			for (int i = 0; i < Array.getLength(theArray); i++)
			{
				try
				{
					Array.set(theArray, i, convertInput(tokens.get(lastIndex + 1 + i).getString(), elemClass));
				}
				catch (CommandException ex)
				{
					throw new TokenException(tokens.get(lastIndex + 1 + i).getString() + ". " + ex.getMessage());
				}
				catch (Exception e)
				{
					throw new TokenException(tokens.get(lastIndex + 1 + i).getString(), e);
				}
			}
			parameters[lastIndex] = theArray;
		}
		else if (lastIndex >= 0)
		{
			try
			{
				if (tokens.size() > lastIndex + 1)
				{
					if (tokens.get(lastIndex + 1).getString() == null && command.getParameters()[lastIndex].getInfo().isOptional())
					{
						// Do nothing because parameter is optional.
					}
					else
					{
						parameters[lastIndex] = convertInput(tokens.get(lastIndex + 1).getString(), paramClasses[lastIndex]);
					}
				}
				else
				{
					if (!command.getParameters()[lastIndex].getInfo().isOptional())
					{
						throw new CommandException("No value provided for parameter: " + lastIndex);
					}
				}
			}
			catch (CommandException ex)
			{
				throw new TokenException(tokens.get(lastIndex + 1).getString() + ". " + ex.getMessage());
			}
			catch (Exception e)
			{
				throw new TokenException(tokens.get(lastIndex + 1).getString(), e);
			}
		}

		return parameters;
	}

	/**
	 * Convert arg to elementary type.
	 * <p>
	 * @param string The string.
	 * @param aClass The a class.
	 * @return Returns the object.
	 * @throws CommandException The command exception.
	 */
	private static Object convertArgToElementaryType(final String string, final Class<?> aClass) throws CommandException
	{
		if ((aClass.equals(String.class)) || (aClass.isInstance(string)))
		{
			return string;
		}
		if ((aClass.equals(Integer.class)) || (aClass.equals(Integer.TYPE)))
		{
			return Integer.valueOf(Integer.parseInt(string));
		}
		if ((aClass.equals(Long.class)) || (aClass.equals(Long.TYPE)))
		{
			return Long.valueOf(Long.parseLong(string));
		}
		if ((aClass.equals(Double.class)) || (aClass.equals(Double.TYPE)))
		{
			return Double.valueOf(Double.parseDouble(string));
		}
		if ((aClass.equals(Float.class)) || (aClass.equals(Float.TYPE)))
		{
			return Float.valueOf(Float.parseFloat(string));
		}
		if ((aClass.equals(Boolean.class)) || (aClass.equals(Boolean.TYPE)))
		{
			return Boolean.valueOf(Boolean.parseBoolean(string));
		}
		try
		{
			Constructor<?> c = aClass.getConstructor(new Class[] { String.class });
			try
			{
				return c.newInstance(new Object[] { string });
			}
			catch (Exception ex)
			{
				throw new CommandException(String.format("Error instantiating class %c using string %s", new Object[] { aClass, string }), ex);
			}
		}
		catch (NoSuchMethodException e)
		{
			throw new CommandException("Can't convert from string to: " + aClass.getName());
		}
	}

	/**
	 * Adds the declared converters.
	 * <p>
	 * @param handler The handler.
	 */
	@SuppressWarnings("nls")
	public void addDeclaredConverters(final Object handler)
	{
		Field[] fields = handler.getClass().getFields();
		String PREFIX = "CLI_INPUT_CONVERTERS";
		for (Field field : fields)
		{
			if ((!field.getName().startsWith("CLI_INPUT_CONVERTERS")) || (!field.getType().isArray()) || (!IInputConverter.class.isAssignableFrom(field.getType().getComponentType())))
			{
				continue;
			}
			try
			{
				Object convertersArray = field.get(handler);
				for (int i = 0; i < Array.getLength(convertersArray); i++)
				{
					addConverter((IInputConverter) Array.get(convertersArray, i));
				}
			}
			catch (Exception ex)
			{
				throw new RuntimeException("Error getting converter from field " + field.getName(), ex);
			}
		}
	}
}