package com.gozap.jetpack.ui.ui.activity

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gozap.jetpack.R
import com.gozap.jetpack.databinding.ActivityDetailBinding
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.util.AppPrefsUtils
import com.gozap.jetpack.viewmodel.CustomViewModelProvider
import com.gozap.jetpack.viewmodel.DetailModel

class DetailActivity : AppCompatActivity() {


    companion object {
        fun startDetailActivity(context: Context, id: Long?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID, id)
            context.startActivity(intent)
        }
    }


    val model: DetailModel by viewModels {
        CustomViewModelProvider.providerDetailMedel(
            this,
            intent.getLongExtra(BaseConstant.DETAIL_SHOE_ID, 1L),
            AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
        )
    }

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.model = model
        initListener()
    }

    fun initListener() {
        binding.lifecycleOwner = this
        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.fbFavourite.setOnClickListener {
            binding.fbFavourite.animate()
                .rotation(360.0f)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {

                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        model.favourite()
                    }

                })
                .setDuration(200)
                .start()
        }
    }
}
