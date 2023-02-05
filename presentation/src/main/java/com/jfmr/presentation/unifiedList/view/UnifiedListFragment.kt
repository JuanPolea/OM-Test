package com.jfmr.presentation.unifiedList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jfmr.presentation.R
import com.jfmr.presentation.databinding.FragmentItemListBinding
import com.jfmr.presentation.extensions.clear
import com.jfmr.presentation.extensions.loadGif
import com.jfmr.presentation.unifiedList.model.UnifiedListState
import com.jfmr.presentation.unifiedList.viewmodel.UnifiedListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UnifiedListFragment : Fragment() {

    private val viewModel: UnifiedListViewModel by viewModels()
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var unifiedListAdapter: UnifiedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvUnifiedList.layoutManager = linearLayoutManager
        unifiedListAdapter = UnifiedListAdapter() {
            viewModel.onItemClicked(it)
        }
        binding.rvUnifiedList.adapter = unifiedListAdapter
        viewModel.getUnifiedList()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.unifiedListState.collectLatest { state ->
                when (state) {
                    is UnifiedListState.Loading -> {
                        shouldShowLoading(true)
                    }

                    is UnifiedListState.Success -> {
                        (binding.rvUnifiedList.adapter as UnifiedListAdapter).submitList(state.unifiedItemLists)
                        shouldShowLoading(false)
                    }

                    is UnifiedListState.Error -> {
                        shouldShowLoading(false)
                    }

                    is UnifiedListState.NavigateToDetail -> {
                        shouldShowLoading(false)
                        binding.root.findNavController().navigate(
                            UnifiedListFragmentDirections.actionItemFragmentToDetailFragment(state.id)
                        )
                    }
                }
            }
        }

        binding.scrollUp.setOnClickListener {
            binding.rvUnifiedList.smoothScrollToPosition(0)
        }

        binding.rvUnifiedList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.scrollUp.visibility =
                    if (linearLayoutManager.findFirstVisibleItemPosition() != 0) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        })
    }

    private fun shouldShowLoading(shouLoading: Boolean) {
        if (shouLoading) {
            binding.ivLoading.loadGif(R.raw.ic_movie_scene)
            binding.ivLoading.visibility = View.VISIBLE
        } else {
            binding.ivLoading.visibility = View.GONE
            binding.ivLoading.clear(this@UnifiedListFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
