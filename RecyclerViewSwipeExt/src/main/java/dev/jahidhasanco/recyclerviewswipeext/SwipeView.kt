package dev.jahidhasanco.recyclerviewswipeext

class SwipeView constructor(icons: IntArray, backgrounds: IntArray, texts: Array<String>) {

    private var mIcons: IntArray
    private var mBackgrounds: IntArray
    private var mTexts: Array<String>
    var textColor = R.color.white
    var textSize = 15
    var leftText: String = ""
    var rightText: String = ""

    var leftIcon: Int = R.drawable.baseline_delete_24
    var rightIcon: Int = R.drawable.baseline_archive_24
    var leftBg: Int = R.color.error
    var rightBg: Int = R.color.success

    init {
        mIcons = icons
        if (mIcons.size == 2) {
            leftIcon = mIcons[0]
            rightIcon = mIcons[1]
        }
        mBackgrounds = backgrounds
        if (backgrounds.size == 2) {
            leftBg = mBackgrounds[0]
            rightBg = mBackgrounds[1]
        }
        mTexts = texts
        if (mTexts.size == 2) {
            leftText = mTexts[0]
            rightText = mTexts[1]
        }
    }

    val isFull: Boolean
        get() = mBackgrounds.size == 2 && mIcons.size == 2 && mTexts.size == 2
}