package org.wit.archfieldwork.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.IOException

fun readImage (activity: Activity, resultCode: Int, data: Intent?): Bitmap?{
    var bitmap: Bitmap? = null
    if (resultCode == Activity.RESULT_OK && data != null && data.data != null){
        try{
            bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, data.data)
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
    return bitmap
}

fun readImageFromPath(context: Context, path: String): Bitmap?{
    var bitmap: Bitmap? = null
    var uri = Uri.parse(path)
    if(uri != null){
        try {
            val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri,"r")
            val fileDescriptor = parcelFileDescriptor.getFileDescriptor()
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
        }catch (e:Exception){
        }
    }
    return bitmap
}