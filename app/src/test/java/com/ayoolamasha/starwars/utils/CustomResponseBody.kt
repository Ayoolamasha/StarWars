package com.ayoolamasha.starwars.utils

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource


class CustomResponseBody(
    private val content: String, // The content of your custom response body
    private val contentType: MediaType? = null
) : ResponseBody() {

    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long {
        return content.length.toLong()
    }

    override fun source(): BufferedSource {
        val buffer = Buffer()
        buffer.writeUtf8(content)
        return buffer
    }

    // You can override other methods as needed for your use case
}