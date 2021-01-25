package mostafa.projects.coroutinessamples.data.repositiory

import mostafa.projects.coroutinessamples.GadHelper
import mostafa.projects.coroutinessamples.data.model.Post
import mostafa.projects.coroutinessamples.data.apis.ApiInterfaces
import retrofit2.Response

class GadRepositiory {
    var apiInterfaces: ApiInterfaces
    init {
        apiInterfaces = GadHelper.GetServices()
    }

    suspend fun GetPosts(): Response<ArrayList<Post>> {
        return apiInterfaces.GetPosts()!!
    }
}