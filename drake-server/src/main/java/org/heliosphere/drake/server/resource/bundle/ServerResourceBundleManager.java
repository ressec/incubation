package org.heliosphere.drake.server.resource.bundle;

import org.heliosphere.drake.base.resource.bundle.ResourceBundleManager;

public class ServerResourceBundleManager extends ResourceBundleManager
{
	private static ResourceBundleService service = null;

	public ServerResourceBundleManager(final ResourceBundleService service)
	{
		this.service = service;
	}
}
