package dev.jahidhasanco.recyclerviewswipeext

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSwipeExt(
    context: Context,
    attrs: AttributeSet?,
    private val recyclerView: RecyclerView
) : SwipeListener {

    private var mListener: SwipeListener? = null
    private lateinit var mSwipedView: SwipeView

    init {
        createSwipedView(context, attrs)
        recyclerView.setWillNotDraw(false)
    }

    fun setRightBg(bg: Int): RecyclerViewSwipeExt {
        mSwipedView.rightBg = bg
        return this
    }

    fun setLeftBg(bg: Int): RecyclerViewSwipeExt {
        mSwipedView.leftBg = bg
        return this
    }

    fun setRightText(text: String): RecyclerViewSwipeExt {
        mSwipedView.rightText = text
        return this
    }

    fun setLeftText(text: String): RecyclerViewSwipeExt {
        mSwipedView.leftText = text
        return this
    }

    fun setRightImage(imgRef: Int): RecyclerViewSwipeExt {
        mSwipedView.rightIcon = imgRef
        return this
    }

    fun setLeftImage(imgRef: Int): RecyclerViewSwipeExt {
        mSwipedView.leftIcon = imgRef
        return this
    }

    fun setTextColor(color: Int): RecyclerViewSwipeExt {
        mSwipedView.textColor = color
        return this
    }

    fun setTextSize(size: Int): RecyclerViewSwipeExt {
        mSwipedView.textSize = size
        return this
    }

    fun setListener(listener: SwipeListener): RecyclerViewSwipeExt {
        mListener = listener
        return this
    }

    override fun onSwipedLeft(position: Int) {
        mListener?.onSwipedLeft(position)
    }

    override fun onSwipedRight(position: Int) {
        mListener?.onSwipedRight(position)
    }

    private fun createSwipedView(context: Context, attrs: AttributeSet?) {
        //obtain attributes
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RecyclerViewSwipeExt,
            0,
            0
        )
        mSwipedView = SwipeView(IntArray(0), IntArray(0), emptyArray())

        mSwipedView.textColor = typedArray.getColor(
            R.styleable.RecyclerViewSwipeExt_textColor, Color.BLACK
        )
        mSwipedView.textSize = typedArray
            .getDimension(R.styleable.RecyclerViewSwipeExt_textSize, 15f).toInt()
    }

    fun createSwipeAble() {
        val mSwipeCallBack = SwipeCallback(
            this, mSwipedView
        )
        ItemTouchHelper(mSwipeCallBack).attachToRecyclerView(recyclerView)
    }

}

fun RecyclerView.makeLeftRightSwipeAble(
    context: Context,
    attrs: AttributeSet? = null
): RecyclerViewSwipeExt {
    return RecyclerViewSwipeExt(context, attrs, this)
}
