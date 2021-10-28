package edu.nmhu.bssd5250.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

private const val AUDIO_RES = "audio_file" //key for bundle

class AudioFragment : Fragment() {

    private val STEP_TAG:String = "edu.nmhu.bssd5250.mediaplayer.step_tag"  // tag to look up frag
    private val EAGLE_TAG:String = "edu.nmhu.bssd5250.mediaplayer.eagle_tag"  // tag to look up frag
    private val LAUNCH_TAG:String = "edu.nmhu.bssd5250.mediaplayer.launch_tag"  // tag to look up frag

    private lateinit var viewModel:AudioViewModel

    private var mediaPlayer: MediaPlayer? = null //will hold mediaplayer
    private var audioRes:Int? = null //resource to play

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AudioViewModel::class.java)
        arguments?.let {
            audioRes = it.getInt(AUDIO_RES) //load argument from companion
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(this.mediaPlayer == null){
            this.mediaPlayer = MediaPlayer.create(context, audioRes!!) //make mediaplayer
        }
        val v:View = inflater.inflate(R.layout.fragment_audio, container, false)
        //look up each button and give it click listener
        v.findViewById<Button>(R.id.play_button).apply {
            setOnClickListener {
                playMedia()
            }
        }
        v.findViewById<Button>(R.id.stop_button).apply {
            setOnClickListener {
                stopMedia()
            }
        }
        v.findViewById<TextView>(R.id.sound_label).apply {
            text = "NASA Sounds"
        }
        return v
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(viewModel.getCurrentTime() > 0){
            playMedia(viewModel.getCurrentTime())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setCurrentTime((this.mediaPlayer?.currentPosition!!))
        stopMedia()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun playMedia(time:Int = 0) {
        mediaPlayer?.seekTo(time)
        mediaPlayer?.start()
    }

    private fun stopMedia(time:Int = 0) {
        mediaPlayer?.stop()
        mediaPlayer?.prepare()
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