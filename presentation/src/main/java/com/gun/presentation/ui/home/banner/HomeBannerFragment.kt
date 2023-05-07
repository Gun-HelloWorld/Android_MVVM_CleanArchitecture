package com.gun.presentation.ui.home.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gun.domain.model.Event
import com.gun.mvvm_cleanarchitecture.databinding.FragmentHomeBannerBinding

const val KEY_HOME_BANNER_DATA = "key_home_banner_data"
const val HOME_BANNER_IMAGE_SIZE = "landscape_xlarge"

class HomeBannerFragment : Fragment() {
    private lateinit var binding: FragmentHomeBannerBinding

    private lateinit var event: Event

    companion object {
        fun newInstance(event: Event): HomeBannerFragment {
            val bundle = Bundle()
            bundle.putSerializable(KEY_HOME_BANNER_DATA, event)

            val fragment = HomeBannerFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBannerBinding.inflate(inflater, container, false)
        event = requireArguments().getSerializable(KEY_HOME_BANNER_DATA) as Event

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            val thumbnailUrl = "${event.thumbnailPath}/$HOME_BANNER_IMAGE_SIZE.${event.thumbnailExtension}"
            Glide.with(ivThumbnail)
                .load(thumbnailUrl)
                .into(ivThumbnail)
        }
    }


}