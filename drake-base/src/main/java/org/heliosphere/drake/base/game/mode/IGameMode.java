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
package org.heliosphere.drake.base.game.mode;

import org.heliosphere.drake.base.event.type.IGameModeStepEventType;
import org.heliosphere.drake.base.game.IGame;
import org.heliosphere.drake.base.game.mode.step.GameModeStepException;
import org.heliosphere.drake.base.game.mode.step.IGameModeStep;
import org.heliosphere.drake.base.game.mode.step.IGameModeStepEvent;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.player.IPlayer;

/**
 * Interface defining the behavior of a game mode.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IGameMode extends Runnable
{
	/**
	 * Returns the name of the game mode.
	 * <p>
	 * @return Game mode name.
	 */
	String getName();

	/**
	 * Returns the parent game.
	 * <p>
	 * @return Parent game.
	 */
	public IGame getGame();

	/**
	 * Initializes the game mode.
	 */
	void initialize();

	/**
	 * Finishes the game mode.
	 */
	void finish();

	/**
	 * Creates a game mode step.
	 * <p>
	 * @param stepClass Game mode step class to create.
	 * @return Game mode step.
	 * @throws GameModeStepException Thrown if an error occured while creating
	 * the game mode step.
	 */
	IGameModeStep createStep(final Class<? extends IGameModeStep> stepClass) throws GameModeStepException;

	/**
	 * Removes a game mode step.
	 * <p>
	 * @param stepClass Game mode step class to remove.
	 */
	void removeStep(final Class<? extends IGameModeStep> stepClass);

	/**
	 * Registers a game mode step.
	 * <p>
	 * @param step Game mode step to register.
	 * @param methodName Name of the method to invoke when the game mode step is
	 * finished. Can be {@code null} or empty. In this case the {@code onEvent}
	 * service will be invoked instead.
	 * @return Game mode step.
	 * @throws GameModeStepException Thrown if an error occured while
	 * registering the game mode step.
	 */
	IGameModeStep registerStep(final IGameModeStep step, final String methodName) throws GameModeStepException;

	/**
	 * Unregisters a game mode step.
	 * <p>
	 * @param stepClass Game mode step class to unregister.
	 * @return Previously registered game mode step for the given class or
	 * {@code null} if no game mode step was registered for the given class.
	 */
	IGameModeStep unregisterStep(final Class<? extends IGameModeStep> stepClass);

	/**
	 * Runs a game mode step.
	 * <p>
	 * @param step Game mode step to run.
	 * @throws GameModeStepException Thrown if an error occured while running
	 * the game mode step.
	 */
	void runStep(final IGameModeStep step) throws GameModeStepException;

	/**
	 * Runs a game mode step.
	 * <p>
	 * @param stepClass Game mode step class to run.
	 * @throws GameModeStepException Thrown if an error occured while running
	 * the game mode step.
	 */
	void runStep(final Class<? extends IGameModeStep> stepClass) throws GameModeStepException;

	/**
	 * Suspends a game mode step. If the game mode step is not running, a call
	 * to this
	 * service has no effect.
	 * <p>
	 * @param stepClass Game mode step class to suspend.
	 */
	void suspendStep(final Class<? extends IGameModeStep> stepClass);

	/**
	 * Resumes a game mode step. If the game mode step was not suspended, a call
	 * to this
	 * service has no effect.
	 * <p>
	 * @param stepClass Game mode step class to resume.
	 */
	void resumeStep(final Class<? extends IGameModeStep> stepClass);

	/**
	 * Schedules a game mode step to run periodically.
	 * <p>
	 * @param stepClass Game mode step class to schedule.
	 * @param start Delay in milliseconds for the first execution to start.
	 * @param delay Minimum delay in milliseconds between two executions.
	 */
	void scheduleStep(final Class<? extends IGameModeStep> stepClass, final long start, final long delay);

	/**
	 * Unschedules a game mode step to run periodically. If the given game mode
	 * step was not scheduled to run periodically, a call to this service has
	 * not effect.
	 * <p>
	 * @param stepClass Game mode step class to unschedule.
	 */
	void unscheduleStep(final Class<? extends IGameModeStep> stepClass);

	/**
	 * Adds a game mode step event listener.
	 * <p>
	 * @param stepClass Game mode step class.
	 * @param event Event type to subscribe.
	 * @param methodName Method name to be invoked when the given event is
	 * raised.
	 */
	void addEventStepListener(final Class<? extends IGameModeStep> stepClass, final Enum<? extends IGameModeStepEventType> event, final String methodName);

	/**
	 * Removes a game mode step event listener.
	 * <p>
	 * @param stepClass Game mode class.
	 * @param event Event type to unsubscribe.
	 */
	void removeEventStepListener(final Class<? extends IGameModeStep> stepClass, final Enum<? extends IGameModeStepEventType> event);

	/**
	 * Removes all event listeners for a given game mode step class.
	 * <p>
	 * @param stepClass Game mode step class.
	 */
	void removeEventStepListener(final Class<? extends IGameModeStep> stepClass);

	/**
	 * Invoked each time a game mode step event is received. If a listener has
	 * been
	 * defined using the {@code registerStep} or {@code addEventStepListener},
	 * the {@code onEvent} service will not be invoked.
	 * <p>
	 * @param event Game mode event to process.
	 */
	void onEvent(final IGameModeStepEvent event);

	/**
	 * Registers a player for the game mode.
	 * <p>
	 * @param player Player to register.
	 */
	void registerPlayer(final IPlayer player);

	/**
	 * Unregisters the player from the game mode.
	 */
	void unregisterPlayer();

	/**
	 * Sends an event to the parent game.
	 * <p>
	 * @param event Game mode event to send.
	 */
	void sendEvent(final IGameModeEvent event);

	/**
	 * Invoked each time a message is received from the player.
	 * <p>
	 * @param message Message to process.
	 */
	void onMessage(final IMessage message);

}
