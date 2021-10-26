package edu.nmhu.bssd5250.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AudioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val AUDIO_RES = "audio_file" //key for bundle

class AudioFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null //will hold meidaplayer
    private var audioRes:Int? = null //resource to play

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            audioRes = it.getInt(AUDIO_RES) //load arguament from companion
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mediaPlayer = MediaPlayer.create(context, audioRes!!) //make mediaplayer

        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_audio, container, false)
        //look up each button and give it click listener
        v.findViewById<Button>(R.id.play_button).apply {
            setOnClickListener {
                mediaPlayer?.start()
            }
        }
        v.findViewById<Button>(R.id.stop_button).apply {
            setOnClickListener {
                mediaPlayer?.stop()
                mediaPlayer?.prepare()
            }
        }
        return v
    }

    companion object {

        @JvmStatic
        fun newInstance(audio:Int) =
            AudioFragment().apply {
                arguments = Bundle().apply {
                    putInt(AUDIO_RES, audio)
                }
            }
    }
}