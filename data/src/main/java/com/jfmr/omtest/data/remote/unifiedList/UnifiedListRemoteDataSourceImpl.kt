package com.jfmr.omtest.data.remote.unifiedList

import com.jfmr.omtest.data.api.rt.v1.RTV1Service
import com.jfmr.omtest.data.di.Dispatchers
import com.jfmr.omtest.data.di.QRTV1Service
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UnifiedListRemoteDataSourceImpl @Inject constructor(
    @QRTV1Service private val rtv1Service: RTV1Service,
    @Dispatchers.DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) : UnifiedListRemoteDataSource {

    override suspend fun retrieveList() =
        withContext(coroutineDispatcher) {
            rtv1Service.retrieveUnifiedList()
        }
}
