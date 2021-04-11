package com.udacity

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.res.TypedArrayUtils.getString
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
    private var buttonText: String? = null

    private var valueAnimator = ValueAnimator()
    // ButtonState.Complete is set as the initial value
    var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { property, oldValue, newValue ->
        Log.i("LoadingButton","Checking if button state changed...")

        when(newValue) {
            // advice from mentor
            ButtonState.Loading -> {
                Log.i("LoadingButton","Button state changed from complete to loading")
                // Show loading text (access string resource)
                buttonText = resources.getString(R.string.button_loading)

                //start the loading animation
                // Change this
                valueAnimator = ValueAnimator.ofFloat(0f, widthSize).apply {
                    duration = 3000
                    addUpdateListener {valueAnimator ->
                        loadingStatus = valueAnimator.animatedValue as Float
                        invalidate() // redraws the button
                    }
                }
                valueAnimator.repeatMode = ValueAnimator.REVERSE
                valueAnimator.repeatCount = ValueAnimator.INFINITE
                valueAnimator.start()

            }
            //similarly handle the other 2 states as well
            ButtonState.Completed -> {
                // Show download text (access string resource)
                buttonText = resources.getString(R.string.button_name)
                Log.i("LoadingButton","Button state changed from loading to complete")
                valueAnimator.cancel()
                loadingStatus = 0.0f
                invalidate() // redraws the button
            }
            else -> {

            }
        }
    }

    private var loadingStatus = 0.0f

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
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        widthSize = width.toFloat()
        heightSize = height.toFloat()
        buttonText = resources.getString(R.string.button_name)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set the button color based on the loading status?
        paint.color = buttonBaseColor

        // Draw the base button
        canvas.drawRect((width/2-widthSize/2).toFloat(), (height/2 - heightSize/2).toFloat(), (width/2+widthSize/2).toFloat(), (height/2+heightSize/2).toFloat(), paint)

        // raw the loading rounded filler
        paint.color = buttonLoadingColor
        canvas.drawRect((width/2-widthSize/2).toFloat(), (height/2 - heightSize/2).toFloat(), (width/2-widthSize/2+loadingStatus).toFloat(), (height/2+heightSize/2).toFloat(), paint)

        // Draw the loading labels
        paint.color = Color.WHITE

        buttonText?.let {
            var bounds: Rect = Rect()
            paint.getTextBounds(buttonText, 0, buttonText!!.length, bounds)
            var textHeight = bounds.height()
            canvas.drawText(buttonText!!, width/2.toFloat(), (height/2+textHeight/2).toFloat(), paint)

        }
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