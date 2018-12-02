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
import com.neovisionaries.i18n.CurrencyCode;

import lombok.extern.log4j.Log4j;

/**
 * A command line interface {@code CLI} to list currencies according to the {@code com.neovisionaries.nv-i18n}
 * library.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @see {@code ISO 4217} currency codes.
 */
@SuppressWarnings("javadoc")
@Log4j
@Copyright(company="Heliosphere Corp.", year=2016, author="Resse Christophe")
@License(license="Apache", version="2.0", url="http://www.apache.org/licenses/LICENSE-2.0")
public class CommandLineListCurrency
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
	@Option(required=true, name="-code", handler=StringOptionHandler.class, usage="Currency code to retrieve.")
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
			new CommandLineListCurrency().CommandLine(args);
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
			doListCurrency(code);
		}
		else
		{
			doListAllCurrency();
		}
	}

	/**
	 * List the currency on the console given its code.
	 * <hr>
	 * @param code Currency code.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	private final static void doListCurrency(final String code)
	{
		CurrencyCode currency = CurrencyCode.valueOf(code);

		System.out.format("[%s] %03d %s\n", code, currency.getNumeric(), currency.getName());
	}


	/**
	 * List all the currencies codes on the console.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	private final static void doListAllCurrency()
	{
		// List all the currency codes.
		for (CurrencyCode code : CurrencyCode.values())
		{
			System.out.format("[%s] %03d %s\n", code, code.getNumeric(), code.getName());
		}
	}
}
