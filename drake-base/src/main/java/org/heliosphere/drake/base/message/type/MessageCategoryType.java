package org.heliosphere.drake.base.message.type;

/**
 * Enumerates the message category types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum MessageCategoryType implements IMessageCategoryType
{
	/**
	 * Basic message category.
	 */
	Basic,

	/**
	 * System message category.
	 */
	System,

	/**
	 * Player message category.
	 */
	Player,

	/**
	 * Event message category.
	 */
	Event,

	/**
	 * Debug message category.
	 */
	Debug,

	/**
	 * Monitoring message category.
	 */
	Monitor,

	/**
	 * Auction message category.
	 */
	Auction;
}
