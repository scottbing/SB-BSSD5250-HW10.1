package edu.nmhu.bssd5250.mediaplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private val STEP_TAG:String = "edu.nmhu.bssd5250.mediaplayer.step_tag"  // tag to look up frag
    private val EAGLE_TAG:String = "edu.nmhu.bssd5250.mediaplayer.eagle_tag"  // tag to look up frag
    private val LAUNCH_TAG:String = "edu.nmhu.bssd5250.mediaplayer.launch_tag"  // tag to look up frag
    private val LLID:Int = 123 // constant id for linear layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            id = LLID
        }
        setContentView(ll)

        if (savedInstanceState == null) {
            // create fragment for collection edit buttons
            supportFragmentManager.commit {
                replace(ll.id, AudioFragment.newInstance(R.raw.step), STEP_TAG)
            }
        }else{
            val stepFragment = supportFragmentManager.findFragmentByTag(STEP_TAG) as AudioFragment
            supportFragmentManager.commit {
                replace(ll.id, stepFragment, STEP_TAG)
            }
        }

        if (savedInstanceState == null) {
            // create fragment for collection edit buttons
            supportFragmentManager.commit {
                replace(ll.id, AudioFragment.newInstance(R.raw.eagle), EAGLE_TAG)
            }
        }else{
            val eagleFragment = supportFragmentManager.findFragmentByTag(EAGLE_TAG) as AudioFragment
            supportFragmentManager.commit {
                replace(ll.id, eagleFragment, EAGLE_TAG)
            }
        }

        if (savedInstanceState == null) {
            // create fragment for collection edit buttons
            supportFragmentManager.commit {
                replace(ll.id, AudioFragment.newInstance(R.raw.launch), LAUNCH_TAG)
            }
        }else{
            val launchFragment = supportFragmentManager.findFragmentByTag(LAUNCH_TAG) as AudioFragment
            supportFragmentManager.commit {
                replace(ll.id, launchFragment, LAUNCH_TAG)
            }
        }

        supportFragmentManager.commit{
            add(ll.id, AudioFragment.newInstance(R.raw.step))
            add(ll.id, AudioFragment.newInstance(R.raw.eagle))
            add(ll.id, AudioFragment.newInstance(R.raw.launch))
        }
    }
}