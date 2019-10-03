package com.tops.filepicker.utills

import android.content.ContentResolver
import android.os.Bundle
import com.tops.filepicker.cursors.DocScannerTask
import com.tops.filepicker.cursors.PhotoScannerTask
import com.tops.filepicker.cursors.loadercallbacks.FileMapResultCallback
import com.tops.filepicker.cursors.loadercallbacks.FileResultCallback
import com.tops.filepicker.model.Document
import com.tops.filepicker.model.FileType
import com.tops.filepicker.model.PhotoDirectory

import java.util.Comparator


object MediaStoreHelper {

    fun getDirs(contentResolver: ContentResolver, args: Bundle, resultCallback: FileResultCallback<PhotoDirectory>) {
        PhotoScannerTask(contentResolver, args, resultCallback).execute()
    }

    fun getDocs(contentResolver: ContentResolver,
                fileTypes: List<FileType>,
                comparator: Comparator<Document>?,
                fileResultCallback: FileMapResultCallback) {
        DocScannerTask(contentResolver, fileTypes, comparator, fileResultCallback).execute()
    }
}