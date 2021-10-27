package edu.nmhu.bssd5250.mediaplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private val STEP_TAG:String = "edu.nmhu.bssd5250.mediaplayer.step_tag"  // tag to look up frag
    private val EAGLE_TAG:String = "edu.nmhu.bssd5250.mediaplayer.eagle_tag"  // tag to look up frag
    private val LAUNCH_TAG:String = "edu.nmhu.bssd5250.mediaplayer.launch_tag"  // tag to look up frag
    private val LLID:Int = 100 // constant id for linear layout
    private val STID:Int = 200 // constant id for linear layout
    private val EGID:Int = 300 // constant id for linear layout
    private val LNID:Int = 400 // constant id for linear layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            id = STID
        }

        val egll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            id = EGID
        }

        val lnll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            id = LNID
        }

        val ll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            id = LLID
            addView(stll)
            addView(egll)
            addView(lnll)
        }
        setContentView(ll)

        if (savedInstanceState == null) {
            // create fragment for collection edit buttons
            supportFragmentManager.commit {
                replace(stll.id, StepFragment.newInstance(R.raw.step), STEP_TAG)
            }
            supportFragmentManager.commit {
                replace(egll.id, StepFragment.newInstance(R.raw.eagle), STEP_TAG)
            }
            supportFragmentManager.commit {
                replace(lnll.id, StepFragment.newInstance(R.raw.launch), STEP_TAG)
            }
        }else{
            val stepFragment = supportFragmentManager.findFragmentByTag(STEP_TAG) as StepFragment
            supportFragmentManager.commit {
                replace(stll.id, stepFragment, STEP_TAG)
            }
            val eagleFragment = supportFragmentManager.findFragmentByTag(EAGLE_TAG) as StepFragment
            supportFragmentManager.commit {
                replace(egll.id, eagleFragment, EAGLE_TAG)
            }
            val launchFragment = supportFragmentManager.findFragmentByTag(LAUNCH_TAG) as StepFragment
            supportFragmentManager.commit {
                replace(lnll.id, launchFragment, LAUNCH_TAG)
            }
        }

        supportFragmentManager.commit{
            add(stll.id, StepFragment.newInstance(R.raw.step))
            add(egll.id, StepFragment.newInstance(R.raw.eagle))
            add(lnll.id, StepFragment.newInstance(R.raw.launch))
        }
    }
}