package com.media2359.weatherapp.content.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg item: T): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(items: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(vararg item: T)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(items: List<T>)
}

suspend fun<T: Any> BaseDao<T>.insertOrUpdate(items: List<T>) {
    val rowIds = insert(items)
    val itemsToUpdate = rowIds.mapIndexedNotNull { index, rowId ->
        if (rowId == -1L) {
            items[index]
        } else {
            null
        }
    }
    update(itemsToUpdate)
}

suspend fun<T: Any> BaseDao<T>.inserOrUpdate(vararg items: T) {
    insertOrUpdate(items.asList())
}