/**
 * Copyright 2015 Pavlos-Petros Tournaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pavlospt.androidiap.billing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

class BillingBase {

	private WeakReference<Context> contextReference;
	private String pkg;
	
	public BillingBase(Context context) {
		contextReference = new WeakReference<>(context);
		pkg = context.getPackageName();
	}

	public Context getContext() {
		return contextReference.get();
	}

	protected String getPreferencesBaseKey() {
		return  pkg + "_preferences";
	}

	private SharedPreferences getPreferences() {
		if (contextReference.get() != null)
			return PreferenceManager.getDefaultSharedPreferences(contextReference.get());
		return null;
	}

	public void release() {
		if (contextReference != null)
			contextReference.clear();
	}

	protected boolean saveString(String key, String value) {
		SharedPreferences sp = getPreferences();
		if (sp != null) {
            sp.edit().putString(key,value).apply();
			return true;
		}
		return false;
	}

	protected String loadString(String key, String defValue) {
		SharedPreferences sp = getPreferences();
		if (sp != null)
			return sp.getString(key, defValue);
		return defValue;
	}

	protected boolean saveBoolean(String key, Boolean value) {
		SharedPreferences sp = getPreferences();
		if (sp != null) {
            sp.edit().putBoolean(key,value).apply();
			return true;
		}
		return false;
	}

	protected boolean loadBoolean(String key, boolean defValue) {
		SharedPreferences sp = getPreferences();
		if (sp != null)
			return sp.getBoolean(key, defValue);
		return defValue;
	}
}

