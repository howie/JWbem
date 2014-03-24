/*
 * Copyright (c) 2009, Hyper9 All rights reserved. Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met: Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution. Neither the name of Hyper9 nor the names of its contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.h9labs.jwbem;

import java.lang.reflect.Constructor;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.IJIComObject;
import org.jinterop.dcom.core.JIString;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.JIObjectFactory;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.trendmicro.jwbem.exception.SWbemException;

/**
 * You can use the methods of an SWbemServices object to perform operations against a namespace on either a local host or a remote
 * host.
 * 
 * @author howie_yu
 * @author akutz
 */
public class SWbemServices extends SWbemDispatchObject {

	private SWbemLocator	locator;

	public static final int	wbemFlagReturnImmediately	= 32;

	public static final int	wbemFlagForwardOnly			= 16;

	/**
	 * Initializes a new instance of the SWbemServices class.
	 * 
	 * @param objectDispatcher The underlying dispatch object used to communicate with the server.
	 */
	private SWbemServices(IJIDispatch objectDispatcher) {
		super(objectDispatcher, null);
	}

	@Override
	public SWbemServices getService() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Initializes a new instance of the SWbemServices class.
	 * 
	 * @param objectDispatcher The underlying dispatch object used to communicate with the server.
	 * @param locator The SWbemLocator that created this object.
	 */
	public SWbemServices(IJIDispatch objectDispatcher, SWbemLocator locator) {
		this(objectDispatcher);
		this.locator = locator;
	}

	/**
	 * Gets the SWbemLocator that created this object.
	 * 
	 * @return The SWbemLocator that created this object.
	 */
	public SWbemLocator getLocator() {
		return this.locator;
	}

	/**
	 * The ExecQuery method of the SWbemServices object executes a query to retrieve objects. These objects are available through
	 * the returned SWbemObjectSet collection.
	 * 
	 * @param query String that contains the text of the query. This parameter cannot be blank.
	 * @return An object set.
	 */
	public SWbemObjectSet<SWbemObject> execQuery(String query) {
		Object[] inParams = new Object[] { new JIString(query), JIVariant.OPTIONAL_PARAM(), JIVariant.OPTIONAL_PARAM(), JIVariant.OPTIONAL_PARAM(), };
		try {
			JIVariant[] results = super.objectDispatcher.callMethodA("ExecQuery", inParams);
			IJIComObject co = results[0].getObjectAsComObject();
			IJIDispatch dispatch;
			dispatch = (IJIDispatch)JIObjectFactory.narrowObject(co);
			return new SWbemObjectSet<SWbemObject>(dispatch, this, SWbemObject.class);
		} catch (JIException ex) {
			return null;
		}
	}

	/**
	 * The ExecQuery method of the SWbemServices object executes a query to retrieve objects. These objects are available through
	 * the returned SWbemObjectSet collection.
	 * 
	 * @param <T> A class that extends SWbemObject.
	 * @param query String that contains the text of the query. This parameter cannot be blank.
	 * @param clazz A class that extends SWbemObject.
	 * @return An object set.
	 * @throws SWbemException
	 */
	public <T extends SWbemObject> SWbemObjectSet<T> execQuery(String query, Class<T> clazz) throws SWbemException {


		Object[] inParams = new Object[] { new JIString(query), JIVariant.OPTIONAL_PARAM(), JIVariant.OPTIONAL_PARAM(), JIVariant.OPTIONAL_PARAM(), };
		try {
			JIVariant[] results = super.objectDispatcher.callMethodA("ExecQuery", inParams);
			IJIComObject co = results[0].getObjectAsComObject();
			IJIDispatch dispatch;
			dispatch = (IJIDispatch)JIObjectFactory.narrowObject(co);
			return new SWbemObjectSet<T>(dispatch, this, clazz);
		} catch (JIException ex) {
			throw new SWbemException(ex);
		}
	}


	/**
	 * The ExecNotificationQuery method of the SWbemServices object executes a query to receive events. The call returns
	 * immediately. The user can poll the returned enumerator for events as they arrive. The method is called in the semisynchronous
	 * mode. For more information, see Calling a Method.
	 * 
	 * @See <a href="http://msdn.microsoft.com/en-us/library/aa393864%28v=vs.85%29.aspx">ExecNotificationQuery</a>
	 * @param <T> A class that extends SWbemObject
	 * @param query String that contains the text of the query. This parameter cannot be blank.
	 * @param clazz A class that extends SWbemObject.
	 * @return
	 * @throws SWbemException
	 */
	public <T extends SWbemEventSource> T execNotificationQuery(String query, Class<T> clazz) throws SWbemException {


		int iFlags = wbemFlagReturnImmediately + wbemFlagForwardOnly;

		Object[] inParams = new Object[] { new JIString(query), new JIString("WQL"), iFlags, JIVariant.OPTIONAL_PARAM() };

		try {
			JIVariant[] eventSourceSet = super.objectDispatcher.callMethodA("ExecNotificationQuery", inParams);

			IJIComObject co = eventSourceSet[0].getObjectAsComObject();

			IJIComObject comObject = (IJIComObject)co.queryInterface(SWbemEventSource.IID);

			IJIDispatch dispatch = (IJIDispatch)JIObjectFactory.narrowObject(comObject.queryInterface(IJIDispatch.IID));


			// Create a new SWbemSetItem from the result.
			final Constructor<T> ctor = clazz.getConstructor(IJIDispatch.class, SWbemServices.class);
			final T item = ctor.newInstance(dispatch, this);
			return item;

		} catch (Exception ex) {
			throw new SWbemException(ex);
		}
	}


}
