package org.heliosphere.drake.server.game.mode;

public class ServerGameMode extends AbstractServerGameMode
{
	/**
	 * Creates a new server game mode of the given type.
	 * <p>
	 * @param type Game mode type.
	 */
	public ServerGameMode(final Enum<? extends IServerGameModeType> type)
	{
		super(type);
	}

}
