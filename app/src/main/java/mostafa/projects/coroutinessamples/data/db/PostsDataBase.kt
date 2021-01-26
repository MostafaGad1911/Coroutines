package appssquare.projects.cut.data.db

import androidx.room.*
import mostafa.projects.coroutinessamples.data.db.PostTable

@Database(entities = [PostTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cutDao(): PostsDao
}
