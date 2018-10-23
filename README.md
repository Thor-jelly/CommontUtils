# CommonUtils
常用工具类   
[![GitHub release](https://img.shields.io/badge/release-v1.0.3-green.svg)](https://github.com/Thor-jelly/CommontUtils/releases)

# 添加依赖

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


	dependencies {
	        compile 'com.github.Thor-jelly:CommonUtils:v最新版本号'
	}

```

# kotlin扩展

- sharedPreferencesName扩展
- boolean扩展

# 常用工具类-具体方法

```
/**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 展示吐司
     */
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    /**
     * 展示吐司居中
     */
    public static void showToastCenter(Context context, String content) {
        Toast toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 格式化数字
     *
     * @param rule 规则：如"#,###.00"
     */
    public static String getRuleDecimalFormat(String num, String rule) {
        DecimalFormat decimalFormat = new DecimalFormat(rule);
        return decimalFormat.format(num);
    }

    /**
     * 格式化数字,保留2位小数样式
     */
    public static String get2DecimalFormat(String num) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
//        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(num);
    }

    /**
     * 自定义日期转换，秒传入
     * 不具有四舍五入功能
     *
     * @param time
     * @param rule yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getSTime(String time, String rule) {
        if (time != null) {
            long longTime = Long.valueOf(time);
            Date date = new Date(longTime * 1000);
            SimpleDateFormat format = new SimpleDateFormat(rule);
            return format.format(date);
        }
        return "0";
    }

    /**
     * 自定义日期转换，毫秒传入
     *
     * @param time
     * @param rule yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getMSTime(String time, String rule) {
        if (time != null) {
            long longTime = Long.valueOf(time);
            Date date = new Date(longTime);
            SimpleDateFormat format = new SimpleDateFormat(rule);
            return format.format(date);
        }
        return "0";
    }

    /**
     * 设置显示方式
     *
     * @param view     需要改变显示方式的View
     * @param showType 改为什么显示方式，View.VISIBLE、View.GONE、View.INVISIBLE
     */
    public static void setShowModel(View view, int showType) {
        switch (showType) {
            case View.VISIBLE:
                if (view.getVisibility() != View.VISIBLE) {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case View.GONE:
                if (view.getVisibility() != View.GONE) {
                    view.setVisibility(View.GONE);
                }
                break;
            case View.INVISIBLE:
                if (view.getVisibility() != View.INVISIBLE) {
                    view.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                Log.e(TAG, "setShowType, 传入非法参数。");
                break;
        }
    }

    /**
     * 关闭软键盘
     */
    public static void closeKeyboard(Activity activity, EditText... editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        for (int i = 0; i < editText.length; i++) {
            EditText ed = editText[i];
            if (imm.isActive(ed)) {  //i(imm.isActive())  //一直是true
//            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//显示或隐藏软键盘
                imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);//隐藏软键盘
                //imm.showSoftInput(myEditText, 0);//显示软键盘
           /* mOrderTimeLl.requestFocus();
            mOrderTimeLl.setFocusableInTouchMode(true);*/
                ed.clearFocus();
                ed.setSelected(false);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Activity activity, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);//显示软键盘
//            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//显示或隐藏软键盘
//            imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);//隐藏软键盘
           /* mOrderTimeLl.requestFocus();
            mOrderTimeLl.setFocusableInTouchMode(true);*/
    }
    
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
```
