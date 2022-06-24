package air.com.hypah.io.slithe.ground

import air.com.hypah.io.slithe.R
import air.com.hypah.io.slithe.game.StartGame
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*

class Filter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        val txtEr: TextView = findViewById(R.id.inviteTxt)
        runBlocking {

            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                getAsync(applicationContext)
            }
            job.join()
            val jsoup: String? = Hawk.get(CNST.asyncResult, "")
            Log.d("cora", "cora $jsoup")

            txtEr.text = jsoup

            if (jsoup == "z0aP") {
                Intent(applicationContext, StartGame::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, WebV::class.java).also { startActivity(it) }
            }
            finish()
        }
    }
    private suspend fun getAsync(context: Context) {
        val asyncKey = JSoup(context)
        val asyncResult = asyncKey.getDocSecretKey()
        Hawk.put(CNST.asyncResult, asyncResult)
    }
}