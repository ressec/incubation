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
package com.heliosphere.icare.model.entity.resource.bundle;

import com.heliosphere.demeter.base.resource.bundle.IBundle;
import com.heliosphere.demeter.base.resource.bundle.ResourceBundleManager;
import com.heliosphere.demeter2.base.annotation.BundleEnumRegister;
import com.heliosphere.demeter2.base.annotation.Copyright;
import com.heliosphere.demeter2.base.annotation.License;

/**
 * Enumeration of the resource bundles of the {@code icare-model-entity} module. Each
 * enumerated value maps to a key in the resource bundle file.
 * <p>
 * <b>Note:</b><br>
 * In the Heliosphere' frameworks, the enumerated values are not in full uppercase.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
@Copyright(company="Heliosphere Corp.", year=2016, author="Resse Christophe")
@License(license="Apache", version="2.0", url="http://www.apache.org/licenses/LICENSE-2.0")
@SuppressWarnings("nls")
@BundleEnumRegister
public enum BundleIcareModelEntity implements IBundle
{
	/**
	 * Bundle name.
	 * <p>
	 * This enumerated value should always be created and must point to the name
	 * of the resource bundle so that the {@link ResourceBundleManager} can load
	 * and register the resource bundle files.
	 * <p>
	 * <b>Example</b>:<br>
	 * If the module's name is {@code foo.module}, then this enumerated
	 * value should have the value: {@code bundle.foo.module}
	 */
	BundleFilename("bundle.icare-model-entity"),

	/**
	 * Root value for all messages of the {@code BundleBase} component.
	 * <p>
	 * <b>Note:</b><br>
	 * Must have the name of the project artifact followed by the dot (.)
	 * character.
	 * <b>Example</b>:<br>
	 * If the module's name is {@code foo.module}, then this enumerated
	 * value should have the value: {@code foo.module}
	 */
	BundleRoot("icare-model-entity."),

	/*
	 * ---------- Test.* ----------
	 */

	/**
	 * A dummy message from component: icare-model-entity.
	 */
	TestDummy("test.dummy"),

	/*
	 * ---------- Enumeration.* ----------
	 */

	/**
	 * Cannot create an enumerated value.
	 */
	CannotCreateEnumerated("enumeration.cannotCreateEnumerated");


	/**
	 * Resource bundle key.
	 */
	private final String key;

	/**
	 * Creates a new enumerated value based on the resource bundle key.
	 * <p>
	 * @param key Resource bundle key.
	 */
	private BundleIcareModelEntity(@SuppressWarnings("hiding") final String key)
	{
		this.key = key;
	}

	@Override
	public final String getKey()
	{
		if (!key.equals(BundleIcareModelEntity.BundleRoot.key) && !key.equals(BundleIcareModelEntity.BundleFilename.key))
		{
			return BundleIcareModelEntity.BundleRoot.getKey() + key;
		}

		return key;
	}

	@Override
	public final String getValue()
	{
		return ResourceBundleManager.getMessage(this);
	}
}
