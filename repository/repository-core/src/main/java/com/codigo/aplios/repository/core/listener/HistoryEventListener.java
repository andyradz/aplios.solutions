package com.codigo.aplios.repository.core.listener;

import org.apache.log4j.Logger;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.internal.sessions.ObjectChangeSet;

public class HistoryEventListener extends DescriptorEventAdapter {

	private static Logger log = Logger.getLogger(HistoryEventListener.class);

	@Override
	public void postUpdate(final DescriptorEvent event) {

		final ObjectChangeSet changeSet = event.getChangeSet();
		if (changeSet != null)
			log.info("ObjectChangeSet not null");
		log.info("ObjectChangeSet null");
	}

	@Override
	public void postMerge(DescriptorEvent event) {

		final ObjectChangeSet changeSet = event.getChangeSet();
		if (changeSet != null)
			log.info("ObjectChangeSet not null");
		log.info("ObjectChangeSet null");
	}

}
