package com.abit.taskapp.ui.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit.taskapp.data.Pref
import com.abit.taskapp.databinding.FragmentOnBoardingBinding
import com.abit.taskapp.ui.onBoarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {
    private lateinit var pref : Pref

    private lateinit var binding: FragmentOnBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter() {
            pref.saveSeen()
            findNavController().navigateUp()

        }
        binding.viewpager.adapter = adapter
        binding.indicator.setViewPager(binding.viewpager)
    }
}