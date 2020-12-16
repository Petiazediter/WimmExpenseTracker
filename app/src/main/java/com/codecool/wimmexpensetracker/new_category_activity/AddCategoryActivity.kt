package com.codecool.wimmexpensetracker.new_category_activity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.codecool.wimmexpensetracker.R
import com.codecool.wimmexpensetracker.data.CategoryColor
import com.codecool.wimmexpensetracker.mvvm.view_models.AddCategoryActivityViewModel
import com.codecool.wimmexpensetracker.room_db.Category
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AddCategoryActivity : AppCompatActivity() {

    private var editText : EditText? = null
    private var gridLayout : GridLayout? = null

    private var categoryName : TextView? = null
    private var previewColor : ImageView? = null

    private var createButton : Button? = null
    private var cancelButton : Button? = null

    private val mViewModel : AddCategoryActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        bindViews()
        setUpGridLayout()
        setUpEditText()
        cancelButton?.setOnClickListener { finish() }
        addListenerToCreateButton()

    }

    private fun addListenerToCreateButton() {
        createButton?.setOnClickListener {
            categoryName?.let { ctName ->
                previewColor?.let { pvColor ->
                    var colorId = CategoryColor.RED
                    when (pvColor.background) {
                        (findViewById<ImageView>(R.id.color_yellow).background) -> colorId = CategoryColor.YELLOW
                        (findViewById<ImageView>(R.id.color_blue).background) -> colorId = CategoryColor.BLUE
                        (findViewById<ImageView>(R.id.color_green).background) -> colorId = CategoryColor.GREEN
                        (findViewById<ImageView>(R.id.color_pink).background) -> colorId = CategoryColor.PINK
                        (findViewById<ImageView>(R.id.color_red).background) -> colorId = CategoryColor.RED
                    }

                    if (ctName.text.toString().isNotEmpty() && ctName.text.toString().isNotBlank() && ctName.text.length > 4) {
                        val category = Category(
                                uId = UUID.randomUUID().toString(),
                                categoryName = ctName.text.toString(),
                                colorId = colorId
                        )

                        mViewModel.addCategory(category)
                        Toast.makeText(applicationContext, "The new category successfully created!",Toast.LENGTH_LONG).show()
                        finish()

                    } else {
                        Toast.makeText(applicationContext, resources.getString(R.string.category_name_error), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setUpEditText() {
        editText?.addTextChangedListener {
            categoryName?.text = it.toString()
        }
    }

    private fun setUpGridLayout() {
        gridLayout?.let {
            for (item in it.children) {
                if (item is ImageView) {
                    item.setOnClickListener {
                        if ( item.background is ColorDrawable){
                            previewColor?.setBackgroundColor( (item.background as ColorDrawable).color)
                        }
                    }
                }
            }
        }
    }

    private fun bindViews() {
        editText = findViewById(R.id.category_name_et)
        gridLayout = findViewById(R.id.grid)
        categoryName = findViewById(R.id.category_name)
        previewColor = findViewById(R.id.preview_color)
        createButton = findViewById(R.id.create_category_btn)
        cancelButton = findViewById(R.id.cancel_btn)
    }
}