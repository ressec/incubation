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
package org.heliosphere.drake.test;

/**
 * Helper class containing properties used for unit test benchmarking.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public final class BenchmarkHelper
{
	/**
	 * The Constant JUB_PROPERTY_CONSUMER.
	 */
	public final static String JUB_PROPERTY_CONSUMER = "jub.consumers";

	/**
	 * The Constant JUB_PROPERTY_CONSUMER_VALUE.
	 */
	public final static String JUB_PROPERTY_CONSUMER_VALUE = "CONSOLE,H2";

	/**
	 * The Constant JUB_PROPERTY_DBFILE.
	 */
	public final static String JUB_PROPERTY_DBFILE = "jub.db.file";

	/**
	 * The Constant JUB_PROPERTY_DBFILE_VALUE.
	 */
	public final static String JUB_PROPERTY_DBFILE_VALUE = ".benchmarks";

	/**
	 * The Constant JUB_PROPERTY_CHARTDIR.
	 */
	public final static String JUB_PROPERTY_CHARTDIR = "jub.charts.dir";

	/**
	 * The Constant JUB_PROPERTY_CHARTDIR_VALUE.
	 */
	public final static String JUB_PROPERTY_CHARTDIR_VALUE = "benchmark";
}
