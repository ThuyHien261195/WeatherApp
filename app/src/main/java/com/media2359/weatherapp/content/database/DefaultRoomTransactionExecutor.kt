package com.media2359.weatherapp.content.database

import androidx.room.withTransaction

class DefaultRoomTransactionExecutor(
    private val db: AppDatabase
): RoomTransactionExecutor {

    override suspend fun <R> execute(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}