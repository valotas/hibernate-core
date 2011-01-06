/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
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
 *
 */
package org.hibernate.cache.entry;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.engine.SessionFactoryImplementor;

/**
 * @author Gavin King
 */
public class StructuredMapCacheEntry implements CacheEntryStructure {

	public Object structure(Object item) {
		CollectionCacheEntry entry = (CollectionCacheEntry) item;
		Serializable[] state = entry.getState();
		Map<Serializable, Serializable> map = new HashMap<Serializable, Serializable>(state.length);
		for ( int i=0; i<state.length; ) {
			map.put( state[i++], state[i++] );
		}
		return map;
	}
	
	public Object destructure(Object item, SessionFactoryImplementor factory) {
		
		@SuppressWarnings("unchecked")
		Map<Serializable, Serializable> map = (Map<Serializable, Serializable>) item;
		
		Serializable[] state = new Serializable[ map.size()*2 ];
		int i=0;
		for (Map.Entry<Serializable, Serializable> me: map.entrySet()) {
			state[i++] = me.getKey();
			state[i++] = me.getValue();
		}
		return new CollectionCacheEntry(state);
	}

}
