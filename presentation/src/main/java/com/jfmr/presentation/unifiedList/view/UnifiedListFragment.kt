package com.jfmr.presentation.unifiedList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jfmr.presentation.databinding.FragmentItemListBinding
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
    private val linearLayoutManager = LinearLayoutManager(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        binding.rvUnifiedList.layoutManager = linearLayoutManager
        binding.rvUnifiedList.adapter = UnifiedListAdapter() {
            viewModel.onItemClicked(it)
        }.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUnifiedList()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.unifiedListState.collectLatest { state ->
                    when (state) {
                        is UnifiedListState.Loading -> {
                            binding.progressIndicator.visibility = View.VISIBLE
                        }

                        is UnifiedListState.Success -> {
                            (binding.rvUnifiedList.adapter as UnifiedListAdapter).submitList(state.unifiedItemLists)
                            binding.progressIndicator.visibility = View.GONE
                        }

                        is UnifiedListState.Error -> {
                            binding.progressIndicator.visibility = View.GONE
                        }
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
}
