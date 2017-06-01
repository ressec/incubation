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
package org.heliosphere.drake.base.player;

import java.util.Locale;

import org.heliosphere.drake.base.player.status.IPlayerStatusType;
import org.heliosphere.drake.base.player.status.PlayerStatusType;

/**
 * Abstract player.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractPlayer implements IPlayer
{
	/**
	 * Player name.
	 */
	private String name;

	/**
	 * Player locale.
	 */
	private Locale locale;

	/**
	 * Player status.
	 */
	private Enum<? extends IPlayerStatusType> status = PlayerStatusType.Disconnected;

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public final void setName(final String name)
	{
		this.name = name;
	}

	@Override
	public final Locale getLocale()
	{
		return locale;
	}

	@Override
	public final void setLocale(final Locale locale)
	{
		this.locale = locale;
	}

	@Override
	public final Enum<? extends IPlayerStatusType> getStatus()
	{
		return status;
	}

	@Override
	public final void setStatus(final Enum<? extends IPlayerStatusType> status)
	{
		this.status = status;
	}
}
