package com.example.bitwisee

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    var changed : Boolean = false
    var staticValue:Int = 0
    var staticValueInput:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun myfunc2(view : View){
        changed = true
        val inpt = findViewById<EditText>(R.id.value)
        var value = inpt.getText().toString()
        if(value.isEmpty()){
            value = "0"
        }

        staticValueInput = value.toInt()

    }
    fun myfunc(view:View){
        val position = (view.tag as String).toInt()
        var num : Int
        val bool = (view as Switch).isChecked
        val inpt = findViewById<EditText>(R.id.value)
        var value = inpt.getText().toString()
        var value2 = staticValue
        if(value.isEmpty()){
            value = "0"
        }

        if(changed){
            value = "$staticValue"
        }

        num = if(bool){
            set(value.toInt(),position)
        }else{
            clear(value.toInt(),position)
        }

        staticValue = num
        if(changed){
            if(staticValue == staticValueInput){
                findViewById<ConstraintLayout>(R.id.bg).setBackgroundColor(Color.parseColor("#FF0000"))
                changed = false

            }
        }else{
            inpt.setText("$num")
            findViewById<ConstraintLayout>(R.id.bg).setBackgroundColor(Color.parseColor("#FFFFFF"))

        }
    }
    fun set(inpt:Int,position:Int):Int{
        var mask:Int = 1 shl position
        return inpt.or(mask)
    }
    fun clear(inpt:Int,position:Int):Int{
        var mask:Int = 1 shl position
        return inpt.and(mask.inv())
    }
}



