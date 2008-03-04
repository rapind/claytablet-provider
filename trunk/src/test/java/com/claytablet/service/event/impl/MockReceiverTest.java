package com.claytablet.service.event.impl;

import org.junit.After;
import org.junit.Before;

import com.claytablet.module.MockProviderModule;
import com.claytablet.service.event.AbsProviderReceiverTest;
import com.google.inject.Guice;

/**
 * Copyright 2007 Clay Tablet Technologies Inc.
 * 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at <a
 * href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 * 
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * <p>
 * 
 * @author <a href="mailto:drapin@clay-tablet.com">Dave Rapin</a>
 * 
 * <p>
 * This class just initializes the mock implementation. All tests are contained
 * within the base class.
 * 
 * <p>
 * @see AbsProviderReceiverTest
 */
public class MockReceiverTest extends AbsProviderReceiverTest {

	// -------------------------------------------------------------------------
	// Initializations
	// -------------------------------------------------------------------------

	/**
	 * This is run before every unit test and is used to setup test variables.
	 */
	@Before
	public void setUp() {

		log.debug("SETUP: ");

		log.debug("Inject the receiver implementation.");
		receiver = Guice.createInjector(new MockProviderModule()).getInstance(
				MockReceiver.class);

		// call the super to finalize setup
		super.setUp();
	}

	@After
	public void tearDown() {

		super.tearDown();
	}

}
