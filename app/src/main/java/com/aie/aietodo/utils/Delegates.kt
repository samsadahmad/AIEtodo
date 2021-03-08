package com.aie.aietodo.utils
import kotlinx.coroutines.*

/**
 * @method : lazyDeferred
 * @desc : This delegate method used for coroutine asyn/wait
 * @param : Lamda function
 * @return : Lazy<Deferred<T>
 */
fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}