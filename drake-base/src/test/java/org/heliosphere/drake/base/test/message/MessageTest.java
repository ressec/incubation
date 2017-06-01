/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.test.message;

import org.heliosphere.drake.base.manager.Manager;
import org.heliosphere.drake.base.message.IMessage;
import org.heliosphere.drake.base.message.codec.MessageDecodingException;
import org.heliosphere.drake.base.message.manager.IMessageManager;
import org.heliosphere.drake.base.message.manager.MessageManagerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A test case for messages.
 * <hr>
 * @author Resse Christophe, Heliosphere Corp. 2010-2012
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public final class MessageTest
{
	/**
	 * Initialization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the initialization.
	 */
	@BeforeClass
	public static final void setUpBeforeClass() throws Exception
	{
		// Empty
	}

	/**
	 * Finalization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the finalization.
	 */
	@AfterClass
	public static final void tearDownAfterClass() throws Exception
	{
		// Empty
	}

	/**
	 * Sets up the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the setup phase.
	 */
	@Before
	public final void setUp() throws Exception
	{
		// Empty
	}

	/**
	 * Tears down the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the tear down phase.
	 */
	@After
	public final void tearDown() throws Exception
	{
		// Empty
	}

	/**
	 * Test the registration of a set of messages using an enumeration.
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void registerMessageByEnum() throws MessageManagerException
	{
		IMessageManager manager = Manager.getManager(IMessageManager.class);

		manager.registerMessage(DrakeMessageType.class);

		// Creates messages based on their identifier.
		IMessage errorMessage = manager.createMessage(2);
		IMessage echoMessage = manager.createMessage(1);

		// Create messages based on their type.
		IMessage exceptionMessage = manager.createMessage(DrakeMessageType.ErrorMessage);

		errorMessage.setContent("Error while decoding message");
		echoMessage.setContent("This is a marvelous journey!");

		try
		{
			manager.handleMessage(echoMessage);
			manager.handleMessage(errorMessage);
		}
		catch (MessageDecodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
