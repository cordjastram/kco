package com.cjastram.sap.kco

import com.sap.conn.jco.JCoRecord

class  Structure(private val parameters: JCoRecord?) : WriteonlyValues, ReadonlyValues, ReadWriteValues  {
    override val writeRecord: JCoRecord?
        get() = parameters

    override val readRecord: JCoRecord?
        get() = parameters
}