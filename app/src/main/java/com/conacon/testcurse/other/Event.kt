package com.conacon.testcurse.other

open class Event <out T> (private val content : T) {
    var hasBeenHandled = false
        private set // permite lectura externa pero no escritura

    /**
     * Retorna el contenido y previene su uso de nuevo
     */

    fun getContentIfNotHandled() = if(hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    /**
     * Retorna el contenido , incluso si este ya a sido manejado
     */

    fun peekContent() = content

}