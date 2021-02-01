package mostafa.projects.coroutinessamples.data.apis

import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Comment
import mostafa.projects.coroutinessamples.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaces {

    @GET("posts")
    suspend fun GetPosts():Response<ArrayList<PostTable>>

    @GET("comments/{post_id}/comments")
    suspend fun GetPostComments(@Path("post_id") postId:Int):Response<ArrayList<Comment>>
}