package org.heliosphere.drake.server.game.mode;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.NameNotBoundException;

public final class ServerGameModeFactory
{
	public final static IServerGameMode get(final ServerGameModeType type)
	{
		IServerGameMode mode = null;

		try
		{
			mode = (IServerGameMode) AppContext.getDataManager().getBinding("relic:game:mode." + type.name()); // TODO Change this!
		}
		catch (NameNotBoundException e)
		{
			switch (type)
			{
				case WELCOME:
					mode = new ServerGameModeWelcome();
					AppContext.getDataManager().createReference(mode);
					AppContext.getDataManager().setBinding(mode.getBindingName(), mode);
					break;

				case LOGIN:
					break;

				case LOGOUT:
					break;
			}
		}

		return mode;
	}
}
