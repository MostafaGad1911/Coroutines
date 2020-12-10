package mostafa.projects.coroutinessamples.ViewModel

import android.widget.Toast
import androidx.constraintlayout.solver.widgets.Helper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mostafa.projects.coroutinessamples.GadHelper
import mostafa.projects.coroutinessamples.data.model.Post

class GadViewModel : ViewModel() {
    var postsData: LiveData<ArrayList<Post>> = MutableLiveData()
    var error_msg :MutableLiveData<String> = MutableLiveData()


    init {
        viewModelScope.launch {
            postsData as MutableLiveData
        }
    }
    suspend fun GetPosts() {
        var posts = GadHelper.GetServices().GetPosts()
        when(posts.code()){
            200 -> {
                postsData.postValue(posts.body())
            }
            else -> {
                error_msg.postValue(posts.errorBody()?.string())
            }
        }
    }
}