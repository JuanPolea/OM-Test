package com.jfmr.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.jfmr.presentation.unifiedList.UnifiedListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentFragment = UnifiedListFragment.newInstance()
        supportFragmentManager
            .commit {
                add(R.id.list, currentFragment, UnifiedListFragment.TAG)
            }
    }
}
