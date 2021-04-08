/**
 *   Copyright 2021 by Cord Jastram, cord.jastram@gmail.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.cjastram.sap.kco

import com.sap.conn.jco.AbapException
import com.sap.conn.jco.JCoDestination
import com.sap.conn.jco.JCoDestinationManager
import com.sap.conn.jco.JCoException


class Destination(
    destinationName: String,
    val abapExceptionHandler: (AbapException) -> Unit,
    val jcoExceptionHandler: (JCoException) -> Unit,
    val throwableHandler: (Throwable) -> Unit
) {

    private var destination: JCoDestination?
    internal val commit: RfcFunction
    internal val rollback: RfcFunction

    init {
        try {
            destination = JCoDestinationManager.getDestination(destinationName)
        } catch (e: Throwable) {
            destination = null
            println(e.message)
        }
        commit = RfcFunction(this.destination!!, "BAPI_TRANSACTION_COMMIT")
        rollback = RfcFunction(this.destination!!, "BAPI_TRANSACTION_ROLLBACK")
    }

    fun rfcFunction(name: String, init: RfcFunction.() -> Unit) {
        RfcFunction(this.destination!!, name).init()
    }

}