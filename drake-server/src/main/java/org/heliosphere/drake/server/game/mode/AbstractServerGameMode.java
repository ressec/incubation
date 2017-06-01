package org.heliosphere.drake.server.game.mode;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.heliosphere.drake.server.entity.AbstractServerEntity;
import org.heliosphere.drake.server.player.IServerPlayer;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.ManagedReference;

@Log4j
public abstract class AbstractServerGameMode extends AbstractServerEntity implements IServerGameMode
{
	/**
	 * Game mode binding prefix.
	 */
	@SuppressWarnings("nls")
	protected final static String BINDING_PREFIX = "relic:game:mode.";

	/**
	 * Game mode type.
	 */
	private Enum<? extends IServerGameModeType> type;

	/**
	 * List of players currently in this game mode.
	 */
	protected List<ManagedReference<? extends IServerPlayer>> players;

	/**
	 * Creates a new server game mode of the given type.
	 * <p>
	 * @param type Game mode type.
	 */
	public AbstractServerGameMode(final Enum<? extends IServerGameModeType> type)
	{
		super(BINDING_PREFIX, type.name());
		this.type = type;
	}

	@Override
	public void join(final IServerPlayer player)
	{
		ManagedReference<? extends IServerPlayer> playerReference = AppContext.getDataManager().createReference(player);
		if (players == null)
		{
			players = new ArrayList<>();
		}

		if (!players.contains(playerReference))
		{
			player.setGameMode(this);
			players.add(playerReference);
		}
	}
}
