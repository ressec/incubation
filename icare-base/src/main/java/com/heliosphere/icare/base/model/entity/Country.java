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
package com.heliosphere.icare.base.model.entity;

import com.heliosphere.demeter2.base.annotation.Copyright;
import com.heliosphere.demeter2.base.annotation.License;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import com.neovisionaries.i18n.LanguageCode;
import com.neovisionaries.i18n.LocaleCode;
import com.neovisionaries.i18n.ScriptCode;

import lombok.Data;
import lombok.NonNull;

/**
 * Represents a {@code Country}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Copyright(company="Heliosphere Corp.", year=2016, author="Resse Christophe")
@License(license="Apache", version="2.0", url="http://www.apache.org/licenses/LICENSE-2.0")
@Data
public final class Country
{
	/**
	 * Country Code.
	 */
	private CountryCode countryCode;

	/**
	 * Country language Code.
	 */
	private LanguageCode languageCode;

	/**
	 * Country currency Code.
	 */
	private CurrencyCode currencyCode;

	/**
	 * Country script code.
	 */
	private ScriptCode scriptCode;

	/**
	 * Country locale code.
	 */
	private LocaleCode localeCode;


	/**
	 * Creates a country based on its country code.
	 * <hr>
	 * @param code Country code according to the {@code ISO 3166-1} country codes.
	 */
	public Country(@NonNull final String code)
	{
		countryCode = CountryCode.valueOf(code);
		localeCode = LocaleCode.getByCountry(countryCode).get(0);
		languageCode = localeCode.getLanguage();
		currencyCode = CurrencyCode.getByCountry(countryCode).get(0);
	}

	/**
	 * Creates a country based on its country code.
	 * <hr>
	 * @param code Country code according to the {@code ISO 3166-1} country codes.
	 */
	public Country(@NonNull final CountryCode code)
	{
		countryCode = code;
		localeCode = LocaleCode.getByCountry(countryCode).get(0);
		languageCode = localeCode.getLanguage();
		currencyCode = CurrencyCode.getByCountry(countryCode).get(0);
	}
}
