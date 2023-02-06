package com.jfmr.presentation.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.jfmr.presentation.MainActivity
import com.jfmr.presentation.databinding.FragmentDetailBinding
import com.jfmr.presentation.detail.model.DetailEvent
import com.jfmr.presentation.detail.model.DetailState
import com.jfmr.presentation.detail.model.RecommendedItemList
import com.jfmr.presentation.detail.model.ResponseUI
import com.jfmr.presentation.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recommendationsAdapter: RecommendationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.onEvent(DetailEvent.GetDetail(args.externalIds))

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recommendationsAdapter = RecommendationsAdapter() {
            detailViewModel.onEvent(DetailEvent.OnRecommendedCliked(it))
        }
        binding.rvRecommendations.adapter = recommendationsAdapter
        binding.rvRecommendations.layoutManager = linearLayoutManager

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.detailState.collectLatest { state ->
                    when (state) {
                        is DetailState.Loading -> {
                            shouldShowLoading(true)
                        }

                        is DetailState.Success -> {
                            updateUI(state)
                            shouldShowLoading(false)
                        }

                        is DetailState.Error -> {
                            shouldShowLoading(false)
                        }

                        DetailState.NavigateBack -> {
                            shouldShowLoading(false)
                            findNavController().navigateUp()
                        }
                    }
                }
            }
        }
    }

    private fun updateUI(state: DetailState.Success) =
        with(binding) {
            binding.ivToolbar.load(state.detail.attachments.first().value){
                crossfade(true)
            }
            itemDescriptionValue.text = state.detail.description
            itemGenreValue.text = state.detail.genre
            itemYearValue.text = state.detail.year.toString()
            itemDefinitionValue.text = state.detail.definition
            itemReviewValue.text = state.detail.reviewerRating
            (rvRecommendations.adapter as RecommendationsAdapter).submitList(
                state.detail.recommendations.response.toUI()
            )
            binding.collapsingToolbarLayout.title = state.detail.name
        }

    private fun shouldShowLoading(shouLoading: Boolean) {
        if (shouLoading) {
            binding.ivLoading.visibility = View.VISIBLE
        } else {
            binding.ivLoading.visibility = View.GONE
        }
    }
}

private fun List<ResponseUI>.toUI(): MutableList<RecommendedItemList> {
    filterNotNull()
        .map { response ->
            RecommendedItemList(
                id = response.id,
                name = response.name,
                externalContendId = response.externalContentId,
                imageXUI = response.images.first(),
            )
        }.toMutableList().let {
            return it
        }
}
