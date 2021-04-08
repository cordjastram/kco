package com.cjastram.sap.kco

import com.sap.conn.jco.JCoFunction
import com.sap.conn.jco.JCoParameterList

abstract class Function(val function: JCoFunction) {

    public abstract val importingParameters: ImportingParameters
    public abstract val exportingParameters: ExportingParameters
    private val tableParameters: JCoParameterList? = function.tableParameterList

    fun exporting(init: ExportingParameters.() -> Unit): ExportingParameters {
        exportingParameters.init()
        return exportingParameters
    }

    fun importing(init: ImportingParameters.() -> Unit): ImportingParameters {
        importingParameters.init()
        return importingParameters
    }

    fun table(name: String, init: Table.() -> Unit): Table {
        val table = Table(tableParameters!!.getTable(name))
        table.init()
        return table
    }

    fun table(name: String): Table {
        return Table(tableParameters!!.getTable(name))
    }

}