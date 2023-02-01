package com.abit.taskapp.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abit.taskapp.R
import com.abit.taskapp.databinding.ItemOnboardingBinding
import com.abit.taskapp.model.OnBord
import com.abit.taskapp.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val data = arrayListOf(
        OnBord(
            "text1",
            "text2",
            R.raw.onboard_anim
        ),
        OnBord(
            "text1",
            "text2",
            R.raw.onboard_anim
        ),
        OnBord(
            "text1",
            "text2",
            R.raw.onboard_anim
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBord: OnBord) {
            binding.tvTitleOnB.text = onBord.title
            binding.tvDescOnB.text = onBord.desc
            onBord.img?.let { binding.ivImage.setAnimation(it) }
            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }

        }

    }
}