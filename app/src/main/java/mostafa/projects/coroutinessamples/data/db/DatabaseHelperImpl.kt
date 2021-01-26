package appssquare.projects.cut.data.db

import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Post

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getPosts(): List<PostTable> = appDatabase.cutDao().getAllPosts()

    override suspend fun insertPost(post: PostTable) = appDatabase.cutDao().InsertPost(post = post)

    override suspend fun deletePosts() =
        appDatabase.cutDao().deleteAllPosts()
}
