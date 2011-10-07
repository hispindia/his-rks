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

package org.openmrs.module.rks.web.controller.category;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.rks.RKSService;
import org.openmrs.module.rks.model.Category;

public class CategoryPropertyEditor  extends PropertyEditorSupport {
	private Log log = LogFactory.getLog(this.getClass());
	public CategoryPropertyEditor() {
	}
	public void setAsText(String text) throws IllegalArgumentException {
		RKSService rksService =Context.getService(RKSService.class);
		if (text != null && text.trim().length() > 0 ) {
			try {
				setValue(rksService.getCategoryById(NumberUtils.toInt(text)));
			}
			catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Category not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
	
	public String getAsText() {
		Category s = (Category) getValue();
		if (s == null ) {
			return null; 
		} else {
			return s.getId()+"";
		}
	}
}
