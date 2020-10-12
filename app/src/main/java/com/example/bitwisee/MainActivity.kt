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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun myfunc2(view : View){
        changed = true
    }
    fun myfunc(view:View){
        val inpt = findViewById<EditText>(R.id.value)
        var value = inpt.getText().toString().let{ if(it.isEmpty())  "0" else it }

        val position = (view.tag as String).toInt()
        staticValue = if((view as Switch).isChecked){
            set(staticValue,position)
        }else{
            clear(staticValue,position)
        }
        if(changed){
            if(value.toInt() == staticValue){
                findViewById<ConstraintLayout>(R.id.bg).setBackgroundColor(Color.parseColor("#FF0000"))
                changed = false
            }
        }else{
            inpt.setText("$staticValue")
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



