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
package com.heliosphere.icare.model.entity.person.type;

import com.heliosphere.demeter.base.resource.bundle.ResourceBundleManager;
import com.heliosphere.demeter2.base.annotation.BundleEnum;
import com.heliosphere.demeter2.base.annotation.Copyright;
import com.heliosphere.demeter2.base.annotation.License;
import com.heliosphere.icare.base.type.IIcareType;
import com.neovisionaries.i18n.LanguageCode;

/**
 * Enumeration of the contact types.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Copyright(company="Heliosphere Corp.", year=2016, author="Resse Christophe")
@License(license="Apache", version="2.0", url="http://www.apache.org/licenses/LICENSE-2.0")
public enum ContactType implements IIcareType
{
	/**
	 * Contact made by phone.
	 */
	PHONE,

	/**
	 * Contact made by mail.
	 */
	MAIL,

	/**
	 * Contact made by the post.
	 */
	POST,

	/**
	 * Contact made by visit.
	 */
	VISIT;

	/**
	 * Returns the contact type name in the current language.
	 * <hr>
	 * @return Contact type name.
	 */
	@BundleEnum(file="bundle.icare-model-entity", path="icare-model-entity.enum.contact.type.name")
	public final String getName()
	{
		return ResourceBundleManager.getResourceForMethodName(this.getClass(), this);
	}

	/**
	 * Returns the contact type name in the given language.
	 * <hr>
	 * @param language {@link LanguageCode} to be used.
	 * @return Contact type name.
	 */
	@BundleEnum(file="bundle.icare-model-entity", path="icare-model-entity.enum.contact.type.name")
	public final String getName(final LanguageCode language)
	{
		return ResourceBundleManager.getResourceForMethodName(this.getClass(), this, language.toLocale());
	}

	/**
	 * Returns the contact type help in the current language.
	 * <hr>
	 * @return Contact type help.
	 */
	@BundleEnum(file="bundle.icare-model-entity", path="icare-model-entity.enum.contact.type.help")
	public final String getHelp()
	{
		return ResourceBundleManager.getResourceForMethodName(this.getClass(), this);
	}

	/**
	 * Returns the contact type help in the given language.
	 * <hr>
	 * @param language {@link LanguageCode} to be used.
	 * @return Contact type help.
	 */
	@BundleEnum(file="bundle.icare-model-entity", path="icare-model-entity.enum.contact.type.help")
	public final String getHelp(final LanguageCode language)
	{
		return ResourceBundleManager.getResourceForMethodName(this.getClass(), this, language.toLocale());
	}
}
