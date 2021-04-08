package com.cjastram.sap.kco

import com.sap.conn.jco.JCoParameterList

class ExportingParameters(private val parameters: JCoParameterList?,
                          private val readonlyStructure: ReadonlyValues = ReadonlyStructure(parameters)) : ReadonlyValues by readonlyStructure {

    fun structure(structureName: String, init: ReadonlyValues.() -> Unit): ReadonlyValues{
        val readonlyStructure = ReadonlyStructure(this.parameters!!.getStructure(structureName))
        readonlyStructure.init()
        return readonlyStructure
    }
}



