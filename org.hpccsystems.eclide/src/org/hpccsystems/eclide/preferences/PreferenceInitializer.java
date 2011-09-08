/*##############################################################################

    Copyright (C) 2011 HPCC Systems.

    All rights reserved. This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
############################################################################## */

package org.hpccsystems.eclide.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.hpccsystems.eclide.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		try{
			String hpccBinFolder = System.getenv("HPCCBIN");
			store.setDefault(PreferenceConstants.P_TOOLSPATH, hpccBinFolder);
		}catch (Exception e){
			store.setDefault(PreferenceConstants.P_TOOLSPATH, "");
		}
		
		store.setDefault(PreferenceConstants.P_REMOTEEXECUTE, false);
		store.setDefault(PreferenceConstants.P_SERVERIP, "192.168.241.131");
		store.setDefault(PreferenceConstants.P_SERVERCLUSTER, "thor");
	}

}
