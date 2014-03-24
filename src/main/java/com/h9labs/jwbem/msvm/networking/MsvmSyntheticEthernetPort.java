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

package com.h9labs.jwbem.msvm.networking;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.trendmicro.jwbem.SWbemObjectSet;
import com.trendmicro.jwbem.SWbemServices;
import com.trendmicro.jwbem.SWbemSet;
import com.trendmicro.jwbem.exception.SWbemException;
import com.trendmicro.jwbem.msvm.MsvmObject;
import com.trendmicro.jwbem.msvm.virtualsystem.MsvmComputerSystem;

/**
 * Represents a synthetic Ethernet adapter.
 * 
 * @author howie_yu
 * @author akutz
 * @remarks This adapter is the preferred network adapter because of its performance and hot configurability.
 */
public class MsvmSyntheticEthernetPort extends MsvmObject {

	/**
	 * Initializes a new instance of the Msvm_SyntheticEthernetPort class.
	 * 
	 * @param objectDispatcher The underlying dispatch object used to communicate with the server.
	 * @param service The service connection.
	 */
	public MsvmSyntheticEthernetPort(IJIDispatch objectDispatcher, SWbemServices service) {
		super(objectDispatcher, service);
	}

	/**
	 * @param service
	 * @param vm
	 * @return
	 * @throws JIException
	 * @throws SWbemException
	 */
	public static SWbemObjectSet<MsvmSyntheticEthernetPort> getMsvmSyntheticEthernetPort(SWbemServices service, MsvmComputerSystem vm) throws JIException, SWbemException {

		String path = vm.getObjectPath().getPath();
		String format = "Associators of {%s} where ResultClass = Msvm_SyntheticEthernetPort";
		String query = String.format(format, path);

		// Execute the query.
		SWbemObjectSet<MsvmSyntheticEthernetPort> compSysSet = service.execQuery(query, MsvmSyntheticEthernetPort.class);

		return compSysSet;

	}

	/**
	 * Gets the associated MsvmVmLANEndpoint for this synthetic Ethernet port.
	 * 
	 * @return The associated MsvmVmLANEndpoints for this synthetic Ethernet port.
	 * @throws Exception When an error occurs.
	 */
	public SWbemSet<MsvmVmLANEndpoint> getVmLANEndpoints() throws Exception {
		// Get the settings data.
		String path = super.getObjectPath().getPath();
		String format = "ASSOCIATORS OF {%s} " + "WHERE AssocClass=Msvm_DeviceSAPImplementation " + "ResultClass=Msvm_VmLANEndpoint " + "ResultRole=Dependent Role=Antecedent";
		String query = String.format(format, path);
		SWbemObjectSet<MsvmVmLANEndpoint> objSetVMLEP = super.getService().execQuery(query, MsvmVmLANEndpoint.class);
		return objSetVMLEP;
	}


}
