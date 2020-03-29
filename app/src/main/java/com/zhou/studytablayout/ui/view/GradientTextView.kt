package com.zhou.studytablayout.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.zhou.studytablayout.common.GreenTextView

/**
 * 提供颜色渐变的TextView
 */
class GradientTextView : GreenTextView {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context) : super(context)

    private var mLinearGradient: LinearGradient? = null
    private var mGradientMatrix: Matrix? = null
    private lateinit var mPaint: Paint
    private var mViewWidth = 0f
    private var mTranslate = 0f
    private val mAnimating = true

    private val fontColor = Color.BLACK
    private val shaderColor = Color.BLUE
    private val shaderColor2 = Color.YELLOW

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mViewWidth == 0f) {
            mViewWidth = measuredWidth.toFloat()
            if (mViewWidth > 0) {
                mPaint = paint
                mLinearGradient = LinearGradient(
                    -mViewWidth,
                    0f,
                    0f,
                    0f,
                    intArrayOf(fontColor, shaderColor, shaderColor, fontColor),
                    floatArrayOf(0f, 0f, 0.9f, 1f),
                    Shader.TileMode.CLAMP
                )
                mPaint.shader = mLinearGradient
                mGradientMatrix = Matrix()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mAnimating && mGradientMatrix != null) {
            mGradientMatrix!!.setTranslate(mTranslate, 0f)
            mLinearGradient!!.setLocalMatrix(mGradientMatrix)
        }
    }

    /**
     * 由外部参数控制shader的位置
     * @param positionOffset 从0 到 1 的小数
     */
    override fun setMatrixTranslate(positionOffset: Float, isSelected: Boolean) {
        mTranslate = if (isSelected)
            (-mViewWidth + 2f * mViewWidth * positionOffset)
        else
            -mViewWidth
        postInvalidate()
    }

    override fun removeShader() {
        super.removeShader()
        mTranslate = -mViewWidth
        postInvalidate()
    }

}