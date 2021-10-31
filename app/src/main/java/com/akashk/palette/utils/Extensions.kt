package com.akashk.palette.utils

import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener({
            continuation.resume(future.get())
        }, executor)
    }
}

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)

val String.color
    get() = Color(android.graphics.Color.parseColor(this))