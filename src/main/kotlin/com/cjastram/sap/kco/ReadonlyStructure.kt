package com.cjastram.sap.kco

import com.sap.conn.jco.JCoRecord

class ReadonlyStructure(private val record: JCoRecord?) : ReadonlyValues {
    override val readRecord: JCoRecord?
        get() = record
}