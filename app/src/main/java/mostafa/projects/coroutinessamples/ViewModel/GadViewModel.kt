package appssquare.projects.cut.data.db

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mostafa.projects.coroutinessamples.GadHelper
import mostafa.projects.coroutinessamples.data.model.Post

class GadViewModel() : ViewModel() {

    var postsData: MutableLiveData<ArrayList<Post>> = MutableLiveData()
    var error_msg: MutableLiveData<String> = MutableLiveData()


     fun fetchPosts() {
        viewModelScope.launch {
            var posts_code = GadHelper.GetServices().GetPosts().code()
            when (posts_code) {
                200 -> {
                    postsData.postValue(GadHelper.GetServices().GetPosts().body())
                }
                else -> {
                    var error = GadHelper.GetServices().GetPosts().errorBody()?.string()
                    error_msg.postValue(error)
                }
            }

        }
    }

}
