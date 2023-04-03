package com.example.airportapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// abstract class похож на interface, но в AC можнт быть реализация функций через CO
@Database(entities = [FavouriteItem::class, AirportItem::class], version = 1, exportSchema = false)
abstract class AirportDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDAO
    companion object {
        /**
         * [Volatile] значит, что база данных не будет кэшироваться, будет храниться только в главной памяти
         * Если один поток изменил database, это сразу увидят все остальные потоки
         * [Instance] ссылка на базу данных
         */
        @Volatile
        private var Instance: AirportDatabase? = null

        fun getDatabase(context: Context): AirportDatabase {
            // следующий код является критическим сегментом, поэтому доступ к нему реализуется через
            // synchronized, чтобы к сегменту не могли обратиться несколько процессов одновременно
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AirportDatabase::class.java, "item_database")
                    // в принципе миграции используются для backup-ов баз данных,
                    // но здесь при измнении параметра БД удалятся и создаётся заново
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{ Instance = it }
            }
        }
    }
}