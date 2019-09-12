package com.example.roomself.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User):Completable

    @Query("Select * from user_table")
    fun getAllUsers(): Flowable<List<User>>

    @Query("select * from user_table where id = :id")
    fun getById(id:Int)
}