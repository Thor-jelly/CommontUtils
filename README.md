# 工具类
常用工具类   
[![GitHub release](https://img.shields.io/badge/release-v1.0.4-green.svg)](https://github.com/Thor-jelly/CommontUtils/releases)

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

# kotlin扩展／委托

## sharePreferences委托
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
    
## boolean扩展

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

# 常用视图方法
`ViewUtils`

## 判断是否为一种显示方式 
`fun isShowType(@NonNull view: View, @Visibility showType: Int): Boolean`

## 设置显示方式
`fun setShowModel(@NonNull view: View, @Visibility showType: Int)`

## textView显示全部文字
`fun setShowAllText(tv: TextView)`

# 通用方法
`CommonUtils`

## dp值转换为px值
`fun dp2px(context: Context, value: Float): Int`

## 将sp值转换为px值
`fun sp2px(context: Context, value: Float): Int`

## 关闭软键盘
`fun closeKeyboard(activity: Activity, vararg editText: EditText)`

## 显示软键盘
`fun showKeyboard(activity: Activity, editText: EditText)`

## 格式化数字
`fun getRuleDecimalFormat(num: String, rule: String): String`

## 自定义日期转换，秒传入
`fun getSTime(time: String?, rule: String): String`

## 自定义日期转换，毫秒传入
`fun getMSTime(time: String?, rule: String): String`

## 是否具有摄像头
`fun hasCamera(): Boolean`

# Activity管理类

`ActivityUtils`