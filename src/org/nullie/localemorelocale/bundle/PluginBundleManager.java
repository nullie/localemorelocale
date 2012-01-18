/*
 * Copyright 2011 two forty four a.m. LLC <http://www.twofortyfouram.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at <http://www.apache.org/licenses/LICENSE-2.0>
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.nullie.localemorelocale.bundle;

import android.os.Bundle;
import android.util.Log;

import org.nullie.localemorelocale.Constants;

/**
 * Class for managing the {@link com.twofortyfouram.locale.Intent#EXTRA_BUNDLE} for this plug-in.
 */
public final class PluginBundleManager
{
    /**
     * Private constructor prevents instantiation
     * 
     * @throws UnsupportedOperationException because this class cannot be instantiated.
     */
    private PluginBundleManager()
    {
        throw new UnsupportedOperationException("This class is non-instantiable"); //$NON-NLS-1$
    }

    /**
     * Type: {@code String}
     * <p>
     * String message to display in a Toast message.
     */
    public static final String BUNDLE_EXTRA_STRING_LANGUAGE = "com.yourcompany.yourapp.extra.STRING_LANGUAGE"; //$NON-NLS-1$
    public static final String BUNDLE_EXTRA_STRING_COUNTRY = "com.yourcompany.yourapp.extra.STRING_COUNTRY"; //$NON-NLS-1$
    public static final String BUNDLE_EXTRA_STRING_VARIANT = "com.yourcompany.yourapp.extra.STRING_VARIANT"; //$NON-NLS-1$

    /**
     * Method to verify the content of the bundle are correct.
     * <p>
     * This method will not mutate {@code bundle}.
     * 
     * @param bundle bundle to verify. May be null, which will always return false.
     * @return true if the Bundle is valid, false if the bundle is invalid.
     */
    public static boolean isBundleValid(final Bundle bundle)
    {
        if (null == bundle)
        {
            return false;
        }

        /*
         * Make sure the expected extras exist
         */
        
        String[] extras = {
        	BUNDLE_EXTRA_STRING_LANGUAGE,
        	BUNDLE_EXTRA_STRING_COUNTRY,
        	BUNDLE_EXTRA_STRING_VARIANT
        };
        
        for(String extra : extras ) {
	        if (!bundle.containsKey(extra))
	        {
	            if (Constants.IS_LOGGABLE)
	            {
	                Log.e(Constants.LOG_TAG, String.format("bundle must contain extra %s", extra)); //$NON-NLS-1$
	            }
	            return false;
	        }
	        
	        /*
	         * Make sure the extra isn't null
	         */
	        if (bundle.getString(extra) == null)
	        {
	            if (Constants.IS_LOGGABLE)
	            {
	                Log.e(Constants.LOG_TAG, String.format("bundle extra %s appears to be null.  It must be a string", extra)); //$NON-NLS-1$
	            }
	            return false;
	        }
        }

        /*
         * Make sure the correct number of extras exist. Run this test after checking for specific Bundle extras above so that the
         * error message is more useful. (E.g. the caller will see what extras are missing, rather than just a message that there
         * is the wrong number).
         */
        if (bundle.keySet().size() != 3)
        {
            if (Constants.IS_LOGGABLE)
            {
                Log.e(Constants.LOG_TAG, String.format("bundle must contain 3 keys, but currently contains %d: %s", Integer.valueOf(bundle.keySet().size()), bundle.keySet() //$NON-NLS-1$
                                                                                                                                                                       .toString()));
            }
            return false;
        }

        return true;
    }
}