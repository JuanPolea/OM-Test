package com.jfmr.presentation.unifiedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jfmr.presentation.R
import com.jfmr.presentation.unifiedList.viewmodel.UnifiedListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UnifiedListFragment : Fragment() {

    private val viewModel: UnifiedListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        viewModel.getUnifiedList()

        return view
    }

    companion object {
        const val TAG = "UnifiedListFragment"

        @JvmStatic
        fun newInstance() =
            UnifiedListFragment()
    }
}
