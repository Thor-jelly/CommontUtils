package com.jelly.thor.commonutils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 类描述：键盘显隐监听<br/>
 * 创建人：吴冬冬<br/>
 * 创建时间：2019/8/12 15:26 <br/>
 */
public class KeyBoardListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private final int KEY_BOARD_HEIGHT = /*IntEKt.dp2px(70)*/1000;

    public interface OnKeyBoardChangeListener {
        void keyBoardShow();

        void keyBoardHide();
    }

    /**
     * activity 根视图
     */
    private final View mDecorView;
    /**
     * 根视图 高度
     */
    private int mDecorViewHeight;

    private OnKeyBoardChangeListener mListener;

    public static KeyBoardListener setListener(AppCompatActivity activity, OnKeyBoardChangeListener listener) {
        KeyBoardListener keyBoardListener = new KeyBoardListener(activity);
        keyBoardListener.setListener(listener);
        return keyBoardListener;
    }

    public void removeListener() {
        mDecorView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        mListener = null;
    }

    private KeyBoardListener(AppCompatActivity activity) {
        //获取根视图
        mDecorView = activity.getWindow().getDecorView();
        //监听视图种全局布局发生改变 或者 视图树中的某个视图的可视状态发生改变
        mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public void setListener(OnKeyBoardChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onGlobalLayout() {
        //获取当前根视图在屏幕上显示的大小
        Rect rect = new Rect();
        mDecorView.getWindowVisibleDisplayFrame(rect);
        int height = rect.height();
        //Log.d("123===", "当前屏幕高度：" + height);
        if (height == 0) {
            mDecorViewHeight = height;
            return;
        }

        //根视图高度没有变化，可以看作 键盘 显示/隐藏 没有改变
        if (mDecorViewHeight == height) {
            return;
        }

        //根视图显示高度 变小 超过 70dp，看作键盘显示
        if (mDecorViewHeight - height > KEY_BOARD_HEIGHT) {
            mDecorViewHeight = height;
            if (mListener != null) {
                mListener.keyBoardShow();
            }
            return;
        }

        //根视图显示高度 变大 超过 70dp， 看作键盘隐藏
        if (height - mDecorViewHeight > KEY_BOARD_HEIGHT) {
            mDecorViewHeight = height;
            if (mListener != null) {
                mListener.keyBoardHide();
            }
            return;
        }
    }
}