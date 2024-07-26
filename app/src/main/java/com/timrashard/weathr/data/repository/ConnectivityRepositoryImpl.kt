package com.timrashard.weathr.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import com.timrashard.weathr.domain.repository.ConnectivityRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConnectivityRepositoryImpl(context: Context): ConnectivityRepository {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityRepository.NetworkStatus> = callbackFlow<ConnectivityRepository.NetworkStatus> {
        val initialStatus = checkCurrentStatus()
        trySend(initialStatus).isSuccess

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(ConnectivityRepository.NetworkStatus.Available).isSuccess
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(ConnectivityRepository.NetworkStatus.Lost).isSuccess
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()

    override fun checkCurrentStatus(): ConnectivityRepository.NetworkStatus {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return if (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            ConnectivityRepository.NetworkStatus.Available
        } else {
            ConnectivityRepository.NetworkStatus.Lost
        }
    }
}