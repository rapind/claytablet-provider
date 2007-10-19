package com.claytablet.module;

import com.claytablet.service.event.AccountManager;
import com.claytablet.service.event.ProviderReceiver;
import com.claytablet.service.event.impl.AccountManagerImpl;
import com.claytablet.service.event.mock.ProviderReceiverMock;

/**
 * Copyright 2007 Clay Tablet Technologies Inc.
 * 
 * <p>
 * 
 * @author <a href="mailto:drapin@clay-tablet.com">Dave Rapin</a>
 * 
 * <p>
 * Mock module for Guice configuration. Extends the SQSS3Module and overrides
 * the default receiver binding for a mock implementation.
 */
public class MockSQSS3Module extends SQSS3Module {

	protected void configure() {

		super.configure();

		// specify account manager binding
		bind(AccountManager.class).to(AccountManagerImpl.class);

		// override the default receiver binding
		bind(ProviderReceiver.class).to(ProviderReceiverMock.class);

	}
}
