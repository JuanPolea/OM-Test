package com.jfmr.omtest.data.remote

import com.jfmr.omtest.data.api.OMService
import com.jfmr.omtest.data.di.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UnifiedListRemoteDataSourceImpl @Inject constructor(
    private val omService: OMService,
    @Dispatchers.DispatcherIO private val coroutineDispatcher: CoroutineDispatcher,
) : UnifiedListRemoteDataSource {

    override suspend fun retrieveList() =
        withContext(coroutineDispatcher) {
            omService.retrieveUnifiedList()
        }
}
