package com.gun.android_marvel_example.ui.home.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gun.android_marvel_example.databinding.FragmentHomeBannerBinding

class HomeBannerFragment : Fragment() {
    private lateinit var binding: FragmentHomeBannerBinding

    companion object {
        fun newInstance() = HomeBannerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            Glide.with(ivThumbnail)
                .load("https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/landscape_xlarge.jpg")
                .into(ivThumbnail)
        }
    }


}