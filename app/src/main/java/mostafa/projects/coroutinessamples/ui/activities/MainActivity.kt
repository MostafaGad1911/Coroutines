package mostafa.projects.coroutinessamples.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.crystal.crystalpreloaders.widgets.CrystalPreloader
import kotlinx.coroutines.*
import mostafa.projects.coroutinessamples.GadHelper
import mostafa.projects.coroutinessamples.R
import mostafa.projects.coroutinessamples.ViewModel.GadViewModel
import mostafa.projects.coroutinessamples.data.model.Post
import mostafa.projects.coroutinessamples.ui.adapter.PostsAdapter
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity()  , CoroutineScope  {


    lateinit var postsJob: Job

    lateinit var posts_loading:CrystalPreloader
    lateinit var posts_recycler:RecyclerView
    lateinit var adapter:PostsAdapter

    private val gadViewModel: GadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitViews()
        postsJob = Job()
        // Load Posts
        showloading()
        launch{
            gadViewModel.GetPosts()
            hideLoading()
            posts_recycler.adapter = adapter
        }


    }

    fun showloading(){
        posts_loading.visibility = View.VISIBLE
    }

    fun hideLoading(){
        posts_loading.visibility = View.GONE
    }

    private fun InitViews() {
        posts_recycler = findViewById(R.id.posts_recycler)
        posts_loading = findViewById(R.id.posts_loading)
    }

    suspend fun getPosts(): ArrayList<Post> {
        var posts: ArrayList<Post> = ArrayList()
        when (GadHelper.GetServices().GetPosts().code()) {
            200 -> {
                posts = GadHelper.GetServices().GetPosts().body()!!
            }
            else -> {
                Toast.makeText(this , "error" , Toast.LENGTH_LONG).show()
            }
        }
        return posts
    }

    override fun onDestroy() {
        super.onDestroy()
        postsJob.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = postsJob + Dispatchers.Main


}