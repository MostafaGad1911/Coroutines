package appssquare.projects.cut.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mostafa.projects.coroutinessamples.data.db.PostTable

@Dao
interface PostsDao {
    @Query("SELECT * FROM Posts")
    suspend fun getAllPosts(): List<PostTable>

    @Insert
    suspend fun InsertPost(post: PostTable)

    @Query("DELETE FROM Posts")
    suspend fun deleteAllPosts()


}