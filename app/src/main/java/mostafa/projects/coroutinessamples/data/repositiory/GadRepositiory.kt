package mostafa.projects.coroutinessamples.data.repositiory

import mostafa.projects.coroutinessamples.GadHelper
import mostafa.projects.coroutinessamples.data.model.Post
import mostafa.projects.coroutinessamples.data.apis.ApiInterfaces
import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Comment
import retrofit2.Response

class GadRepositiory {
    var apiInterfaces: ApiInterfaces
    init {
        apiInterfaces = GadHelper.GetServices()
    }

    suspend fun GetPosts(): Response<ArrayList<PostTable>> {
        return apiInterfaces.GetPosts()!!
    }

    suspend fun GetPostComments(postId:Int): Response<ArrayList<Comment>> {
        return apiInterfaces.GetPostComments(postId = postId)!!
    }

}