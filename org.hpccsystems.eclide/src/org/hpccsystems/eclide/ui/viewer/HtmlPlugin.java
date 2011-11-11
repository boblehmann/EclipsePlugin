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

package org.hpccsystems.eclide.ui.viewer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class HtmlPlugin extends AbstractUIPlugin {
	
	/**
	 * Singleton console manager
	 */
//	private IConsoleManager fConsoleManager = null;

	/**
	 * The singleton console plug-in instance
	 */
	private static HtmlPlugin fgPlugin= null;
	
	/**
	 * Unique identifier constant (value <code>"org.eclipse.ui.console"</code>)
	 * for the UI Console plug-in.
	 */
	private static final String PI_UI_HTML = "org.hpccsystems.eclide.html"; //$NON-NLS-1$
		
	/**
	 * Returns the singleton instance of the console plug-in.
	 */
	public static HtmlPlugin getDefault() {
		return fgPlugin;
	}

	public HtmlPlugin() {
		super();
		fgPlugin = this;
	}
	
	/**
	 * Convenience method which returns the unique identifier of this plug-in.
	 */
	public static String getUniqueIdentifier() {
		return PI_UI_HTML;
	}

	/**
	 * Logs the specified status with this plug-in's log.
	 * 
	 * @param status status to log
	 */
	public static void log(IStatus status) {
		getDefault().getLog().log(status);
	}

	/**
	 * Logs the specified throwable with this plug-in's log.
	 * 
	 * @param t throwable to log 
	 */
	public static void log(Throwable t) {
		if (t instanceof CoreException) {
			log(((CoreException)t).getStatus());
		} else {
			log(newErrorStatus("Error logged from Console plug-in: ", t)); //$NON-NLS-1$
		}
	}
	
	/**
	 * Returns a new error status for this plug-in with the given message
	 * @param message the message to be included in the status
	 * @param exception the exception to be included in the status or <code>null</code> if none
	 * @return a new error status
	 */
	public static IStatus newErrorStatus(String message, Throwable exception) {
		return null;  //todo:  new Status(IStatus.ERROR, getUniqueIdentifier(), "HTML Error 001", message, exception);
	}
	
	/**
	 * Returns the console manager. The manager will be created lazily on 
	 * the first access.
	 * 
	 * @return IConsoleManager
	 */
//	public IConsoleManager getConsoleManager() {
//		if (fConsoleManager == null) {
//			fConsoleManager = new ConsoleManager();
//		}
//		return fConsoleManager;
//	}

	/**
	 * Returns the workbench display.
	 */
	public static Display getStandardDisplay() {
		return PlatformUI.getWorkbench().getDisplay();	
	}
	
//	/**
//	 * Utility method with conventions
//	 */
//	public static void errorDialog(Shell shell, String title, String message, Throwable t) {
//		IStatus status;
//		if (t instanceof CoreException) {
//			status= ((CoreException)t).getStatus();
//			// if the 'message' resource string and the IStatus' message are the same,
//			// don't show both in the dialog
//			if (status != null && message.equals(status.getMessage())) {
//				message= null;
//			}
//		} else {
//			//status= new Status(IStatus.ERROR, getUniqueIdentifier(), IConsoleConstants.INTERNAL_ERROR, "Error within Debug UI: ", t); //$NON-NLS-1$
//			//log(status);	
//		}
//		ErrorDialog.openError(shell, title, message, status);
//	}
    
    /**
     * Returns the <code>Image</code> identified by the given key,
     * or <code>null</code> if it does not exist.
     * 
     * @return the <code>Image</code> identified by the given key,
     * or <code>null</code> if it does not exist
     * @since 3.1
     */
//    public static Image getImage(String key) {
//        return HtmlPluginImages.getImage(key);
//    }
    
    /**
     * Returns the <code>ImageDescriptor</code> identified by the given key,
     * or <code>null</code> if it does not exist.
     * 
     * @return the <code>ImageDescriptor</code> identified by the given key,
     * or <code>null</code> if it does not exist
     * @since 3.1
     */
//    public static ImageDescriptor getImageDescriptor(String key) {
//        return HtmlPluginImages.getImageDescriptor(key);
//    }
    
    /* (non-Javadoc)
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
//    public void stop(BundleContext context) throws Exception {
//    	if (fConsoleManager != null) {
//	        IConsole[] consoles = fConsoleManager.getConsoles();
//	        if (consoles != null) {
//	            fConsoleManager.removeConsoles(consoles);
//	        }
//    	}
//        super.stop(context);
//    }    
    
    
}
