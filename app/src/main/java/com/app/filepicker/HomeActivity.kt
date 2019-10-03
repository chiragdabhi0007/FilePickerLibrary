package com.app.filepicker

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.theartofdev.edmodo.cropper.CropImage
import com.tops.filepicker.CropperBuilder
import com.tops.filepicker.FilePickerBuilder
import com.tops.filepicker.FilePickerConst
import java.util.*

/**
 * Created by chirag on 02/10/19.
 */
class HomeActivity : AppCompatActivity() {

    private val CUSTOM_REQUEST_CODE = 532

    private var recyclerView: RecyclerView? = null
    private lateinit var btnPickImage: Button


    private var photoPaths = ArrayList<String>()
    private var docPaths = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        recyclerView = findViewById(R.id.recyclerview)
        btnPickImage = findViewById(R.id.btnPickImage)


        btnPickImage.setOnClickListener {
            onPickPhoto()
        }
    }

    fun onPickPhoto() {

        FilePickerBuilder.instance
                .setMaxCount(10)
                //    .setSelectedFiles(photoPaths)
                .setActivityTheme(R.style.AppTheme)
                .setActivityTitle("Please select media")
                .enableVideoPicker(true)
                .enableCameraSupport(true)
                .checkBoxColor(ContextCompat.getColor(this, R.color.colorAccent))
                .showGifs(true)
                .showFolderView(true)
                .enableSelectAll(false)
                .enableImagePicker(true)
                // .setCameraPlaceholder(R.drawable.custom_camera)
                .withOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .pickPhoto(this, CUSTOM_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CUSTOM_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK && data != null) {
                photoPaths = ArrayList()
                photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA)!!)

                if (photoPaths.size > 0)
                    CropperBuilder.instance.OpenCropActivity(this@HomeActivity, photoPaths.get(0), 1, 1, true)

            }

            FilePickerConst.REQUEST_CODE_DOC -> if (resultCode == Activity.RESULT_OK && data != null) {
                docPaths = ArrayList<String>()
                docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS)!!)
            }

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK && data != null) {
                 val result = CropImage.getActivityResult(data)

                //val result = (data.getParcelableExtra<Parcelable>("CROP_IMAGE_EXTRA_RESULT") as CropImage.ActivityResult).uri


                val resultUri = result.uri

                resultUri.path?.let { photoPaths.add(it) }
            }
        }

        addThemToView(photoPaths, docPaths)
    }


    private fun addThemToView(imagePaths: ArrayList<String>?, docPaths: ArrayList<String>?) {
        val filePaths = ArrayList<String>()
        if (imagePaths != null) filePaths.addAll(imagePaths)

        if (docPaths != null) filePaths.addAll(docPaths)

        val recyclerView: RecyclerView? = findViewById(R.id.recyclerview)
        if (recyclerView != null) {
            val layoutManager = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
            layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            recyclerView.setLayoutManager(layoutManager)
            val imageAdapter = ImageAdapter(this, filePaths)
            recyclerView.setAdapter(imageAdapter)
            recyclerView.setItemAnimator(DefaultItemAnimator())
        }

        Toast.makeText(this, "Num of files selected: " + filePaths.size, Toast.LENGTH_SHORT).show()
    }

}