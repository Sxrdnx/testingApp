package com.conacon.testcurse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ShoppingItem::class], version = 1)

abstract class ShoppingIteDatabase: RoomDatabase() {
    abstract fun shoppingDao(): ShoppinDao
}