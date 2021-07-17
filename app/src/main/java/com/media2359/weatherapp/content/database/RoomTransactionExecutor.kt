package com.media2359.weatherapp.content.database

interface RoomTransactionExecutor {
    suspend fun <R> execute(block: suspend() -> R): R
}