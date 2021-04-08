package com.cjastram.sap.kco

import com.sap.conn.jco.JCoRecord
import java.math.BigInteger


interface WriteonlyValues {

    val writeRecord: JCoRecord?

    operator fun set(name: String, value: String) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: BigInteger) {
        writeRecord!!.setValue(name, value)
    }

    // additional set for Boolean
    operator fun set(name: String, value: Boolean) {
        writeRecord!!.setValue(name, if ( value ) 'X' else ' ')
    }

    operator fun set(name: String, value: Char) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: CharArray) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Short) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Int) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Long) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Float) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Double) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Byte) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: ByteArray) {
        writeRecord!!.setValue(name, value)
    }

    operator fun set(name: String, value: Any) {
        writeRecord!!.setValue(name, value)
    }
}