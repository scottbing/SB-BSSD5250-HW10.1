package edu.nmhu.bssd5250.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

private const val AUDIO_RES = "audio_file" //key for bundle

class LaunchFragment : Fragment() {

    private lateinit var viewModel:AudioViewModel

    private var mediaPlayer: MediaPlayer? = null //will hold meidaplayer
    private var audioRes:Int? = null //resource to play

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AudioViewModel::class.java)
        arguments?.let {
            audioRes = it.getInt(AUDIO_RES) //load arguament from companion
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(this.mediaPlayer == null){
            this.mediaPlayer = MediaPlayer.create(context, audioRes!!) //make mediaplayer
        }
        val v:View = inflater.inflate(R.layout.fragment_launch, container, false)
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
            LaunchFragment().apply {
                arguments = Bundle().apply {
                    putInt(AUDIO_RES, audio)
                }
            }
    }
}