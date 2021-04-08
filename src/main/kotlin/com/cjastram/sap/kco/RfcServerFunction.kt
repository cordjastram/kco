package com.cjastram.sap.kco


import com.sap.conn.jco.JCoFunction
import com.sap.conn.jco.JCoParameterList


class RfcServerFunction(function: JCoFunction) : Function(function) {

    override val importingParameters: ImportingParameters = ImportingParameters(function.exportParameterList)
    override val exportingParameters: ExportingParameters = ExportingParameters(function.importParameterList)

}