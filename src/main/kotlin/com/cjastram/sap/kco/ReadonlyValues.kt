package com.cjastram.sap.kco

import com.sap.conn.jco.JCoRecord
import java.io.InputStream
import java.io.Reader
import java.math.BigInteger
import java.math.BigDecimal
import java.util.*


interface ReadonlyValues {

    val  readRecord : JCoRecord?

    operator fun get(name: String): Any {
        return readRecord!!.getValue(name)
    }

    fun boolean(name: String): Boolean {
        return readRecord!!.getChar(name) == 'X'
    }

    fun char(var1: String): Char {
        return readRecord!!.getChar(var1)
    }

    fun charArray(var1: String): CharArray {
        return readRecord!!.getCharArray(var1)
    }

    fun byte(var1: String): Byte {
        return readRecord!!.getByte(var1)
    }

    fun byteArray(var1: String): ByteArray {
        return readRecord!!.getByteArray(var1)
    }

    fun short(var1: String): Short {
        return readRecord!!.getShort(var1)
    }

    fun long(var1: String): Long {
        return readRecord!!.getLong(var1)
    }

    fun float(var1: String): Float {
        return readRecord!!.getFloat(var1)
    }

    fun double(var1: String): Double {
        return readRecord!!.getDouble(var1)
    }

    fun bigDecimal(var1: String): BigDecimal {
        return readRecord!!.getBigDecimal(var1)
    }

    fun date(var1: String): Date {
        return readRecord!!.getDate(var1)
    }

    fun time(var1: String): Date {
        return readRecord!!.getTime(var1)
    }

    fun getBinaryStream(var1: String): InputStream {
        return readRecord!!.getBinaryStream(var1)
    }

    fun getCharacterStream(var1: String): Reader {
        return readRecord!!.getCharacterStream(var1)
    }

    fun getBigInteger(name: String): BigInteger {
        return readRecord!!.getBigInteger(name)
    }

    fun getInt(name: String): Int {
        return readRecord!!.getInt(name)
    }

    fun getString(name: String): String {
        return readRecord!!.getString(name)
    }

    fun  table(name: String): Table {
        return Table( readRecord!!.getTable(name) )
    }
}