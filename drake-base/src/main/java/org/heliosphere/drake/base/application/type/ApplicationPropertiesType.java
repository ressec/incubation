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
package org.heliosphere.drake.base.application.type;

import org.heliosphere.drake.base.resource.bundle.BundleBase;
import org.heliosphere.drake.base.resource.bundle.IBundle;

/**
 * Enumeration of the application properties type (these properties are common
 * to clients and servers applications).
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ApplicationPropertiesType implements IApplicationPropertiesType
{
	/**
	 * Property defining the name of the application.
	 */
	ApplicationName(BundleBase.PropertyApplicationName),

	/**
	 * Property defining the version of the application.
	 */
	ApplicationVersion(BundleBase.PropertyApplicationVersion),

	/**
	 * Property defining the copyright of the application.
	 */
	ApplicationCopyright(BundleBase.PropertyApplicationCopyright),

	/**
	 * Property defining the path of the folder containing the application log
	 * files.
	 */
	ApplicationDirectoryLog(BundleBase.PropertyApplicationDirectoryLog),

	/**
	 * Property defining the path of the folder containing the application data
	 * files.
	 */
	ApplicationDirectoryData(BundleBase.PropertyApplicationDirectoryData),

	/**
	 * Property defining the encoder type to use for encoding/decoding messages.
	 */
	ApplicationMessageEncoderType(BundleBase.PropertyApplicationMessageEncoderType),

	/**
	 * Property defining the message protocol class name.
	 */
	ApplicationMessageProtocolClassname(BundleBase.PropertyApplicationMessageProtocolClassname),

	/**
	 * Property defining the data encoder algorithm type (see
	 * ApplicationDataEncoderAlgorithmClassname).
	 */
	ApplicationDataEncoderAlgorithmType(BundleBase.PropertyApplicationDataEncoderAlgorithmType),

	/**
	 * Property defining the data encoder algorithm (enumeration) classname.
	 */
	ApplicationDataEncoderAlgorithmClassname(BundleBase.PropertyApplicationDataEncoderAlgorithmClassname),

	/**
	 * Property defining the locale of the application.
	 * <p>
	 * Property = {@code Properties.Application.Locale}
	 */
	ApplicationLocale(BundleBase.PropertyApplicationLocale);

	/**
	 * Property key.
	 */
	private Enum<? extends IBundle> key = null;

	/**
	 * Creates a new property.
	 * <p>
	 * @param key Property key.
	 */
	private ApplicationPropertiesType(final Enum<? extends IBundle> key)
	{
		this.key = key;
	}

	@Override
	public String getKey()
	{
		return ((IBundle) key).getKey();
	}

	@Override
	public String getValue()
	{
		return ((IBundle) key).getValue();
	}

	/**
	 * Creates an application property key from a given key.
	 * @param key Property key.
	 * @return {@link ApplicationPropertiesType}.
	 * @throws IllegalArgumentException Throws when the given property key does
	 * not correspond to a known key.
	 */
	@SuppressWarnings("nls")
	public static ApplicationPropertiesType from(final String key) throws IllegalArgumentException
	{
		if (key == null || key.isEmpty())
		{
			throw new IllegalArgumentException("Key cannot be null or empty");
		}

		for (final ApplicationPropertiesType k : values())
		{
			if (key.equals(k.getValue()))
			{
				return k;
			}
		}

		throw new IllegalArgumentException("Error due to [cause=Cannot create an " + ApplicationPropertiesType.class.getSimpleName() + " from given key, key=" + key + "]");
	}
}
