package com.example.app.swip

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import kotlinx.android.synthetic.main.activity_swipe_menu.*

class SwipeMenuActivity : AppCompatActivity() {
    var index = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_menu)
        recycler_view.layoutManager = LinearLayoutManager(this);
        val mShowItems: MutableList<Bean> =
            ArrayList()

        var bean = Bean("item");
        var imgList = ArrayList<String>();
        imgList.add("https://img3.chouti.com/CHOUTI_200621_53BD661AC7934C2AB804FFD92D231E19.jpg?imageView2/1/q")
        imgList.add("https://img3.chouti.com/CHOUTI_200621_4AB39B23CFAA4AFF9B129F24D5643093.jpg?imageView2/1/q")
        imgList.add("https://img3.chouti.com/CHOUTI_200621_7B717FFD986348F78F471F06A466AC85.jpg?imageView2/1/q")
        imgList.add("https://img3.chouti.com/CHOUTI_200621_A4275DB30B61409DB22E099482A17EEA.jpg?imageView2/1/q")
        imgList.add("https://img3.chouti.com/CHOUTI_200621_82CCB348433B493B8EEF94F5A9FAD417.jpg?imageView2/1/q")
        imgList.add("https://img3.chouti.com/CHOUTI_200621_2038C3706CAB46F3B7781D823E824063.jpg?imageView2/1/q")

        bean.imgList = imgList;
        mShowItems.add(bean)

//        for (i in 0..49) {
//            var bean = Bean("item = $i");
//            var imgList = ArrayList<String>();
//            if (i % 3 == 0) {
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_53BD661AC7934C2AB804FFD92D231E19.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_4AB39B23CFAA4AFF9B129F24D5643093.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_7B717FFD986348F78F471F06A466AC85.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_A4275DB30B61409DB22E099482A17EEA.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_82CCB348433B493B8EEF94F5A9FAD417.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_2038C3706CAB46F3B7781D823E824063.jpg?imageView2/1/q")
//            } else if (i % 5 == 0) {
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_BFD2E64EB8C84956AC77BA9BCE2AD85A.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_3DE2239F582544669FA144452FD90881.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_8B3F0A3B060141309F4552655F9EBF5E.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_6A757C22007940C38B2434C3C678BCAD.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_431529F4D6EF48B3B00C61F2E797E6BF.jpg?imageView2/1/q")
//                imgList.add("https://img3.chouti.com/CHOUTI_200621_EA7F05F4527E49E58AF79DE6503BB429.jpg?imageView2/1/q")
//            }
//            bean.imgList = imgList;
//            mShowItems.add(bean)
//        }


        var adapter = ItemAdapter(mShowItems);
        recycler_view.adapter = adapter;
        adapter.setOnItemClickListener { adapter, view, position ->
            {
                Log.d("======", "点击了item   :   " + ++index)
            }
        }
        adapter.setOnItemChildClickListener({ adapter, view, position ->
            {
                if (view.id == R.id.tv_menu1) {
                    Log.d("======", "点击菜单1   " + ++index)
                } else {
                    Log.d("======", "点击菜单2   " + ++index)
                }
            }
        })
        adapter.setOnItemLongClickListener({ adapter, view, position ->
            Log.d("======", "长按了" + ++index)
            true
        })
    }


}
