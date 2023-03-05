package com.ligh.dao

import androidx.room.*
import com.ligh.room.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("select * from word_table")
    fun getAll() : Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(wordDao: Word)
}