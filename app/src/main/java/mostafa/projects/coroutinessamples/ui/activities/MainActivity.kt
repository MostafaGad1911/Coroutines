package mostafa.projects.coroutinessamples.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import appssquare.projects.cut.data.db.DatabaseBuilder
import appssquare.projects.cut.data.db.DatabaseHelperImpl
import appssquare.projects.cut.data.db.GadViewModel
import com.crystal.crystalpreloaders.widgets.CrystalPreloader
import kotlinx.coroutines.*
import mostafa.projects.coroutinessamples.R
import mostafa.projects.coroutinessamples.ViewModel.GadRoomDBViewModel
import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Post
import mostafa.projects.coroutinessamples.ui.adapter.PostsAdapter
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity() {


    lateinit var posts_loading: CrystalPreloader
    lateinit var posts_recycler: RecyclerView
    lateinit var posts_adapter: PostsAdapter
    var postsList: ArrayList<PostTable> = ArrayList()
    lateinit var error_lyt:ConstraintLayout

    val gadViewModel:GadViewModel by viewModels()
    lateinit var roomDBViewModel: GadRoomDBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitObjects()
        InitViews()
        // Load Posts
        CoroutineScope(Dispatchers.Main).launch {
            showloading()
            gadViewModel.postsData.observe(this@MainActivity, Observer {
                hideLoading()
                roomDBViewModel.deletePosts()
                for (i in 0 until it?.size!!) {
                    roomDBViewModel.addPost(post_table = it.get(i))
                }
                postsList.addAll(it)
                posts_adapter.notifyItemRangeInserted(postsList.size + 1, it.size)
            })
            roomDBViewModel.posts.observe(this@MainActivity , Observer {
                postsList.clear()
                error_lyt.visibility = View.GONE
                if(it.size > 0){
                    showToast(getString(R.string.offline))
                    postsList.addAll(it)
                    posts_adapter.notifyItemRangeInserted(postsList.size + 1, it.size)
                }else{
                    error_lyt.visibility = View.VISIBLE
                }
            })
            gadViewModel.error_msg.observe(this@MainActivity , Observer {
                hideLoading()
//                showToast(it)
                roomDBViewModel.fetchPosts()
                error_lyt.visibility = View.VISIBLE
            })
            roomDBViewModel.error_msg.observe(this@MainActivity , Observer {
                hideLoading()
                showToast(it)
            })





        }
    }

    private fun InitObjects() {
        val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(this))
        roomDBViewModel = GadRoomDBViewModel(dbHelper = dbHelper)
    }

    override fun onStop() {
        super.onStop()
        roomDBViewModel.posts.removeObservers(this)
        roomDBViewModel.error_msg.removeObservers(this)
        gadViewModel.postsData.removeObservers(this)
        gadViewModel.error_msg.removeObservers(this)
    }

    fun showloading() {
        posts_loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        posts_loading.visibility = View.GONE
    }

    private fun InitViews() {
        posts_recycler = findViewById(R.id.posts_recycler)
        posts_loading = findViewById(R.id.posts_loading)
        error_lyt = findViewById(R.id.error_lyt)
        posts_adapter = PostsAdapter(posts = postsList)
        posts_recycler.adapter = posts_adapter
    }

    override fun onBackPressed() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }


//    override fun GetComments(post_id: Int) {
//        var comments_intent = Intent(this, PostComments::class.java)
//        comments_intent.putExtra("post_id", post_id)
//        startActivity(comments_intent)
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
//    }

}