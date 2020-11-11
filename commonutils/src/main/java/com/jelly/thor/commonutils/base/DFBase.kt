package com.jelly.thor.commonutils.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment

/**
 * 类描述：DialogFragment基类<br></br>
 * 创建人：吴冬冬<br></br>
 * 创建时间：2020/4/24 14:23 <br></br>
 */
abstract class DFBase : AppCompatDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val window = dialog?.window
        window?.let {
            setBackgroundDrawable(it)
            setOutBackgroundDrawableTransparent(it)
        }
        //外部点击事件
        setOutClick()
        return inflater.inflate(createView(), container, false)
    }

    protected open fun setOutClick() {
        //外部点击事件
        //isCancelable = true
        //dialog?.setCanceledOnTouchOutside(false)
    }

    protected open fun setOutBackgroundDrawableTransparent(window: Window) {
        //window.setDimAmount(0F)
    }

    protected open fun setBackgroundDrawable(window: Window) {
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    /**
     * 设置布局
     *
     * @return R.layout.xxx
     */
    @LayoutRes
    abstract fun createView(): Int

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    /**
     * 在onViewCreated中初始化View
     */
    protected abstract fun initView(view: View?)

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.let {
            view?.viewTreeObserver?.addOnPreDrawListener(object :
                ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    view?.viewTreeObserver?.removeOnPreDrawListener(this)
                    setDialogParams(it)
                    return false
                }
            })
        }
    }

    /**
     * 设置dialogFragment参数
     */
    protected open fun setDialogParams(window: Window) {
        //val point = Point()
        //window.windowManager.defaultDisplay.getSize(point)

        //设置宽度百分比
        //#第一种
        //window.setLayout( ((point.x * 0.5).toInt()), ViewGroup.LayoutParams.WRAP_CONTENT);

        //val layoutParams = window.attributes
        //#第二种
        //layoutParams.width = point.x
        //layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        //设置dialog位置
        //layoutParams.gravity = Gravity.BOTTOM
        //设置dialog动画
        //layoutParams.windowAnimations = R.style.xxxx
        //window.attributes = layoutParams
    }
}