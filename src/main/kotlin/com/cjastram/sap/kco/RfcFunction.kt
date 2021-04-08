package com.cjastram.sap.kco

import com.sap.conn.jco.AbapException
import com.sap.conn.jco.JCoDestination
import com.sap.conn.jco.JCoFunction
import com.sap.conn.jco.JCoParameterList



class RfcFunction(private val destination: JCoDestination, name: String) : Function( destination.repository.getFunction(name)) {
    private var successfull = false
    private var abapException: AbapException? = null
    private var throwable: Throwable? = null

    override val importingParameters = ImportingParameters(function.importParameterList)
    override val exportingParameters = ExportingParameters(function.exportParameterList)

    fun onSuccess(init: () -> Unit) {
        if (successfull) {
            init()
        }
    }

    fun execute(): RfcFunction {
        try {
            this.successfull = false
            function.execute(destination)
            this.successfull = true
        }catch(e: AbapException) {
            this.abapException = e
        } catch (e: Throwable) {
            this.throwable = e
        }
        return this
    }

    fun onAbapException(init: (e: Throwable) -> Unit) {
        val localException = this.abapException
        if (localException != null) {
            init(localException)
        }
    }


}


