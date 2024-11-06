/*
 * Copyright (c) 2024 Dominic Joas
 * This file is part of the CloudApp-Project and licensed under the
 * General Public License V3.
 */

package de.dojodev.mybiblenotes.bibleapi.requests.base

import de.dojodev.mybiblenotes.bibleapi.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.internal.ignoreIoExceptions
import java.util.concurrent.TimeUnit

@Suppress("SameParameterValue")
open class BasicRequest(urlPart: String) {
    private val jsonType: MediaType = "application/json".toMediaType()
    private var state: ResponseCode = ResponseCode.NoRequest
    private var message: String = ""

    protected val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        ignoreIoExceptions {  }
        coerceInputValues = true
    }
    protected val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(120L, TimeUnit.SECONDS)
        .readTimeout(120L, TimeUnit.SECONDS)
        .writeTimeout(300L, TimeUnit.SECONDS)
        .connectTimeout(120L, TimeUnit.SECONDS)
        .build()
    private val url: String = "https://api.scripture.api.bible/v1/$urlPart"

    protected fun buildRequest(endPoint: String, method: String, body: String?): Request? {
        this.state = ResponseCode.NoRequest
        this.message = ""
        if(method.lowercase()=="get") {
            return Request.Builder()
                .url("$url$endPoint")
                .addHeader("api-key", BuildConfig.API_KEY)
                .build()
        } else if(method.lowercase()=="post") {
            return if(body==null) {
                Request.Builder()
                    .url("${this.url}$endPoint")
                    .addHeader("api-key", BuildConfig.API_KEY)
                    .build()
            } else {
                return Request.Builder()
                    .url("${this.url}$endPoint")
                    .addHeader("api-key", BuildConfig.API_KEY)
                    .post(body.toRequestBody(jsonType))
                    .build()
            }
        } else if(method.lowercase()=="delete") {
            return Request.Builder()
                .url("${this.url}$endPoint")
                .addHeader("api-key", BuildConfig.API_KEY)
                .delete(body?.toRequestBody(jsonType))
                .build()
        } else if(method.lowercase()=="patch") {
            return Request.Builder()
                .url("${this.url}$endPoint")
                .addHeader("api-key", BuildConfig.API_KEY)
                .patch(body?.toRequestBody(jsonType)!!)
                .build()
        } else if(method.lowercase()=="put") {
            return if(body != null) {
                Request.Builder()
                    .url("${this.url}$endPoint")
                    .addHeader("api-key", BuildConfig.API_KEY)
                    .put(body.toRequestBody(jsonType))
                    .build()
            } else {
                Request.Builder()
                    .url("${this.url}$endPoint")
                    .addHeader("api-key", BuildConfig.API_KEY)
                    .build()
            }
        }
        return null
    }

    protected fun setState(response: Response) {
        when(response.code) {
            200 -> this.state = ResponseCode.Successful
            400 -> this.state = ResponseCode.NotAuthorizedOrInvalidLanguageCode
            401 -> this.state = ResponseCode.UnAuthorized
            403 -> this.state = ResponseCode.NotAuthorized
            404 -> this.state = ResponseCode.NotFound
            else -> this.state = ResponseCode.Unknown
        }
        this.message = response.message
    }

    fun getState(): ResponseCode {
        return this.state
    }

    fun getMessage(): String {
        return this.message
    }
}