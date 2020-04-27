package com.jelly.thor.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jelly.thor.commonutils.*
import com.jelly.thor.commonutils.bean.TextMoreStyle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mName by preferencesExt("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.setMoreStyle(
            TextMoreStyle
                .builder("Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!\n")
                .build(),
            TextMoreStyle
                .builder("123456777343453453")
                .setParagraphHeight(12.dp2px(this))
                .build(),
            TextMoreStyle
                .builder("Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!Hello World!")
                .build()
        )

        val otherwise = true.yes {
            Log.d("123===", "true")
            1
        }.otherwise {
            Log.d("123===", "false")
            2
        }
        Log.d("123===", "-->$otherwise")


        val otherwise1 = false.yes {
            Log.d("123===", "true")
            3
        }.otherwise {
            Log.d("123===", "false")
            4
        }
        Log.d("123===", "-->$otherwise1")


        val otherwise2 = true.no {
            Log.d("123===", "true")
            5
        }.otherwise {
            Log.d("123===", "false")
            6
        }
        Log.d("123===", "-->$otherwise2")


        val otherwise3 = false.no {
            Log.d("123===", "true")
            7
        }.otherwise {
            Log.d("123===", "false")
            8
        }
        Log.d("123===", "-->$otherwise3")
    }
}
