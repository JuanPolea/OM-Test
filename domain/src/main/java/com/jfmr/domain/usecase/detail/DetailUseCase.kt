package com.jfmr.domain.usecase.detail

import com.jfmr.domain.model.rtv1.RTV1DetailDomain

interface DetailUseCase {

    suspend operator fun invoke(externalId:String): RTV1DetailDomain?
}
