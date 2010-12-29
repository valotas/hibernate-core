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
package org.hibernate.service.classloading.internal;

import java.util.Map;

import org.hibernate.service.classloading.spi.ClassLoaderService;
import org.hibernate.service.spi.ServiceInitiator;
import org.hibernate.service.spi.ServicesRegistry;


/**
 * Standard initiator for the stanndard {@link ClassLoaderService} service.
 *
 * @author Steve Ebersole
 */
public class ClassLoaderServiceInitiator implements ServiceInitiator<ClassLoaderService> {
	public static final ClassLoaderServiceInitiator INSTANCE = new ClassLoaderServiceInitiator();

	public Class<ClassLoaderService> getServiceInitiated() {
		return ClassLoaderService.class;
	}

	public ClassLoaderService initiateService(Map configurationValues, ServicesRegistry registry) {
		return new ClassLoaderServiceImpl( configurationValues );
	}
}
