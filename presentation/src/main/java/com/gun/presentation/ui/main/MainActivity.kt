package com.gun.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.ActivityMainBinding
import com.gun.presentation.common.extenstion.getBottomSoftKeyHeight
import com.gun.presentation.common.extenstion.setStatusBarOrigin
import com.gun.presentation.common.extenstion.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy<ActivityMainBinding> { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setNavigation()
    }

    private fun setNavigation() {
        with(binding) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(navHostFragment.id) as NavHostFragment
            val navController = navHostFragment.findNavController()

            bottomNavigation.setupWithNavController(navController)

            bottomNavigation.setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, navController)
                // popBackStack 메서드를 호출하여 이동하려는 대상이 백 스택에 존재할 시, 해당 대상까지 사이의 스택을 지우고 가져온다.
                navController.popBackStack(item.itemId, inclusive = false)
            }
        }
    }

    /**
     * StatusBar/Navigation(Bottom) 영역까지 확장 (투명 상태바)
     *
     * @param needExpand - true : 확장
     *                     false : 축소 (원복)
     * */
    fun setWindowLayoutNoLimit(needExpand: Boolean) {
        if (needExpand) {
            setStatusBarTransparent()
        } else {
            setStatusBarOrigin()
        }

        binding.bottomNavigation.updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomMargin = if (needExpand) getBottomSoftKeyHeight() else 0
        }
    }
}
