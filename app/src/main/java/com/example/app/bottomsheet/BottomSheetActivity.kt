package com.example.app.bottomsheet

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mylibrary.adapter.BaseRecyclerViewAdapter
import com.android.mylibrary.adapter.TitleAdapter
import com.android.mylibrary.bottomsheet.BottomSheet
import com.android.mylibrary.bottomsheet.BottomSheet.Builder
import com.android.mylibrary.bottomsheet.BottomSheetHelper
import com.android.mylibrary.bottomsheet.BottomSheetViewDialog
import com.example.app.R
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : AppCompatActivity(), BaseRecyclerViewAdapter.OnItemClickListener {

    val viewManager by lazy {
        LinearLayoutManager(this)
    }
    val viewAdapter by lazy {
        TitleAdapter(this)
    }
    val dialog by lazy {
        BottomSheetViewDialog(this, DialogInterface.OnClickListener { var1, var2 ->

        })
    }
    val dataList = mutableListOf<String>(
        "From Xml",
        "Without Icon",
        "Dark Theme",
        "Grid",
        "Style",
        "Style from Theme",
        "ShareAction",
        "FullScreen",
        "Menu Manipulate",
        "HeaderLayout",
        "BottomSheet View"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        viewAdapter.dataList = dataList
        viewAdapter.itemClickListener = this
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onItemClick(position: Int) {
        if (position == 10) {
            dialog.refreshTopicList(dataList)
            dialog.show()
        } else {
            showDialog(position)
        }
    }

    var sheet: BottomSheet? = null


    override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
        when (id) {
            0 -> {
                sheet = Builder(this).icon(getRoundedBitmap(R.mipmap.icon))
                    .title("To " + dataList[id]).sheet(R.menu.list)
                    .listener { dialog, which ->
                        this@BottomSheetActivity.onClick(
                            dataList[id],
                            which
                        )
                    }.build()
            }
            1 -> {
                sheet = Builder(this).sheet(R.menu.noicon)
                    .listener { dialog, which ->
                        this@BottomSheetActivity.onClick(
                            dataList[id],
                            which
                        )
                    }.build()
            }
            2 -> {
                sheet = Builder(this).darkTheme().title("To " + dataList[id]).sheet(R.menu.list)
                    .sheet(R.menu.list)
                    .listener { dialog, which ->
                        this@BottomSheetActivity.onClick(
                            dataList[id],
                            which
                        )
                    }.build()
            }
            3 -> {
                sheet = Builder(this).sheet(R.menu.list)
                    .listener { dialog, which ->
                        this@BottomSheetActivity.onClick(
                            dataList[id],
                            which
                        )
                    }.grid().build()
            }
            4 -> {
                sheet = Builder(this, R.style.BottomSheet_StyleDialog).title(
                    "To " + dataList[id]
                ).sheet(R.menu.list).listener { dialog, which ->
                    this@BottomSheetActivity.onClick(
                        dataList[id],
                        which
                    )
                }.build()
            }
            5 -> {
                sheet =
                    Builder(this).title("To " + dataList[id]).sheet(R.menu.longlist)
                        .listener { dialog, which ->
                            this@BottomSheetActivity.onClick(
                                dataList[id],
                                which
                            )
                        }.limit(R.integer.bs_initial_list_row).build()
            }
            6 -> {
                sheet = getShareActions("Hello " + dataList[id])?.title(
                    "Share To " + dataList[id]
                )?.limit(R.integer.no_limit)?.build()
            }
            7 -> {
                sheet = getShareActions("Hello " + dataList[id])?.title(
                    "Share To " + dataList[id]
                )?.build()
            }
            8 -> {
                sheet = Builder(this).icon(getRoundedBitmap(R.mipmap.icon))
                    .title("To " + dataList[id]).sheet(R.menu.list)
                    .listener { dialog, which ->
                        this@BottomSheetActivity.onClick(
                            dataList[id],
                            which
                        )
                    }.build()
                var menu: Menu = sheet!!.getMenu()
                menu.getItem(0).title = "MenuClickListener"
                menu.getItem(0).setOnMenuItemClickListener {
                    AlertDialog.Builder(this@BottomSheetActivity).apply {
                        setMessage("You can set OnMenuItemClickListener for each item")
                        setTitle("OnMenuItemClickListener")
                    }.create()
                    true
                }
                menu.getItem(1).isVisible = false
                menu.getItem(2).isEnabled = false
                menu.add(Menu.NONE, 23, Menu.NONE, "Fresh meal!")
                menu.findItem(23).setIcon(R.mipmap.perm_group_user_dictionary)
                menu.findItem(23).setOnMenuItemClickListener {
                    Toast.makeText(this@BottomSheetActivity, "Hello", Toast.LENGTH_SHORT).show()
                    true
                }
                menu.setGroupVisible(android.R.id.empty, false)
            }
            9 -> {
                sheet = Builder(this, R.style.BottomSheet_CustomizedDialog).grid()
                    .title("To " + dataList[id]).sheet(R.menu.list)
                    .listener(DialogInterface.OnClickListener { dialog, which ->
                        this@BottomSheetActivity.onClick(
                            dataList[id],
                            which
                        )
                    }).build()
                sheet!!.setOnShowListener {
                    Toast.makeText(
                        this@BottomSheetActivity,
                        "I'm showing",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                sheet!!.setOnDismissListener {
                    Toast.makeText(
                        this@BottomSheetActivity,
                        "I'm dismissing",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return sheet
    }


    private fun getShareActions(text: String): Builder? {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        return BottomSheetHelper.shareAction(this, shareIntent)
    }


    fun onClick(name: String, which: Int) {
        when (which) {
            R.id.share -> Toast.makeText(this, "Share to $name", Toast.LENGTH_SHORT).show()
            R.id.upload -> Toast.makeText(this, "Upload for $name", Toast.LENGTH_SHORT).show()
            R.id.call -> Toast.makeText(this, "Call to $name", Toast.LENGTH_SHORT).show()
            R.id.help -> Toast.makeText(this, "Help me!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getRoundedBitmap(imageId: Int): Drawable? {
        val src = BitmapFactory.decodeResource(resources, imageId)
        val dst: Bitmap
        dst = if (src.width >= src.height) {
            Bitmap.createBitmap(
                src,
                src.width / 2 - src.height / 2,
                0,
                src.height,
                src.height
            )
        } else {
            Bitmap.createBitmap(
                src,
                0,
                src.height / 2 - src.width / 2,
                src.width,
                src.width
            )
        }
        val roundedBitmapDrawable =
            RoundedBitmapDrawableFactory.create(resources, dst)
        roundedBitmapDrawable.cornerRadius = dst.width / 2.toFloat()
        roundedBitmapDrawable.setAntiAlias(true)
        return roundedBitmapDrawable
    }


}