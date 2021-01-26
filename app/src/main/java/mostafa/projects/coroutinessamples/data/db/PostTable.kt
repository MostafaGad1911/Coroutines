package mostafa.projects.coroutinessamples.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Posts")
class PostTable {
    @ColumnInfo(name = "user_id")
    var user_id:Int? = null
    @PrimaryKey
    var id:Int? = null
    @ColumnInfo(name = "title")
    var title:String? = null
    @ColumnInfo(name = "body")
    var body:String? = null

}