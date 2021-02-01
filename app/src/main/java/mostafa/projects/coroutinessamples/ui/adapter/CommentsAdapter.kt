package mostafa.projects.coroutinessamples.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mostafa.projects.coroutinessamples.R
import mostafa.projects.coroutinessamples.data.db.PostTable
import mostafa.projects.coroutinessamples.data.model.Comment

class CommentsAdapter(var comments: ArrayList<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.PostsHolder>() {



    class PostsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var post_commenter_name_txt = itemView.findViewById<TextView>(R.id.post_commenter_name_txt)
        var post_commenter_email_txt = itemView.findViewById<TextView>(R.id.post_commenter_email_txt)
        var post_comment_body_txt = itemView.findViewById<TextView>(R.id.post_comment_body_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.comment_item, null)
        return PostsHolder(
            inflate
        )
    }

    override fun onBindViewHolder(holder: PostsHolder, position: Int) {
        var comment = comments.get(position)
        holder.post_commenter_name_txt.setText(comment.name)
        holder.post_commenter_email_txt.setText(comment.email?.toLowerCase())
        holder.post_comment_body_txt.setText(comment.body)


    }


    override fun getItemCount(): Int {
        return comments.size
    }
}