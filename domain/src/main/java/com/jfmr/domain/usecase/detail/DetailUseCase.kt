package com.jfmr.domain.usecase.detail

import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {

    operator fun invoke(externalId:String): Flow<RTV1DetailDomain?>
}
