package com.timrashard.weathr.domain.repository

import kotlinx.coroutines.flow.Flow

interface ConnectivityRepository {

    fun observe() : Flow<NetworkStatus>

    fun checkCurrentStatus(): NetworkStatus

    enum class NetworkStatus{
        Available, Lost, Unknown
    }
}