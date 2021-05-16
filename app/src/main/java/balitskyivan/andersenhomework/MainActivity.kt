package balitskyivan.andersenhomework

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private var watch: CustomWatch? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        watch = findViewById(R.id.customWatch)
        setClockColorListener()
        setRadiusClockListener()
    }

    /*
    Здесь лютый говнокод, потому что я не понял как изменить переданный объект в функции,
    если он всегда immutable в kotlin :/
    Пришлолсь писать четыре отдельных функции...
     */

    private fun setRadiusClockListener() {
        findViewById<SeekBar>(R.id.clockSize).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                watch?.radius = p1.toFloat()
                watch?.invalidate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    private fun setClockColorListener() {
        setListenerFace(R.id.clockFaceRBar, "R")
        setListenerFace(R.id.clockFaceGBar, "G")
        setListenerFace(R.id.clockFaceBBar, "B")
        setListenerHorsArrow(R.id.hoursArrowRBar, "R")
        setListenerHorsArrow(R.id.hoursArrowGBar, "G")
        setListenerHorsArrow(R.id.hoursArrowBBar, "B")
        setListenerMinutesArrow(R.id.minutesArrowRBar, "R")
        setListenerMinutesArrow(R.id.minutesArrowGBar, "G")
        setListenerMinutesArrow(R.id.minutesArrowBBar, "B")
        setListenerSecondsArrow(R.id.secondsArrowRBar, "R")
        setListenerSecondsArrow(R.id.secondsArrowGBar, "G")
        setListenerSecondsArrow(R.id.secondsArrowBBar, "B")
    }

    private fun setListenerFace(id: Int, color: String) {
        findViewById<SeekBar>(id).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val currentColor: Color = Color.valueOf(watch!!.clockFaceColor)
                when (color) {
                    "R" -> watch?.clockFaceColor =
                        Color.valueOf(p1.toFloat() + 1, currentColor.green(), currentColor.blue())
                            .toArgb()
                    "G" -> watch?.clockFaceColor =
                        Color.valueOf(currentColor.red(), p1.toFloat() + 1, currentColor.blue())
                            .toArgb()
                    "B" -> watch?.clockFaceColor =
                        Color.valueOf(currentColor.red(), currentColor.green(), p1.toFloat() + 1)
                            .toArgb()
                }
                watch?.invalidate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    private fun setListenerHorsArrow(id: Int, color: String) {
        findViewById<SeekBar>(id).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val currentColor: Color = Color.valueOf(watch!!.hoursArrowColor)
                when (color) {
                    "R" -> watch?.hoursArrowColor =
                        Color.valueOf(p1.toFloat() + 1, currentColor.green(), currentColor.blue())
                            .toArgb()
                    "G" -> watch?.hoursArrowColor =
                        Color.valueOf(currentColor.red(), p1.toFloat() + 1, currentColor.blue())
                            .toArgb()
                    "B" -> watch?.hoursArrowColor =
                        Color.valueOf(currentColor.red(), currentColor.green(), p1.toFloat() + 1)
                            .toArgb()
                }
                watch?.invalidate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    private fun setListenerMinutesArrow(id: Int, color: String) {
        findViewById<SeekBar>(id).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val currentColor: Color = Color.valueOf(watch!!.minutesArrowColor)
                when (color) {
                    "R" -> watch?.minutesArrowColor =
                        Color.valueOf(p1.toFloat() + 1, currentColor.green(), currentColor.blue())
                            .toArgb()
                    "G" -> watch?.minutesArrowColor =
                        Color.valueOf(currentColor.red(), p1.toFloat() + 1, currentColor.blue())
                            .toArgb()
                    "B" -> watch?.minutesArrowColor =
                        Color.valueOf(currentColor.red(), currentColor.green(), p1.toFloat() + 1)
                            .toArgb()
                }
                watch?.invalidate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    private fun setListenerSecondsArrow(id: Int, color: String) {
        findViewById<SeekBar>(id).setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val currentColor: Color = Color.valueOf(watch!!.secondsArrowColor)
                when (color) {
                    "R" -> watch?.secondsArrowColor =
                        Color.valueOf(p1.toFloat() + 1, currentColor.green(), currentColor.blue())
                            .toArgb()
                    "G" -> watch?.secondsArrowColor =
                        Color.valueOf(currentColor.red(), p1.toFloat() + 1, currentColor.blue())
                            .toArgb()
                    "B" -> watch?.secondsArrowColor =
                        Color.valueOf(currentColor.red(), currentColor.green(), p1.toFloat() + 1)
                            .toArgb()
                }
                watch?.invalidate()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

}