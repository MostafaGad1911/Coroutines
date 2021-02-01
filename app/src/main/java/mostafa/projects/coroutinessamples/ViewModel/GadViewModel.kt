package appssquare.projects.cut.data.db

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Comment
import mostafa.projects.coroutinessamples.data.repositiory.GadRepositiory
import mostafa.projects.coroutinessamples.data.model.Post
import java.net.UnknownHostException

class GadViewModel() : ViewModel() {

    var gadRepositiory: GadRepositiory = GadRepositiory()

    var postsData: MutableLiveData<ArrayList<PostTable>> = MutableLiveData()
    var commentsData: MutableLiveData<ArrayList<Comment>> = MutableLiveData()
    var error_msg: MutableLiveData<String> = MutableLiveData()

    init {
        fetchPosts()
    }


    fun fetchPosts() {
        viewModelScope.launch {
            try {
                when (gadRepositiory.GetPosts().code()) {
                    200 -> {
                        var posts = async { gadRepositiory.GetPosts().body() }
                        postsData.postValue(posts.await())
                    }
                    else -> {
                        var error = gadRepositiory.GetPosts().errorBody()?.string()
                        error_msg.postValue(error)
                    }
                }
            } catch (e: Exception) {
                error_msg.postValue(e.message)
            }

        }
    }

    fun fetchComments(postId:Int) {
        viewModelScope.launch {
            try {
                var commentsBody = gadRepositiory.GetPostComments(postId = postId)
                when (commentsBody.code()) {
                    200 -> {
                        var comments = async {commentsBody.body() }
                        commentsData.postValue(comments.await())
                    }
                    else -> {
                        var error = gadRepositiory.GetPosts().errorBody()?.string()
                        error_msg.postValue(error)
                    }
                }
            } catch (e: Exception) {
                error_msg.postValue(e.message)
            }

        }
    }

}
