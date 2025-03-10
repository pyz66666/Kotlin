package com.pyz.myapplication.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.pyz.myapplication.R
import com.pyz.myapplication.adapter.CustomTransformer
import com.pyz.myapplication.adapter.MainFragmentAdapter
import com.pyz.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initData()
    }

    private fun initData() {

    }

    private val fragments = listOf(Fragment1(),Fragment2(),ScrollingFragment3())
    private val title = listOf("TAB1","TAB2","TAB3")
    private val fragmentAdapter by lazy {
        MainFragmentAdapter(fragments,this@MainActivity)
    }
    private fun initView() {
        mainBinding.vp2.adapter = fragmentAdapter
        mainBinding.vp2.setPageTransformer(CustomTransformer())
        mainBinding.vp2.offscreenPageLimit = 3
        TabLayoutMediator(mainBinding.tabLayout,mainBinding.vp2
        ) { tab, position ->
            tab.text = title[position]
        }.attach()

    }
}