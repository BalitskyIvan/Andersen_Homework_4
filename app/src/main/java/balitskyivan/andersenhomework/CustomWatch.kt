package balitskyivan.andersenhomework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import java.lang.Integer.min
import java.security.AccessControlContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class CustomWatch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val HOURS_COUNT = 12
    private val DRAW_DELAY = 30
    private val ANGLE_DELTA = 30f
    private val WATCH_BORDER = 0.8f
    private var hoursArrowAngle: Float = 0f
    private var minutesArrowAngle: Float = 0f
    private var secondsArrowAngle: Float = 0f
    private var lifeCycleThread: Thread? = null
    private var isThreadStop = false
    var radius = 0f
    var clockFaceColor = 0
    var hoursArrowColor = 0
    var minutesArrowColor = 0
    var secondsArrowColor = 0


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    init {
        runLyfeCycleThread()
        context.withStyledAttributes(attrs, R.styleable.CustomWatch) {
            clockFaceColor = getColor(R.styleable.CustomWatch_clockFaceColor, Color.BLACK)
            hoursArrowColor = getColor(R.styleable.CustomWatch_hoursArrowColor, Color.BLACK)
            minutesArrowColor = getColor(R.styleable.CustomWatch_minutesArrowColor, Color.BLACK)
            secondsArrowColor = getColor(R.styleable.CustomWatch_secondsArrowColor, Color.GRAY)
            radius = getFloat(
                R.styleable.CustomWatch_clockRadius,
                (min(width, height) / 2f) * WATCH_BORDER
            )
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = (min(w, h) / 2f) * WATCH_BORDER
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawClockFace(canvas, radius / 14f, clockFaceColor)
        drawArrow(canvas, radius / 14f, radius / 1.5f, hoursArrowAngle, hoursArrowColor)
        drawArrow(canvas, radius / 28f, radius / 1.2f, minutesArrowAngle, minutesArrowColor)
        drawArrow(canvas, radius / 60f, radius / 1f, secondsArrowAngle, secondsArrowColor)
    }

    private fun drawArrow(
        canvas: Canvas?,
        arrowBorder: Float,
        arrowLength: Float,
        angle: Float,
        color: Int
    ) {
        canvas?.save()
        canvas?.rotate(angle, width / 2f, height / 2f)
        paint.strokeWidth = arrowBorder
        paint.color = color
        canvas?.drawRect(
            width / 2f - arrowLength / 4f,
            height / 2f,
            width / 2f + arrowLength - arrowLength / 4f,
            height / 2f,
            paint
        )
        canvas?.restore()
    }

    private fun drawClockFace(canvas: Canvas?, circleBorder: Float, color: Int) {
        paint.strokeWidth = circleBorder
        paint.color = color
        var currentAngle = 0f
        for (i in 0..HOURS_COUNT) {
            canvas?.save()
            canvas?.rotate(currentAngle, width / 2f, height / 2f)
            canvas?.drawRect(
                width / 2f - radius,
                height / 2f,
                width / 2f - radius / 1.12f,
                height / 2f,
                paint
            )
            canvas?.restore()
            currentAngle += ANGLE_DELTA
        }
        canvas?.drawCircle(width / 2f, height / 2f, radius, paint)
    }

    override fun onDetachedFromWindow() {
        isThreadStop = true
        super.onDetachedFromWindow()
        lifeCycleThread?.interrupt()
    }

    private fun runLyfeCycleThread() {
        var calendar = Calendar.getInstance()
        var currentMilliSecond: Int? = calendar.get(Calendar.MILLISECOND)

        lifeCycleThread = thread {
            while (!isThreadStop) {
                calendar = Calendar.getInstance()

                if (currentMilliSecond!! + DRAW_DELAY < calendar.get(Calendar.MILLISECOND) ||
                    calendar.get(Calendar.MILLISECOND) + DRAW_DELAY < currentMilliSecond!!
                ) {
                    currentMilliSecond = calendar.get(Calendar.MILLISECOND)
                    hoursArrowAngle = computeHourAngle(calendar)
                    minutesArrowAngle = computeMinutesAngle(calendar)
                    secondsArrowAngle = computeSecondsAngle(calendar)
                    invalidate()
                }
                try {
                    Thread.sleep(5)
                } catch (e : Exception) {
                    break
                }
            }
        }
    }

    private fun computeHourAngle(calendar: Calendar): Float {
        var currentHour = calendar.get(Calendar.HOUR)
        if (currentHour > HOURS_COUNT)
            currentHour -= HOURS_COUNT
        return (currentHour * ANGLE_DELTA) + ((calendar.get(Calendar.MINUTE) * 0.6f +
                (calendar.get(Calendar.SECOND) * 0.01f))) - 90f
    }

    private fun computeMinutesAngle(calendar: Calendar): Float {
        return (calendar.get(Calendar.MINUTE) * 6f + (calendar.get(Calendar.SECOND) * 0.1f)) - 90f
    }

    private fun computeSecondsAngle(calendar: Calendar): Float {
        return ((calendar.get(Calendar.SECOND) * 6f) +
                (calendar.get(Calendar.MILLISECOND) * 0.006f)) - 90f
    }
}