package dev.jahidhasanco.recyclerviewswipeext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeCallback constructor(
    private val listener: SwipeListener,
    private val swipeView: SwipeView
) :
    ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val movementFlags: Int = returnMovementFlags()
        return if (movementFlags != -1) movementFlags else super.getMovementFlags(
            recyclerView,
            viewHolder
        )
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.RIGHT) {
            listener.onSwipedRight(viewHolder.adapterPosition)
        } else {
            listener.onSwipedLeft(viewHolder.adapterPosition)
        }
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(
            c, recyclerView, viewHolder, dX, dY,
            actionState, isCurrentlyActive
        )
        val v = viewHolder.itemView
        val context = v.context
        var toDraw: ChildToDraw? = null
        if (dX > 0) {
            toDraw = ChildToDraw(dX.toInt(), v, context, ChildToDraw.LEFT, swipeView).invoke()
        } else if (dX < 0) {
            toDraw = ChildToDraw(dX.toInt(), v, context, ChildToDraw.RIGHT, swipeView).invoke()
        }
        toDraw?.let { draw(c, context, it) }
    }

    private fun draw(c: Canvas, context: Context, toDraw: ChildToDraw) {
        val bg: ColorDrawable? = toDraw.bg
        val icon: Drawable? = toDraw.icon
        val paint: Paint? = toDraw.paintText
        bg?.draw(c)
        icon?.draw(c)
        c.drawText(
            toDraw.text?:"", icon?.bounds?.centerX()?.toFloat()?:0f,
            (icon?.bounds?.centerY()?.toFloat() ?: 0f) + (icon?.bounds?.height()?:0) +
                    convertDpToPixel(2f, context), (paint?:Paint(0))
        )
    }

    private fun returnMovementFlags(): Int {
        if (((swipeView.leftIcon == -1) && (
                    swipeView.leftBg == R.color.white) &&
                    swipeView.leftText.isEmpty() && (
                    swipeView.rightIcon == -1) && (
                    swipeView.rightBg == R.color.white) &&
                    swipeView.rightText.isEmpty())
        ) return makeMovementFlags(0, 0)
        if (((swipeView.leftIcon == -1) && (
                    swipeView.leftBg == R.color.white) &&
                    swipeView.leftText.isEmpty())
        ) return makeMovementFlags(0, ItemTouchHelper.LEFT)
        return if (((swipeView.leftIcon == -1) && (
                    swipeView.rightBg == R.color.white) &&
                    swipeView.rightText.isEmpty())
        ) makeMovementFlags(0, ItemTouchHelper.RIGHT) else -1
    }


}