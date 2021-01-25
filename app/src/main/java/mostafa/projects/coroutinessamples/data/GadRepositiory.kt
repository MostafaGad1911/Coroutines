package mostafa.projects.coroutinessamples.data

import androidx.lifecycle.MutableLiveData
import mostafa.projects.coroutinessamples.GadHelper
import mostafa.projects.coroutinessamples.data.model.Post
import mostafa.projects.coroutinessamples.data.requests.ApiInterfaces
import mostafa.projects.coroutinessamples.ui.adapter.PostsAdapter

class GadRepositiory {

    var apiInterfaces: ApiInterfaces

    init {
        apiInterfaces = GadHelper.GetServices()
    }

    suspend fun GetPosts(): ArrayList<Post> {
        return apiInterfaces.GetPosts().body()!!
    }
}