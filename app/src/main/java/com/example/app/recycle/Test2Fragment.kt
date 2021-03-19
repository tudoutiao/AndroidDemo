package com.example.app.recycle

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.recycle.datapter.Test1Adapter
import kotlinx.android.synthetic.main.fragment_test1.*
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Test2Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    var dataList = mutableListOf<String>()
    fun initData() {
        for (i in 0 until 5) {
            dataList.add("" + i)
        }
    }

    lateinit var adapter: Test1Adapter
    fun initView() {
        adapter = Test1Adapter(activity, dataList, itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter;
    }

    val itemTouchHelper by lazy {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return if (recyclerView.layoutManager is GridLayoutManager) {
                    val dragFlags =
                        ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    val swipeFlags = 0
                    makeMovementFlags(
                        dragFlags,
                        swipeFlags
                    )
                } else {
                    val dragFlags =
                        ItemTouchHelper.UP or ItemTouchHelper.DOWN
                    val swipeFlags = 0
                    makeMovementFlags(
                        dragFlags,
                        swipeFlags
                    )
                }
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Log.e("hsjkkk", "onMove()")
                //得到当拖拽的viewHolder的Position
                val fromPosition = viewHolder.adapterPosition
                //拿到当前拖拽到的item的viewHolder
                val toPosition = target.adapterPosition
                if (fromPosition < toPosition) {
                    for (i in fromPosition until toPosition) {
                        Collections.swap(dataList, i, i + 1)
                    }
                } else {
                    for (i in fromPosition downTo toPosition + 1) {
                        Collections.swap(dataList, i, i - 1)
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.e("hsjkkk", "拖拽完成 方向" + direction);

            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                Log.e("hsjkkk", "onSelectedChanged()");
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                    viewHolder?.itemView.let {
                        it?.setBackgroundColor(Color.LTGRAY);
                    }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                Log.e("hsjkkk", "clearView()");
                viewHolder.itemView.setBackgroundColor(0);
            }

            override fun isLongPressDragEnabled(): Boolean {
                Log.e("hsjkkk", "isLongPressDragEnabled()");
                return super.isLongPressDragEnabled()

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }
        })


    }
    var dragListener: DragListener? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Test2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    //**//
    interface DragListener {
        /**
         * 用户是否将 item拖动到删除处，根据状态改变颜色
         */
        fun deleteState(delete: Boolean)

        /**
         * 是否于拖拽状态
         */
        fun dragState(start: Boolean)

        /**
         * 当用户与item的交互结束并且item也完成了动画时调用
         */
        fun remove(position: Int)

    }
}