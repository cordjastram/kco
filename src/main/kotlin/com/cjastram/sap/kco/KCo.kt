package com.cjastram.sap.kco

import com.sap.conn.jco.AbapException
import com.sap.conn.jco.JCoException
import com.sap.conn.jco.server.JCoServerFactory

private val defaultHandler : (Throwable) -> Unit = { println(it.message) }


fun sapDestination(name : String,
                   abapExceptionHandler: (AbapException) -> Unit = defaultHandler,
                   jcoExceptionHandler: (JCoException) -> Unit = defaultHandler,
                   throwableHandler: (Throwable) -> Unit = defaultHandler,
                   init : Destination.() -> Unit  ) {
    Destination(name, abapExceptionHandler, jcoExceptionHandler, throwableHandler).init()
}

fun sapTransaction(name : String,
                   abapExceptionHandler: (AbapException) -> Unit = defaultHandler,
                   jcoExceptionHandler: (JCoException) -> Unit = defaultHandler,
                   throwableHandler: (Throwable) -> Unit =  defaultHandler,
                   init: Destination.() -> Unit) {

    val destination = Destination(name, abapExceptionHandler, jcoExceptionHandler, throwableHandler)
    try {
        destination.init()
        destination.commit.execute(  )
    } catch (e: AbapException) {
        destination.abapExceptionHandler.invoke(e)
        destination.rollback.execute()
    } catch (e: JCoException) {
        destination.jcoExceptionHandler.invoke(e)
        destination.rollback.execute()
    } catch (e: Throwable) {
        destination.throwableHandler.invoke(e)
        destination.rollback.execute (   )
    }
}