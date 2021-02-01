package mostafa.projects.coroutinessamples.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mostafa.projects.coroutinessamples.R
import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Post

class PostsAdapter(var posts: ArrayList<PostTable>, var postsHelper: PostsHelper) :
    RecyclerView.Adapter<PostsAdapter.PostsHolder>() {


    var _postsHelper: PostsHelper

    init {
        _postsHelper = postsHelper
    }


    class PostsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var post_title_txt = itemView.findViewById<TextView>(R.id.post_title_txt)
        var post_body_txt = itemView.findViewById<TextView>(R.id.post_body_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, null)
        return PostsHolder(
            inflate
        )
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        var post = posts.get(position)
        holder.post_title_txt.setText(post.title)
        holder.post_body_txt.setText(post.body)

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                _postsHelper.GetComments(post_id = post.id!!)
            }

        })

    }

    interface PostsHelper {
        fun GetComments(post_id: Int)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}