/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.service.jmx.internal;

import java.util.Map;

import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.jmx.spi.JmxService;
import org.hibernate.service.spi.ServicesRegistry;
import org.hibernate.service.spi.ServiceInitiator;

/**
 * Standard initiator for the standard {@link JmxService} service
 *
 * @author Steve Ebersole
 */
public class JmxServiceInitiator implements ServiceInitiator<JmxService> {
	public static final String JMX_ENABLED = "hibernate.jmx.enabled";
	public static final JmxServiceInitiator INSTANCE = new JmxServiceInitiator();

	public Class<JmxService> getServiceInitiated() {
		return JmxService.class;
	}

	public JmxService initiateService(Map configValues, ServicesRegistry registry) {
		return ConfigurationHelper.getBoolean( JMX_ENABLED, configValues, false )
				? new JmxServiceImpl( configValues )
				: DisabledJmxServiceImpl.INSTANCE;
	}
}
