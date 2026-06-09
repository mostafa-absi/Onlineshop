package com.lib.Onlineshop.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityObserver @Inject constructor(
    @ApplicationContext ctx: Context
) {
    private val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val online: Flow<Boolean> = callbackFlow {

        trySend(ctx.isNetworkState())
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(net: Network) {
                trySend(true)
            }

            override fun onLost(net: Network) {
                trySend(ctx.isNetworkState())
            }
            override fun onCapabilitiesChanged(n: Network, c: NetworkCapabilities) {
                trySend(
                    c.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            c.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                )
            }
        }
        cm.registerDefaultNetworkCallback(callback)
        awaitClose() { cm.unregisterNetworkCallback(callback) }
    }.distinctUntilChanged()
}