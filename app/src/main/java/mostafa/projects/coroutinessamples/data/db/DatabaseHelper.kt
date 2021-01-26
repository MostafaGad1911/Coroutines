package appssquare.projects.cut.data.db

import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Post

interface DatabaseHelper {

    suspend fun getPosts(): List<PostTable>

    suspend fun insertPost(card: PostTable)

    suspend fun deletePosts()

}
