/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the
 * Apache license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project's artifact binaries and/or
 * sources.
 *
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.demeter2.base.resource.bundle;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.heliosphere.demeter2.base.annotation.BundleEnumRegister;

import eu.infomas.annotation.AnnotationDetector.TypeReporter;

/**
 * Class used to visit all annotated classes available on the classpath that are
 * annotated with the {@link BundleEnumRegister} annotation.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public final class BundleEnumRegisterVisitor implements TypeReporter
{

	/**
	 * Collection of bundles to auto-register (by priority).
	 */
	private Map<Integer, List<String>> bundles = new TreeMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Annotation>[] annotations()
	{
		return new Class[] { BundleEnumRegister.class };
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className)
	{
		List<String> classes = null;
		Integer key = null;

		Class<? extends IBundle> bClass;
		try
		{
			bClass = (Class<? extends IBundle>) Class.forName(className);
			BundleEnumRegister a = bClass.getAnnotation(BundleEnumRegister.class);
			key = Integer.valueOf(a.priority());
			classes = (List<String>) (bundles.containsKey(key) ? bundles.get(key) : new ArrayList<>());
			classes.add(className);
			bundles.put(Integer.valueOf(a.priority()), classes);
		}
		catch (ClassNotFoundException e)
		{
			// TODO Handle correctly!
			e.printStackTrace();
		}
	}

	/**
	 * Delegates registration of annotated resource bundle classes.
	 * <hr>
	 * @throws ClassNotFoundException Thrown in case a resource bundle enumeration
	 * class annotated with the {@link BundleEnumRegister} annotation is not found.
	 */
	@SuppressWarnings("unchecked")
	public void delegateRegistration() throws ClassNotFoundException
	{
		for (Integer i : bundles.keySet())
		{
			for (String e : bundles.get(i))
			{
				ResourceBundleManager.register((Class<? extends IBundle>) Class.forName(e));
			}
		}
	}
}
