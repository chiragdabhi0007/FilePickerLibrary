package com.tops.filepicker

import android.app.Activity
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.File


class CropperBuilder {


    fun OpenCropActivity(context: Activity, uri: String, aspectRationX: Int, aspectRatioY: Int, isRectangle: Boolean) {

        val f = File(uri)
        val outUri: Uri

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            outUri = FileProvider.getUriForFile(context, BuildConfig.LIBRARY_PACKAGE_NAME + ".provider", f)
        } else
            outUri = Uri.fromFile(f)

        CropImage.activity(outUri).setAspectRatio(aspectRationX, aspectRatioY).setCropShape(if (isRectangle) CropImageView.CropShape.RECTANGLE else CropImageView.CropShape.OVAL)
                .start(context)
    }

    fun OpenCropFragment(context: Activity, fragment: Fragment, uri: String, aspectRationX: Int, aspectRatioY: Int, isRectangle: Boolean) {

        val f = File(uri)
        val outUri: Uri

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            outUri = FileProvider.getUriForFile(context, BuildConfig.LIBRARY_PACKAGE_NAME + ".provider", f)
        } else
            outUri = Uri.fromFile(f)

        CropImage.activity(outUri).setAspectRatio(aspectRationX, aspectRatioY).setCropShape(if (isRectangle) CropImageView.CropShape.RECTANGLE else CropImageView.CropShape.OVAL)
                .start(context)

    }

    companion object {
        @JvmStatic
        val instance: CropperBuilder
            get() = CropperBuilder()
    }

}