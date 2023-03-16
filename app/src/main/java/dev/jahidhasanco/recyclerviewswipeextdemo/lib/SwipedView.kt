package dev.jahidhasanco.recyclerviewswipeextdemo.lib

class SwipedView constructor(icons: IntArray, backgrounds: IntArray, texts: Array<String>){

    private var mIcons: IntArray
    private var mBackgrounds: IntArray
    private var mTexts: Array<String>
    var textColor = 0
    var textSize = 0

    init {
        mIcons = icons
        mBackgrounds = backgrounds
        mTexts = texts
    }

    val leftIcon: Int
        get() = mIcons[0]
    val rightIcon: Int
        get() = mIcons[1]
    val leftBg: Int
        get() = mBackgrounds[0]
    val rightBg: Int
        get() = mBackgrounds[1]
    val leftText: String
        get() = mTexts[0]
    val rightText: String
        get() = mTexts[1]

    fun setIcons(icons: IntArray) {
        mIcons = icons
    }

    fun setBackgrounds(backgrounds: IntArray) {
        mBackgrounds = backgrounds
    }

    fun setTexts(texts: Array<String>) {
        mTexts = texts
    }

    val isFull: Boolean
        get() = mBackgrounds.size == 2 && mIcons.size == 2 && mTexts.size == 2
}