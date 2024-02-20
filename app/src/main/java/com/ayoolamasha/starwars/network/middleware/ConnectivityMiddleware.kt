package com.ayoolamasha.starwars.network.middleware

import android.Manifest
import androidx.annotation.RequiresPermission
import com.ayoolamasha.starwars.network.InternetConnectivityHelper
import com.ayoolamasha.starwars.network.model.NetworkMiddlewareFailure
import javax.inject.Inject

class ConnectivityMiddleware @Inject constructor(
    private val connectivityUtils: InternetConnectivityHelper,
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(
            middleWareExceptionMessage = "No Internet Connection.Please Check Your Connection"
        )

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isValid(): Boolean = connectivityUtils.isNetworkAvailable()
}