package com.codecool.wimmexpensetracker.new_category_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.room_db.Category
import java.util.*

class AddCategoryActivity : AppCompatActivity() {

    // Known bug : The image views shrink after you changing the text in the editText.

    private var editText : EditText? = null
    private var gridLayout : GridLayout? = null

    private var categoryName : TextView? = null
    private var previewColor : ImageView? = null

    private var createButton : Button? = null
    private var cancelButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        editText = findViewById(R.id.category_name_et)
        gridLayout = findViewById(R.id.grid)
        categoryName = findViewById(R.id.category_name)
        previewColor = findViewById(R.id.preview_color)
        createButton = findViewById(R.id.create_category_btn)
        cancelButton = findViewById(R.id.cancel_btn)

        gridLayout?.let{
            for ( item in it.children){
                if ( item is ImageView){
                    item.setOnClickListener {
                        previewColor?.background = item.background
                    }
                }
            }
        }

        editText?.addTextChangedListener {
            categoryName?.text = it.toString()
        }

        cancelButton?.setOnClickListener { finish() }

        createButton?.setOnClickListener {

            categoryName?.let{ ctName ->
                previewColor?.let{ pvColor ->
                    var colorId = CategoryColor.RED
                    when ( pvColor.background ){
                        (findViewById<ImageView>(R.id.color_yellow).background) -> colorId = CategoryColor.YELLOW
                        (findViewById<ImageView>(R.id.color_blue).background) -> colorId = CategoryColor.BLUE
                        (findViewById<ImageView>(R.id.color_green).background) -> colorId = CategoryColor.GREEN
                        (findViewById<ImageView>(R.id.color_pink).background) -> colorId = CategoryColor.PINK
                        (findViewById<ImageView>(R.id.color_red).background) -> colorId = CategoryColor.RED
                    }

                    if ( ctName.text.toString().isNotEmpty() && ctName.text.toString().isNotBlank() && ctName.text.length > 4) {
                        val category = Category(
                                uId = UUID.randomUUID().toString(),
                                categoryName = ctName.text.toString(),
                                colorId = colorId
                        )


                    } else {
                        Toast.makeText(applicationContext, resources.getString(R.string.category_name_error), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}