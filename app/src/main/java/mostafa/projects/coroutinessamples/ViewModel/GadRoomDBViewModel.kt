package mostafa.projects.coroutinessamples.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import appssquare.projects.cut.data.db.DatabaseHelper
import kotlinx.coroutines.launch
import mostafa.projects.coroutinessamples.data.db.PostTable

class GadRoomDBViewModel(private val dbHelper: DatabaseHelper) :
    ViewModel() {

    var posts: MutableLiveData<List<PostTable>> = MutableLiveData()
    var post_inserted: MutableLiveData<Boolean> = MutableLiveData()
    var postsDeleted: MutableLiveData<Boolean> = MutableLiveData()

    var error_msg:MutableLiveData<String> = MutableLiveData()


    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val postData = dbHelper.getPosts()
                posts.postValue(postData)
            } catch (e: Exception) {
                error_msg.postValue(e.message)
            }
        }
    }

    fun addPost(post_table: PostTable) {
        viewModelScope.launch {
            try {
                dbHelper.insertPost(post_table)
                post_inserted.postValue(true)
            } catch (e: java.lang.Exception) {
                error_msg.postValue(e.message)
            }
        }
    }



    fun deletePosts() {
        viewModelScope.launch {
            try {
                val usersFromDb = dbHelper.deletePosts()
                postsDeleted.postValue(true)
            } catch (e: Exception) {
                error_msg.postValue(e.message)
            }
        }
    }

}
