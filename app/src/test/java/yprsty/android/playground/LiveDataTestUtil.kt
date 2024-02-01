package yprsty.android.playground

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Awaits and retrieves the value emitted by the LiveData within the specified time frame.
 *
 * This utility function is designed for testing purposes, allowing you to wait for and retrieve
 * the value emitted by a LiveData. It is particularly useful in unit tests to perform assertions
 * on LiveData values.
 *
 * @param time The maximum time to wait.
 * @param timeUnit The time unit of the [time] parameter.
 * @param afterObserve Callback to be executed after observing the LiveData.
 * @return The value emitted by the LiveData.
 * @throws TimeoutException if the value is not set within the specified time.
 */
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}