package com.example.waterplant.utils

import android.graphics.Bitmap

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.lang.Exception
import android.R

import android.graphics.BitmapFactory





class ImageManager {

    fun getBytes(imageView: ImageView): ByteArray? {
        return try {
            val bitmap = (imageView.getDrawable() as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val bytesData: ByteArray = stream.toByteArray()
            stream.close()
            bytesData
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        return null
    }
    fun getBitmap(byteArr : ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(byteArr, 0, byteArr.size)
    }
}