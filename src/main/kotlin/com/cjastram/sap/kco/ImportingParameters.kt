package com.cjastram.sap.kco

import com.sap.conn.jco.JCoParameterList

class ImportingParameters(private val parameters: JCoParameterList?,
                          private val writeonlyStructure: WriteonlyStructure = WriteonlyStructure(parameters)) : WriteonlyValues by writeonlyStructure {

    fun structure(structureName: String, init: WriteonlyValues.() -> Unit): WriteonlyValues {
        val writeonlyStructure = WriteonlyStructure(this.parameters!!.getStructure(structureName))
        writeonlyStructure.init()
        return writeonlyStructure
    }

    fun structure(structureName: String ): WriteonlyValues {
        return WriteonlyStructure(this.parameters!!.getStructure(structureName))
    }
}





