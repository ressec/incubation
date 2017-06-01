package org.heliosphere.drake.server.game.mode;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.MessageException;
import org.heliosphere.drake.base.message.manager.MessageManager;
import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.heliosphere.drake.message.type.DrakeMessageType;
import org.heliosphere.drake.server.player.IServerPlayer;

import com.sun.sgs.app.AppContext;

@Log4j
public class ServerGameModeWelcome extends AbstractServerGameMode
{
	/**
	 * Creates a new welcome server game mode.
	 */
	public ServerGameModeWelcome()
	{
		super(ServerGameModeType.WELCOME);
	}

	@Override
	public void join(final IServerPlayer player)
	{
		super.join(player);

		IMessage message;

		try
		{
			message = AppContext.getManager(MessageManager.class).createMessage(DrakeMessageType.EchoMessage);
			message.setContent("Welcome to the game: " + player.getName());
			player.sendMessage(message);
		}
		catch (MessageManagerException e)
		{
			log.error(e.getMessage());
		}
		catch (MessageException e)
		{
			log.error(e.getMessage());
		}
	}
}
