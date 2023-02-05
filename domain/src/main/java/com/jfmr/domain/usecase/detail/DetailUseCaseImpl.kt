package com.jfmr.domain.usecase.detail

import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import com.jfmr.domain.repository.detail.DetailRepository
import javax.inject.Inject

class DetailUseCaseImpl @Inject constructor(
    private val repository: DetailRepository
) : DetailUseCase {

    override suspend operator fun invoke(externalId:String): RTV1DetailDomain? {
        return repository.retrieveDetail(externalId)
    }
}
