package dev.jahidhasanco.recyclerviewswipeext

import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat

class ChildToDraw
    (
    private val dX: Int,
    private val v: View,
    private val context: Context,
    private val mSide: Int,
    private val swipeView: SwipeView
) {
    var bg: ColorDrawable? = null
    var icon: Drawable? = null
    var paintText: Paint? = null
    var text: String? = null

    operator fun invoke(): ChildToDraw {
        var iconMargin = 0
        var iconTop = 0
        var iconBottom = 0
        try {
            icon = ContextCompat.getDrawable(
                context,
                if (mSide == LEFT) swipeView.leftIcon else swipeView.rightIcon
            )
            iconMargin = (v.height - icon!!.intrinsicHeight) / 2
            iconTop =
                if ((mSide == LEFT && swipeView.leftText.isEmpty()) or (mSide == RIGHT && swipeView.rightText.isEmpty())) {
                    v.top + (v.height - icon!!.intrinsicHeight) / 2
                } else {
                    v.top + (v.height - icon!!.intrinsicHeight - swipeView.textSize) / 2
                }

            iconBottom =
                if ((mSide == LEFT && swipeView.leftText.isEmpty()) or (mSide == RIGHT && swipeView.rightText.isEmpty())) {
                    iconTop + icon!!.intrinsicHeight
                } else {
                    iconTop + icon!!.intrinsicHeight - swipeView.textSize
                }

        } catch (e: Exception) {
            icon = null
            e.printStackTrace()
        }
        val iconLeft: Int
        val iconRight: Int
        when (mSide) {
            LEFT -> {
                text = swipeView.leftText
                if (icon != null) {
                    iconLeft = v.left + iconMargin + (text!!.length /2)
                    iconRight = v.left + iconMargin + icon!!.intrinsicWidth
                    icon!!.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                }
                bg = ColorDrawable(
                    context.resources
                        .getColor(swipeView.leftBg, context.theme)
                )
                bg!!.setBounds(
                    v.left, v.top, v.left +
                            dX, v.bottom
                )
            }
            RIGHT -> {
                text = swipeView.rightText
                if (icon != null) {
                    iconLeft = v.right - iconMargin - icon!!.intrinsicWidth
                    iconRight = v.right - iconMargin + (text!!.length /2)
                    icon!!.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                }
                bg = ColorDrawable(
                    context.resources
                        .getColor(swipeView.rightBg, context.theme)
                )
                bg!!.setBounds(
                    v.right + dX, v.top,
                    v.right, v.bottom
                )
            }
        }
        paintText = Paint()
        paintText!!.color = swipeView.textColor
        paintText!!.textSize = if (swipeView.textSize == 15) convertDpToPixel(
            swipeView.textSize.toFloat(),
            context
        ) else swipeView.textSize.toFloat()

        paintText!!.textAlign = Paint.Align.CENTER
        return this
    }

    companion object {
        const val LEFT = 0
        const val RIGHT = 1
    }
}