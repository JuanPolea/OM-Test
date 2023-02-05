package com.jfmr.presentation.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.jfmr.presentation.R
import com.jfmr.presentation.databinding.FragmentDetailBinding
import com.jfmr.presentation.detail.model.DetailEvent
import com.jfmr.presentation.detail.model.DetailState
import com.jfmr.presentation.detail.viewmodel.DetailViewModel
import com.jfmr.presentation.extensions.clear
import com.jfmr.presentation.extensions.loadGif
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModels()

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
            ivDetail.load(state.detail.attachments.first().value)
            itemNameValue.text = state.detail.name
            itemDescriptionValue.text = state.detail.description
            itemGenreValue.text = state.detail.genre
            itemYearValue.text = state.detail.year.toString()
            itemDefinitionValue.text = state.detail.definition
            itemReviewValue.text = state.detail.reviewerRating
        }

    private fun shouldShowLoading(shouLoading: Boolean) {
        if (shouLoading) {
            binding.ivLoading.loadGif(R.raw.ic_movie_scene)
            binding.ivLoading.visibility = View.VISIBLE
        } else {
            binding.ivLoading.visibility = View.GONE
            binding.ivLoading.clear(this)
        }
    }
}
