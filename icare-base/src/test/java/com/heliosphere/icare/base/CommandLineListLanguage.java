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
package com.heliosphere.icare.base;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringOptionHandler;

import com.heliosphere.demeter2.base.annotation.Copyright;
import com.heliosphere.demeter2.base.annotation.License;
import com.neovisionaries.i18n.LanguageCode;

import lombok.extern.log4j.Log4j;

/**
 * A command line interface {@code CLI} to list languages according to the {@code com.neovisionaries.nv-i18n}
 * library.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @see {@code ISO 639-1} language codes.
 */
@SuppressWarnings("javadoc")
@Log4j
@Copyright(company="Heliosphere Corp.", year=2016, author="Resse Christophe")
@License(license="Apache", version="2.0", url="http://www.apache.org/licenses/LICENSE-2.0")
public class CommandLineListLanguage
{
	/**
	 * Arguments passed on the command line other than the options.
	 */
	@Argument
	private List<String> arguments = new ArrayList<>();

	/**
	 * Language code value.
	 */
	@SuppressWarnings("nls")
	@Option(required=true, name="-code", handler=StringOptionHandler.class, usage="Language code to retrieve.")
	private String code = "All";

	/**
	 * Command line interface ({@code CLI}) main entry point.
	 * <hr>
	 * @param args Arguments passed on the command line.
	 */
	@SuppressWarnings("nls")
	public static void main(String[] args)
	{
		try
		{
			new CommandLineListLanguage().CommandLine(args);
		}
		catch (CmdLineException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Executes the command line.
	 * <p>
	 * @param args Arguments passed on the command line.
	 * @throws CmdLineException Thrown in case an error occurred while parsing the command line.
	 */
	@SuppressWarnings("nls")
	private final void CommandLine(final String[] args) throws CmdLineException
	{
		CmdLineParser parser = new CmdLineParser(this);
		parser.parseArgument(args);

		if (!code.equalsIgnoreCase("All"))
		{
			doListLanguage(code);
		}
		else
		{
			doListAllLanguage();
		}
	}

	/**
	 * List the language on the console given its code.
	 * <hr>
	 * @param code language code.
	 */
	@SuppressWarnings("nls")
	private final static void doListLanguage(final String code)
	{
		LanguageCode languageCode = LanguageCode.valueOf(code);

		System.out.format("[%s] %s\n", languageCode, languageCode.getName());
	}


	/**
	 * List all the language codes on the console.
	 */
	@SuppressWarnings("nls")
	private final static void doListAllLanguage()
	{
		// List all the language codes.
		for (LanguageCode code : LanguageCode.values())
		{
			System.out.format("[%s] %s\n", code, code.getName());
		}
	}
}
