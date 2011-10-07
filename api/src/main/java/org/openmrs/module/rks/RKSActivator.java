/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of RKS module.
 *
 *  RKS module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RKS module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RKS module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.rks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class RKSActivator implements ModuleActivator{
	
	private Log log = LogFactory.getLog(this.getClass());
	

	@Override
	public void contextRefreshed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void started() {
		// TODO Auto-generated method stub
		log.info("Started RKS Module");
	}

	@Override
	public void stopped() {
		// TODO Auto-generated method stub
		log.info("Stoped RKS Module");
	}

	@Override
	public void willRefreshContext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void willStart() {
		// TODO Auto-generated method stub
		log.info("Starting RKS Module");
	}

	@Override
	public void willStop() {
		// TODO Auto-generated method stub
		log.info("Shutting down RKS Module");
	}
	
}
