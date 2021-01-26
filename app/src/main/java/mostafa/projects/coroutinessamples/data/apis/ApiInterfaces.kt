package mostafa.projects.coroutinessamples.data.apis

import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaces {

    @GET("posts")
    suspend fun GetPosts():Response<ArrayList<PostTable>>
}