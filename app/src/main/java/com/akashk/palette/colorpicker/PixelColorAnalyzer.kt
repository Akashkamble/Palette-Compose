package com.akashk.palette.colorpicker

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream

class PixelColorAnalyzer(private val colorCallback :(Int) -> Unit) :
    ImageAnalysis.Analyzer {
    private var lastAnalyzedTimestamp = 0L

    companion object{
        const val HALF_SECOND = 500
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val currentTimestamp = System.currentTimeMillis()
        // Get the new pixel value every half second
        val bitmap = imageProxy.convertImageProxyToBitmap()
        imageProxy.close()
        bitmap?.let {
            val bitmap1 = it
            val bitmapX = bitmap1.width / 2
            val bitmapY = bitmap1.height / 2
            val pixel = bitmap1.getPixel(bitmapX , bitmapY)
            val redValue = Color.red(pixel)
            val blueValue = Color.blue(pixel)
            val greenValue = Color.green(pixel)
            val intColor = Color.argb(255, redValue, greenValue, blueValue)
            Log.d("COLOR2","$intColor")
            colorCallback.invoke(intColor)
        }
    }

    private fun ImageProxy.convertImageProxyToBitmap(): Bitmap? {
        val yBuffer = planes[0].buffer // Y
        val vuBuffer = planes[2].buffer // VU

        val ySize = yBuffer.remaining()
        val vuSize = vuBuffer.remaining()

        val nv21 = ByteArray(ySize + vuSize)

        yBuffer.get(nv21, 0, ySize)
        vuBuffer.get(nv21, ySize, vuSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 50, out)
        val imageBytes = out.toByteArray()
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

}