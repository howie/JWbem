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

package tw.howie.jwbem.msvm.virtualsystem;

import org.jinterop.dcom.impls.automation.IJIDispatch;

import tw.howie.jwbem.SWbemServices;
import tw.howie.jwbem.msvm.MsvmObject;

/**
 * Used in the getSummaryInformation method in the MsvmVirtualSystemManagementService class to quickly retrieve common information
 * related to a virtual system or snapshot.
 * 
 * @author howie_yu
 * @author akutz
 */
public class MsvmSummaryInformation extends MsvmObject {
	/**
	 * Initializes an instance of the MsvmSummaryInformation class.
	 * 
	 * @param objectDispatcher The underlying dispatch object used to communicate with the server.
	 * @param service The service connection.
	 */
	public MsvmSummaryInformation(IJIDispatch objectDispatcher, SWbemServices service) {
		super(objectDispatcher, service);
	}

	/**
	 * @return GuestOperatingSystem
	 */
	public String getGuestOperatingSystem() {
		try {
			return super.getProperty("GuestOperatingSystem", String.class);
		} catch (Exception e) {
			return "N/A";
		}
	}

	/**
	 * Gets the current processor load.
	 * 
	 * @return The current processor load.
	 */
	public int getProcessorLoad() {
		try {
			return super.getProperty("ProcessorLoad", Integer.class);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Gets the current memory usage.
	 * 
	 * @return The current memory usage.
	 */
	public int getMemoryUsage() {
		try {
			return super.getProperty("MemoryUsage", Integer.class);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * @return
	 */
	public int getNumberOfProcessors() {

		try {
			return super.getProperty("NumberOfProcessors", Integer.class);
		} catch (Exception e) {
			return -1;
		}

	}
	
}
