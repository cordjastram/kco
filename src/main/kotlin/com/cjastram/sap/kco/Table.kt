package com.cjastram.sap.kco

import com.sap.conn.jco.JCoTable
import java.util.*

open class Table(private val table: JCoTable, private val structure: Structure = Structure(table) ) : ReadWriteValues by structure, Sequence<ReadWriteValues> {

    fun appendRow() : Table {
        table.appendRow()
        return this
    }

    override fun iterator(): Iterator<ReadWriteValues> {
        return Table.TableIterator(this.table)
    }

    /**
     *  return the underlying JCoTable object
     */
    fun asJCoTable(): JCoTable {
        return table
    }

    fun setValue(data : ByteArray, fieldName: String,  length: Int) : Unit {
        // remove all data
        table.clear()

        var startOffset = 0
        var endOffset = startOffset + length - 1

        var numberOfRowsToProcess = data.size / length
        val rest = data.size % length

        while (numberOfRowsToProcess > 0) {
            numberOfRowsToProcess--
            table.appendRow()
            var buffer = Arrays.copyOfRange(data, startOffset, endOffset)
            table.setValue(fieldName, buffer)
            startOffset += length
            endOffset = startOffset + length - 1
        }

        if (rest > 0) {
            table.appendRow()
        }
    }


    internal class TableIterator(private var table: JCoTable) : Iterator<ReadWriteValues> {

        var index : Int = 0
        val structure =  Structure(table)

        override fun hasNext(): Boolean {
            return index < table.numRows
        }

        override fun next(): ReadWriteValues {
            table.row = index
            index++
            return structure
        }
    }
}


