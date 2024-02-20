package com.ayoolamasha.starwars.network.middleware

import com.ayoolamasha.starwars.network.model.NetworkMiddlewareFailure


abstract class NetworkMiddleware {
    abstract val failure: NetworkMiddlewareFailure

    abstract fun isValid(): Boolean
}