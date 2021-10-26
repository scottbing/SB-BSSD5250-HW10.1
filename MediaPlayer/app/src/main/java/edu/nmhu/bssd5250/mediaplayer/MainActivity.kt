package edu.nmhu.bssd5250.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private val PLAYTIME:String = "edu.nmhu.bssd5250.mediaplayer.playtime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            id = View.generateViewId()
        }
        setContentView(ll)

        supportFragmentManager.commit{
            add(ll.id, AudioFragment.newInstance(R.raw.step))
        }
    }
}