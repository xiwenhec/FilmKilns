package com.lmy.samplenative.ui

import android.widget.SeekBar
import com.lmy.hwvcnative.processor.AlVideoV2Processor
import com.lmy.hwvcnative.processor.MediaType
import com.lmy.samplenative.BaseActivity
import com.lmy.samplenative.R
import kotlinx.android.synthetic.main.activity_video_v2.*
import java.text.SimpleDateFormat
import java.util.*

class AlVideoV2Activity : BaseActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_video_v2
    private val fmt = SimpleDateFormat("mm:ss")
    private val processor: AlVideoV2Processor? = AlVideoV2Processor()
    private var playing: Boolean = true
    private var duration: Long = -1
    private var mCurTrackID: Int? = -1
    override fun initView() {
        mCurTrackID = processor?.addTrack(MediaType.TYPE_AUDIO, "/sdcard/the-world-today-short.m4a", 0)
        processor?.setOnPlayProgressListener { timeInUS, duration ->
            this.duration = duration
            runOnUiThread {
                seekBar.progress = (timeInUS * 100 / duration).toInt()
                timeView.text =
                    "${fmt.format(Date(timeInUS / 1000))}/${fmt.format(Date(duration / 1000))}"
            }
        }
        processor?.start()
        setupView()
    }

    private fun setupView() {
        playBtn.setOnClickListener {
            if (playing) {
                playBtn.setImageResource(android.R.drawable.ic_media_play)
                processor?.pause()
            } else {
                playBtn.setImageResource(android.R.drawable.ic_media_pause)
                processor?.start()
            }
            playing = !playing
        }
        addBtn.setOnClickListener {
            mCurTrackID = processor?.addTrack(MediaType.TYPE_AUDIO, "/sdcard/hw_test.m4a", 10000000)
        }
        delBtn.setOnClickListener {
            if (null != mCurTrackID) {
                processor?.removeTrack(mCurTrackID!!)
            }
            mCurTrackID = -1
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    processor?.seek(duration * progress.toLong() / 100)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                processor?.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                processor?.start()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        processor?.pause()
        processor?.release()
    }
}