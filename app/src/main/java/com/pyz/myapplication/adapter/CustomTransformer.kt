package com.pyz.myapplication.adapter

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.pyz.myapplication.App

/**
 * 自定义ViewPager切换
 */
class CustomTransformer :ViewPager2.PageTransformer {

    companion object{
        const val DEFAULT_MAX_ROTATION: Float = 60f
        const val DEF_MIN_SCALE: Float = 0.86f
        /**
         * 最大旋转角度
         */
        private val mMaxRotation = DEFAULT_MAX_ROTATION
        /**
         * 最小缩放
         */
        private val mMinScale = DEF_MIN_SCALE

    }
    override fun transformPage(page: View, position: Float) {
        page.setPivotY(page.getHeight() / 2f);
        val distance = getCameraDistance();
        page.cameraDistance = distance;//设置 View 的镜头距离，可以防止旋转大角度时出现图像失真或不显示。
        if (position < -1) { // [-Infinity,-1)
            page.setRotationY(-mMaxRotation);
            page.pivotX = page.getWidth().toFloat();
        } else if (position <= 1) { // [-1,1]
            page.rotationY = position * mMaxRotation;
            if (position < 0) {//[0,-1]
                page.pivotX = page.getWidth().toFloat();
                val scale = DEF_MIN_SCALE + 4f * (1f - DEF_MIN_SCALE) * (position + 0.5f) * (position + 0.5f);
                page.scaleX = scale;
                page.scaleY = scale;
            } else {//[1,0]
                page.pivotX = 0F;
                val scale = DEF_MIN_SCALE + 4f * (1f - DEF_MIN_SCALE) * (position - 0.5f) * (position - 0.5f);
                page.scaleX = scale;
                page.scaleY = scale;
            }
        } else { // (1,+Infinity]
            page.rotationY = mMaxRotation;
            page.pivotX = 0F;
        }
    }

    private fun getCameraDistance(): Float {
        val displayMetrics = App.getApplication().resources.displayMetrics;
        val density = displayMetrics.density
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        return 1.5f*Math.max(widthPixels, heightPixels)*density;
    }
}