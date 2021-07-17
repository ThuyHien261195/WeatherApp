package com.media2359.weatherapp.ui.test_util

import androidx.room.RoomDatabase
import com.media2359.weatherapp.content.database.RoomTransactionExecutor

/**
 * Perform a database transaction without using another [CoroutineDispatcher]
 * so that test case can control execution of coroutines
 */
class TestTransactionExecutor(private val db: RoomDatabase) : RoomTransactionExecutor {
    override suspend fun <R> execute(block: suspend () -> R): R {
        @Suppress("DEPRECATION")
        db.beginTransaction()
        try {
            val result = block.invoke()
            @Suppress("DEPRECATION")
            db.setTransactionSuccessful()
            return result
        } finally {
            @Suppress("DEPRECATION")
            db.endTransaction()
        }
    }
}