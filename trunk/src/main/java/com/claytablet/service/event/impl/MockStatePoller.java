package com.claytablet.service.event.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.claytablet.model.AssetTaskMap;
import com.claytablet.model.ConnectionContext;
import com.claytablet.model.LanguageMap;
import com.claytablet.model.event.provider.UpdateAssetTaskState;
import com.claytablet.service.event.ProviderSender;
import com.claytablet.service.event.ProviderStatePoller;
import com.claytablet.service.event.stubs.MockStub;
import com.google.inject.Inject;
import com.google.inject.Singleton;

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
 * This is the mock implementation for the provider state poller.
 * 
 * @see ProviderStatePoller
 * @see ProviderSender
 * @see UpdateAssetTask
 */
@Singleton
public class MockStatePoller implements ProviderStatePoller {

	private final Log log = LogFactory.getLog(getClass());

	// also injected into the stub, use where appropriate
	private final ConnectionContext context;

	// also injected into the stub, use where appropriate
	private final LanguageMap languageMap;

	// also injected into the stub, use where appropriate
	private AssetTaskMap assetTaskMap;

	private final ProviderSender sender;

	private final MockStub stub;

	/**
	 * Constructor for dependency injection.
	 * 
	 * @param context
	 * @param languageMap
	 * @param assetTaskMap
	 * @param sender
	 * @param stub
	 */
	@Inject
	public MockStatePoller(final ConnectionContext context,
			final LanguageMap languageMap, AssetTaskMap assetTaskMap,
			final ProviderSender sender, final MockStub stub) {

		this.context = context;
		this.languageMap = languageMap;
		this.assetTaskMap = assetTaskMap;
		this.sender = sender;
		this.stub = stub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.event.ProviderStatePoller#poll()
	 */
	public void poll() throws Exception {

		log.debug("Polling TMS for asset task state changes.");

		UpdateAssetTaskState event = new UpdateAssetTaskState();
		event.setAssetTaskId("mock-id");
		event.setNativeState("mock-tms-state");
		event.setNotes("mock-notes");
		sender.sendEvent(event);

	}

}
