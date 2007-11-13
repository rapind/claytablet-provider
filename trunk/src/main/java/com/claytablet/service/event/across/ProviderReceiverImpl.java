package com.claytablet.service.event.across;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.claytablet.model.event.Account;
import com.claytablet.model.event.platform.ApprovedAssetTask;
import com.claytablet.model.event.platform.CanceledAssetTask;
import com.claytablet.model.event.platform.CanceledSupportAsset;
import com.claytablet.model.event.platform.ProcessingError;
import com.claytablet.model.event.platform.RejectedAssetTask;
import com.claytablet.model.event.platform.StartAssetTask;
import com.claytablet.model.event.platform.StartSupportAsset;
import com.claytablet.provider.SourceAccountProvider;
import com.claytablet.service.event.EventServiceException;
import com.claytablet.service.event.ProviderReceiver;
import com.claytablet.storage.service.StorageClientService;
import com.claytablet.storage.service.StorageServiceException;
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
 * This is the default implementation for the provider receiver.
 * 
 * @see ProviderReceiver
 * @see SourceAccountProvider
 * @see StorageClientService
 * @see ApprovedAssetTask
 * @see CanceledAssetTask
 * @see ProcessingError
 * @see RejectedAssetTask
 * @see StartAssetTask
 */
@Singleton
public class ProviderReceiverImpl implements ProviderReceiver {

	private final Log log = LogFactory.getLog(getClass());

	private SourceAccountProvider sap;

	private StorageClientService storageClientService;

	/**
	 * Constructor for dependency injection.
	 * 
	 * @param sap
	 * @param storageClientService
	 */
	@Inject
	public ProviderReceiverImpl(SourceAccountProvider sap,
			StorageClientService storageClientService) {
		this.sap = sap;
		this.storageClientService = storageClientService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.event.ReceiverProvider#receiveEvent(com.claytablet.model.event.producer.ApprovedAssetTask)
	 */
	public void receiveEvent(ApprovedAssetTask event)
			throws StorageServiceException, EventServiceException {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// check to see if new content was submitted with the event
		if (event.isWithContent()) {
			log.debug("A new asset task revision was sent with the approval.");

			// retrieve the source account from the provider.
			Account sourceAccount = sap.get();

			log.debug("Initialize the storage client service.");
			storageClientService.setPublicKey(sourceAccount.getPublicKey());
			storageClientService.setPrivateKey(sourceAccount.getPrivateKey());
			storageClientService.setStorageBucket(sourceAccount
					.getStorageBucket());

			log.debug("Download the latest asset task revision for: "
					+ event.getAssetTaskId());
			String downloadPath = storageClientService
					.downloadLatestAssetTaskVersion(event.getAssetTaskId(),
							"./files/received/");

			log.debug("Downloaded an asset task version file to: "
					+ downloadPath);
		}

		// TODO - provider integration code goes here.

		// If an exception is thrown the event will remain on the queue.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.event.ReceiverProvider#receiveEvent(com.claytablet.model.event.producer.CanceledAssetTask)
	 */
	public void receiveEvent(CanceledAssetTask event) {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// TODO - provider integration code goes here.

		// If an exception is thrown the event will remain on the queue.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.event.ProviderReceiver#receiveEvent(com.claytablet.model.event.platform.CanceledSupportAsset)
	 */
	public void receiveEvent(CanceledSupportAsset event) {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// TODO - provider integration code goes here.

		// If an exception is thrown the event will remain on the queue.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.client.ReceiverProvider#receiveEvent(com.claytablet.model.event.platform.ProcessingError)
	 */
	public void receiveEvent(ProcessingError event) {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// TODO - provider integration code goes here.
		// An error occured. Examine and take appropriate action.

		log.error(event.getErrorMessage());

		// The event.getErrorDetails() will contain the original serialized
		// event that caused the error. It can be deserialized into it's event
		// object and dealt with.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.event.ReceiverProvider#receiveEvent(com.claytablet.model.event.producer.RejectedAssetTask)
	 */
	public void receiveEvent(RejectedAssetTask event)
			throws StorageServiceException, EventServiceException {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// check to see if new content was submitted with the event
		if (event.isWithContent()) {
			log.debug("A new asset task revision was sent with the rejection.");

			// retrieve the source account from the provider.
			Account sourceAccount = sap.get();

			log.debug("Initialize the storage client service.");
			storageClientService.setPublicKey(sourceAccount.getPublicKey());
			storageClientService.setPrivateKey(sourceAccount.getPrivateKey());
			storageClientService.setStorageBucket(sourceAccount
					.getStorageBucket());

			log.debug("Download the latest asset task revision for: "
					+ event.getAssetTaskId());
			String downloadPath = storageClientService
					.downloadLatestAssetTaskVersion(event.getAssetTaskId(),
							"./files/received/");

			log.debug("Downloaded an asset task version file to: "
					+ downloadPath);
		}

		// TODO - provider integration code goes here.

		// If an exception is thrown the event will remain on the queue.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.client.ReceiverProvider#receiveEvent(com.claytablet.model.event.platform.StartAssetTask)
	 */
	public void receiveEvent(StartAssetTask event)
			throws StorageServiceException, EventServiceException {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// retrieve the source account from the provider.
		Account sourceAccount = sap.get();

		log.debug("Initialize the storage client service.");
		storageClientService.setPublicKey(sourceAccount.getPublicKey());
		storageClientService.setPrivateKey(sourceAccount.getPrivateKey());
		storageClientService.setStorageBucket(sourceAccount.getStorageBucket());

		log.debug("Download the latest asset task revision for: "
				+ event.getAssetTaskId());
		String downloadPath = storageClientService
				.downloadLatestAssetTaskVersion(event.getAssetTaskId(),
						"./files/received/");

		log.debug("Downloaded an asset task version file to: " + downloadPath);

		// TODO - provider integration code goes here.
		// I.e. send the asset to the TMS and mark it as pending.

		// I.e. call an external system method called new document

		// If an exception is thrown the event will remain on the queue.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.claytablet.service.event.ProviderReceiver#receiveEvent(com.claytablet.model.event.platform.StartSupportAsset)
	 */
	public void receiveEvent(StartSupportAsset event)
			throws StorageServiceException, EventServiceException {

		log.debug(event.getClass().getSimpleName() + " event received.");

		// retrieve the source account from the provider.
		Account sourceAccount = sap.get();

		log.debug("Initialize the storage client service.");
		storageClientService.setPublicKey(sourceAccount.getPublicKey());
		storageClientService.setPrivateKey(sourceAccount.getPrivateKey());
		storageClientService.setStorageBucket(sourceAccount.getStorageBucket());

		log.debug("Download the support asset for: "
				+ event.getSupportAssetId());
		String downloadPath = storageClientService.downloadSupportAsset(event
				.getSupportAssetId(), event.getFileExt(), "./files/received/");

		log.debug("Downloaded a support asset file to: " + downloadPath);

		// TODO - provider integration code goes here.
		// I.e. send the support asset to the TMS.

		// I.e. call an external system method called new document

		// If an exception is thrown the event will remain on the queue.

	}

}
