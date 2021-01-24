package mostafa.projects.coroutinessamples.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import appssquare.projects.cut.data.db.GadViewModel
import com.crystal.crystalpreloaders.widgets.CrystalPreloader
import kotlinx.coroutines.*
import mostafa.projects.coroutinessamples.R
import mostafa.projects.coroutinessamples.data.model.Post
import mostafa.projects.coroutinessamples.ui.adapter.PostsAdapter

class MainActivity : AppCompatActivity()  {



    lateinit var posts_loading:CrystalPreloader
    lateinit var posts_recycler:RecyclerView
    lateinit var posts_adapter:PostsAdapter
    var postsList:ArrayList<Post> = ArrayList()

    private val gadViewModel: GadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitViews()
        // Load Posts
        CoroutineScope(Dispatchers.Main).launch {
            showloading()
            withContext(Dispatchers.IO){
                gadViewModel.fetchPosts()
            }
            gadViewModel.postsData.observe(this@MainActivity , Observer {
                hideLoading()
                postsList.addAll(it)
                posts_adapter.notifyItemRangeInserted(postsList.size + 1 , it.size)
            })

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

        posts_adapter = PostsAdapter(posts = postsList)
        posts_recycler.adapter = posts_adapter
    }



}