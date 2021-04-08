package com.cjastram.sap.kco

import com.sap.conn.jco.JCoRecord

class WriteonlyStructure(private val internalRecord: JCoRecord?) : WriteonlyValues {
    override val writeRecord: JCoRecord
        get() = internalRecord!!
}