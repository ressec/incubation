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
package com.heliosphere.icare.model.entity.person;

import com.heliosphere.demeter2.base.annotation.Copyright;
import com.heliosphere.demeter2.base.annotation.License;
import com.heliosphere.icare.model.entity.person.type.ContactType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a contact information.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Copyright(company="Heliosphere Corp.", year=2016, author="Resse Christophe")
@License(license="Apache", version="2.0", url="http://www.apache.org/licenses/LICENSE-2.0")
@EqualsAndHashCode
@ToString
public class Contact
{
	/**
	 * Type of the contact.
	 */
	@Getter
	@Setter
	private ContactType type;

}
