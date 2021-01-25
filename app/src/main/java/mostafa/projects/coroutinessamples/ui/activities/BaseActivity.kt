package mostafa.projects.coroutinessamples.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mostafa.projects.coroutinessamples.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showToast(msg:String){
        Toast.makeText(this , msg , Toast.LENGTH_LONG).show()
    }
}