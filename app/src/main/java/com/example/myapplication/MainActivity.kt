package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val boolTrue: Boolean = true
    val boolFalse = false

    val long: Long = 42
    private val long1 = 234

    val int: Int = long1.toInt()
    val int1 = long1.toInt()

    val maxLong: Long = Long.MAX_VALUE
    val minLong = Long.MIN_VALUE

    val double: Double = 2.3
    val double1 = 9.2

    val float: Float = 2.0F
    val otherFloat = 2E3F

    val aChar: Char = '0'
    val bChar = '中'
    val float3: Float = 2.0F

    //    val cChar: Char = '\u000f'
    private val string: String = "Hello"
    private val char = String(charArrayOf('H', 'e', 'l', 'l', 'o'))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "onCreate: Hello World!")
        println(string == char)
        println(string === char)
        val arg1 = 0
        val arg2 = 1
        println("$arg1 + $arg2 = ${arg1 + arg2}")
        println("你好'张三'")
        val aGirl: Girl = Girl("女孩", "18", "看电影")
        val bGirl: Girl = Girl("女孩", "10", "喝汽水")
        val aBoy: Boy = Boy("男孩", "20", "打游戏")
        val aA: A = A()
        println(aGirl)
        println(bGirl)
        println(aBoy)
        println(aA)
        B()

        val parent:Parent = Child()
        if(parent is Child){
            println(parent.name)
        }

        val intent = Intent(this,ForegroundService::class.java)

        startForegroundService(intent)

    }

    open class People(name: String, age: String, like: String) {
        init {
            println("来了个${this.javaClass.simpleName},姓名：$name,age:$age,like:$like")
        }
    }

    class Boy(name: String, age: String, like: String) : People(name, age, like)

    class Girl(name: String, age: String, like: String) : People(name, age, like)

    class A : Any()

    fun B(): String? {
        return null
    }

    fun setName() {
        println("123123")
    }

    open class Parent

    class Child : Parent() {
        val name: String = "张三"
    }

}