package mostafa.projects.coroutinessamples.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import appssquare.projects.cut.data.db.GadViewModel
import com.crystal.crystalpreloaders.widgets.CrystalPreloader
import mostafa.projects.coroutinessamples.R
import mostafa.projects.coroutinessamples.data.model.Comment
import mostafa.projects.coroutinessamples.ui.adapter.CommentsAdapter

class PostsComments : BaseActivity() {

    lateinit var post_comments_recycler:RecyclerView
    lateinit var comments_loading:CrystalPreloader
    lateinit var error_lyt:ConstraintLayout

    lateinit var commentsAdapter: CommentsAdapter
    var commentsList:ArrayList<Comment> = ArrayList()

    var postId:Int = 0
    val gadViewModel: GadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_comments)
        initViews()
        initObjects()
        observeComments()
    }

    fun observeComments() {
       gadViewModel.commentsData.observe(this , Observer {
           hideLoading()
           commentsList.addAll(it)
           commentsAdapter.notifyItemRangeInserted(commentsList.size + 1 , it.size)
       })
       gadViewModel.error_msg.observe(this , Observer {
           hideLoading()
           showToast(it)
       })
    }


    fun showLoading(){
        comments_loading.visibility = View.VISIBLE
    }

    fun hideLoading(){
        comments_loading.visibility = View.GONE
    }

    private fun initObjects() {
        intent.let {
            postId = it.getIntExtra("post_id" , 0)
            showLoading()
            gadViewModel.fetchComments(postId = postId)
        }
        commentsAdapter = CommentsAdapter(comments = commentsList)
        post_comments_recycler.adapter = commentsAdapter
    }

    override fun onStop() {
        super.onStop()
        gadViewModel.commentsData.removeObservers(this)
        gadViewModel.error_msg.removeObservers(this)
    }

    override fun onBackPressed() {
        var postsIntent = Intent(this, MainActivity::class.java)
        startActivity(postsIntent)
    }

    fun initViews() {
        post_comments_recycler = findViewById(R.id.post_comments_recycler)
        comments_loading = findViewById(R.id.comments_loading)
        error_lyt = findViewById(R.id.error_lyt)
    }
}