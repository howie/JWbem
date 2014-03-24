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

import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.trendmicro.jwbem.SWbemServices;
import com.trendmicro.jwbem.msvm.MsvmObject;

/**
 * This class represents an internal Ethernet port (network adapter).
 * 
 * @author akutz
 * @remarks This type of Ethernet ports gives virtual machines access to the partition running the networking software. The internal
 *          network adapters allow the virtual machine's network traffic to be routed or filtered before leaving the physical
 *          system.
 */
public class MsvmInternalEthernetPort extends MsvmObject {
	/**
	 * Initializes a new instance of the MsvmInternalEthernetPort class.
	 * 
	 * @param objectDispatcher The underlying dispatch object used to communicate with the server.
	 * @param service The service connection.
	 */
	public MsvmInternalEthernetPort(IJIDispatch objectDispatcher, SWbemServices service) {
		super(objectDispatcher, service);
	}

}