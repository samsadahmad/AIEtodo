package com.aie.aietodo.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This utility class will hold all coroutine methods.
 */
object coroutines{

    /**
     * @method : main
     * @desc : This method used for coroutine scope
     * Dispatchers.Main for main safety
     * @param : Lamda function
     * @return : Lazy<Deferred<T>
     */
    fun main(work: suspend (() ->Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    /**
     * @method : main
     * @desc : This method used for coroutine scope
     * @param : Lamda function
     * Dispatchers.IO for network call
     * @return : Lazy<Deferred<T>
     */
    fun io(work: suspend (() ->Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    /**
     * @method : default
     * @desc : This method used for coroutine scope
     * @param : Lamda function
     * Dispatchers.IO for cpu intensive work
     * @return : Lazy<Deferred<T>
     */
    fun default(work: suspend (() ->Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}