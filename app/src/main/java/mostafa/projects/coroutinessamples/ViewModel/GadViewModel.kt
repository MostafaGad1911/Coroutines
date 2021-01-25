package appssquare.projects.cut.data.db

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mostafa.projects.coroutinessamples.data.repositiory.GadRepositiory
import mostafa.projects.coroutinessamples.data.model.Post

class GadViewModel() : ViewModel() {

    var gadRepositiory: GadRepositiory = GadRepositiory()

    var postsData: MutableLiveData<ArrayList<Post>> = MutableLiveData()
    var error_msg: MutableLiveData<String> = MutableLiveData()


    fun fetchPosts() {
        viewModelScope.launch {
            when (gadRepositiory.GetPosts().code()) {
                200 -> {
                    var posts = gadRepositiory.GetPosts()
                    postsData.postValue(posts.body())
                }
                else -> {
                    var error = gadRepositiory.GetPosts().errorBody()?.string()
                    error_msg.postValue(error)
                }
            }
        }
    }

}
