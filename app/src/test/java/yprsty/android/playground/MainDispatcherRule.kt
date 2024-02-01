package yprsty.android.playground

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * JUnit TestRule for managing the main dispatcher during coroutine testing.
 *
 * This rule facilitates testing of coroutines that use the main dispatcher by providing a way to
 * set and reset the main dispatcher for JUnit tests. It ensures proper coroutine execution on the
 * main thread in a controlled manner during test scenarios.
 *
 * @param testDispatcher The TestDispatcher to be used for coroutine execution during testing.
 *
 * Docs: [Setting the Main dispatcher](https://developer.android.com/kotlin/coroutines/test#setting-main-dispatcher)
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    /**
     * Sets the main dispatcher to the specified [testDispatcher] before the test starts.
     */
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Resets the main dispatcher to its original state after the test finishes.
     */
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}