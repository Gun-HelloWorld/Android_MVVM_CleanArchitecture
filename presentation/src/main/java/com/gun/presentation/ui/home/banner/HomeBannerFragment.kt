package com.gun.presentation.ui.home.banner

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gun.mvvm_cleanarchitecture.databinding.FragmentHomeBannerBinding
import com.gun.presentation.ui.home.model.HomeListItem

private const val KEY_HOME_BANNER_DATA = "key_home_banner_data"

class HomeBannerFragment : Fragment() {
    private lateinit var binding: FragmentHomeBannerBinding

    private lateinit var data: HomeListItem

    companion object {
        fun newInstance(event: HomeListItem): HomeBannerFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_HOME_BANNER_DATA, event)

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

        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_HOME_BANNER_DATA, HomeListItem::class.java)!!
        } else {
            (requireArguments().getParcelable(KEY_HOME_BANNER_DATA) as? HomeListItem)!!
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            Glide.with(ivThumbnail)
                .load(data.getBannerItemThumbnailUrl())
                .into(ivThumbnail)
        }
    }


}