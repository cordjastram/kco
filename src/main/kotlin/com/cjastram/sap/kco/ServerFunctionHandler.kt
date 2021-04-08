package com.cjastram.sap.kco

import com.sap.conn.jco.JCoFunction
import com.sap.conn.jco.server.JCoServerContext
import com.sap.conn.jco.server.JCoServerFunctionHandler

abstract class ServerFunctionHandler : JCoServerFunctionHandler {

    override fun handleRequest(context: JCoServerContext , function: JCoFunction) {

        val bapiret2 = function.exportParameterList.getStructure("ES_BAPIRET2")
        var bapiret2Type = 'S'
        var bapiret2Message = "OK"
        try {
            this.handleFunctionCall(context, function)
            bapiret2.setValue("MESSAGE", "OK")

        } catch (e:Throwable) {
            bapiret2Message = e.message ?: "Unknow Error"
            bapiret2Type = 'E'
        }
        bapiret2.setValue("TYPE", bapiret2Type)
        bapiret2.setValue("MESSAGE", bapiret2Message)
    }

    abstract fun handleFunctionCall(context: JCoServerContext, function: JCoFunction)
}