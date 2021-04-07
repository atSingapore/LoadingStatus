package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.properties.Delegates

/** In the example the base is a circle and the dial position is animated.
 * This time the base is a square and the loading status is animated
 * (by filling the square with a rounded rectangle */

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0f
    private var heightSize = 0f

    private val valueAnimator = ValueAnimator()
    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->

    }

    // Variables to cache the attributed value
    private var buttonBaseColor = 0
    private var buttonLoadingColor = 0

    // Initialize paint object
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    init {

        // Set button to be clickable
        isClickable = true

        //Style attributes?
        context.withStyledAttributes(attrs, R.styleable.LoadingButton) {
            buttonBaseColor = getColor(R.styleable.LoadingButton_notLoadingColor, 0)
            buttonLoadingColor = getColor(R.styleable.LoadingButton_loadingColor, 0)
        }

        //TODO - Where is color actually set?

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        widthSize = width / 1.2f
        heightSize = width / 8f
    }

    //TODO - Function to compute loading filler size for specific loading status?

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //TODO - Set the button color based on the loading status?

        //TODO - Draw the base button
        canvas.drawRect((width/2-widthSize/2).toFloat(), (height/2 - heightSize/2).toFloat(), (width/2+widthSize/2).toFloat(), (height/2+heightSize/2).toFloat(), paint)

        //TODO - Draw the loading rounded filler

        //TODO - Draw the loading labels
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w.toFloat()
        heightSize = h.toFloat()
        setMeasuredDimension(w, h)
    }

}