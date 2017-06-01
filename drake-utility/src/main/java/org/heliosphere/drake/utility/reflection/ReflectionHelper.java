package org.heliosphere.drake.utility.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelper
{
	public static List<Field> getInheritedFields(final Class<?> type)
	{
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass())
		{
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}
}
