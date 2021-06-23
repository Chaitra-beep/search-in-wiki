package com.example.searchinwiki.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import com.example.searchinwiki.R

/**
 * Created by Chaitra on 22,June,2021
 */
class RoundedCornerImageView : androidx.appcompat.widget.AppCompatImageView {
    // Fields
    private var mCornerRadius = 0f

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes) {
        val array = context.obtainStyledAttributes(attributes, R.styleable.RoundedCornerImageView)
        if (array != null) {
            mCornerRadius = array.getDimension(R.styleable.RoundedCornerImageView_corner_radius, 5f)
            array.recycle()
        }
    }

    // Overridden methods
    override fun onDraw(canvas: Canvas) {
        val drawable = drawable
        if (drawable is BitmapDrawable && mCornerRadius > 0) {
            val paint = drawable.paint
            val color = -0x1000000
            val bitmapBounds = drawable.getBounds()
            val rectF = RectF(bitmapBounds)
            // Create an off-screen bitmap to the PorterDuff alpha blending to work right
            val saveCount = canvas.saveLayer(
                rectF, null,
                Canvas.ALL_SAVE_FLAG
            )
            imageMatrix.mapRect(rectF)
            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, paint)
            val oldMode = paint.xfermode
            // This is the paint already associated with the BitmapDrawable that super draws
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            super.onDraw(canvas)
            paint.xfermode = oldMode
            canvas.restoreToCount(saveCount)
        } else {
            super.onDraw(canvas)
        }
    }

    /**
     * Sets the corner radius in pixels.
     */
    fun setCornerRadius(cornerRadius: Float) {
        mCornerRadius = cornerRadius
    }
}