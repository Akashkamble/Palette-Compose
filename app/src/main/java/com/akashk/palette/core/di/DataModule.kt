package com.akashk.palette.core.di

import android.content.Context
import com.akash.palette.sqldelight.PaletteQueries
import com.akashk.palette.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

     @Provides
     @Singleton
     fun provideSqlDelightDataBase(
         @ApplicationContext
         applicationContext: Context
     ) : Database {
         val driver: SqlDriver = AndroidSqliteDriver(
             schema = Database.Schema,
             context = applicationContext,
             name  = "palette.db"
         )
         return Database(driver)
     }

    @Provides
    fun providePaletteQueries(
        database: Database
    ) : PaletteQueries {
        return database.paletteQueries
    }
}