# 工具类
常用工具类   
[![GitHub release](https://img.shields.io/badge/release-v1.0.6-green.svg)](https://github.com/Thor-jelly/CommontUtils/releases)

# 添加依赖

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


	dependencies {
	        compile 'com.github.Thor-jelly:CommonUtils:最新版本号'
	}

```

---

# Activity管理类

`ActivityUtils`

# 通用方法
`CommonUtils`

## 关闭软键盘
`fun closeKeyboard(activity: Activity, vararg editText: EditText)`

## 显示软键盘
`fun showKeyboard(activity: Activity, editText: EditText)`

## 自定义日期转换，秒传入
`fun getSTime(time: String?, rule: String): String`

## 自定义日期转换，毫秒传入
`fun getMSTime(time: String?, rule: String): String`

## 是否具有摄像头
`fun hasCamera(): Boolean`

# 大数字运算
具体看代码`NumberUtils`

# 数字转汉字
`NumberTurnToHz`

```
NumberTurnToHz.builder()
              .setNumber(String.valueOf(allPrice))
              .setUnit("元")
              .build()
              .getHz();
```

---

# kotlin扩展／委托

# sharePreferences委托
**PreferencesBy**

使用

1. 首先先创建一个扩展方便使用

    ```
    /**
     * 类描述：sharePreferences扩展<br/>
     * 创建人：吴冬冬<br/>
     * 创建时间：2018/11/15 14:46 <br/>
     */
    inline fun <reified I, T> I.preferencesExt(default: T) = PreferencesBy(AppContext, "", default, "shandian")
    ```
    
2. 代码中使用

    ```
    var mName by preferencesExt("")
    ```

# Int扩展

`fun Int.dp2px(context: Context): Int`
`fun Int.sp2px(context: Context): Int`

# String扩展

```
fun String?.parseInt(): Int

fun String?.parseDouble(): Double

/**
 * 格式化数字升级版
 * @param dot 保留小数点位数
 * @param isShowEnd0 是否显示末尾0
 * @param roundingMode 四舍五入模式
 */
@JvmOverloads
fun String?.formatNumber(
    dot: Int,
    isShowEnd0: Boolean = false,
    roundingMode: RoundingMode = RoundingMode.FLOOR
): String

/**
 * 格式化数字
 * ##0.000
 * @param roundingMode 四舍五入模式
 */
@JvmOverloads
fun String?.formatNumber(
    formatStr: String,
    roundingMode: RoundingMode = RoundingMode.FLOOR
): String

/**
 * string 转换为中英文数组 设置到富文本中
 */
fun String?.toZhEnSpan(zhTextSizePx: Int, enTextSizePx: Int): Array<TextMoreStyle>

/**
 * 添加中英文字体大小富文本 进入数组
 */
private fun addZhEnSpanType2Array(
    sb: StringBuilder,
    zhTextSizePx: Int,
    result: MutableList<TextMoreStyle>
)
```

# TextView扩展

```
/**
 * 设置字体样式
 */
fun TextView?.setMoreStyle(
    vararg itemArray: TextMoreStyle?
)

/**
 * 通过defaultFromStyle设置textView字体typeface
 */
fun TextView?.setDefaultFromStyle(typefaceType: Int = Typeface.BOLD)

/**
 * textView显示全部文字
 */
fun TextView?.setShowAllText()
```

# boolean扩展

可以直接.yes 或者 .no 执行判断
如果需要判断中的值，则可以在.otherwise

```
val otherwise = true.yes {
            Log.d("123===", "true")
            1
        }.otherwise {
            Log.d("123===", "false")
            2
        }

        Log.d("123===", "-->${otherwise}")

123===: true
123===: -->1

val otherwise1 = false.yes {
            Log.d("123===", "true")
            3
        }.otherwise {
            Log.d("123===", "false")
            4
        }
        Log.d("123===", "-->${otherwise1}")

false
-->4
```