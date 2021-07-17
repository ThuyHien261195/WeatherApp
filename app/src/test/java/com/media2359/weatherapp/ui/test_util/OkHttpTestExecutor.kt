package com.media2359.weatherapp.ui.test_util

import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * An executor that delegates its execution to a provided [TestCoroutineDispatcher].
 * Use this to replace OkHttp's default thread management in testing situations:
 *
 * ```
 * OkHttpClient.Builder()
 * .dispatcher(Dispatcher(OkHttpTestExecutor(testDispatcher)))
 * .build()
 * ```
 */
class OkHttpTestExecutor(testDispatcher: TestCoroutineDispatcher) : ExecutorService {
    private val testExecutor = testDispatcher.asExecutor()

    override fun execute(command: Runnable?) {
        testExecutor.execute(command)
    }

    override fun shutdown() {
        TODO("Not yet implemented")
    }

    override fun shutdownNow(): MutableList<Runnable> {
        TODO("Not yet implemented")
    }

    override fun isShutdown(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isTerminated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun awaitTermination(timeout: Long, unit: TimeUnit?): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> submit(task: Callable<T>?): Future<T> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> submit(task: Runnable?, result: T): Future<T> {
        TODO("Not yet implemented")
    }

    override fun submit(task: Runnable?): Future<*> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> invokeAll(tasks: MutableCollection<out Callable<T>>?): MutableList<Future<T>> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> invokeAll(
        tasks: MutableCollection<out Callable<T>>?,
        timeout: Long,
        unit: TimeUnit?
    ): MutableList<Future<T>> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> invokeAny(tasks: MutableCollection<out Callable<T>>?): T {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> invokeAny(
        tasks: MutableCollection<out Callable<T>>?,
        timeout: Long,
        unit: TimeUnit?
    ): T {
        TODO("Not yet implemented")
    }
}