package com.tops.filepicker.cursors.loadercallbacks

import com.tops.filepicker.model.Document
import com.tops.filepicker.model.FileType


/**
 * Created by chirag on 02/10/19.
 */

interface FileMapResultCallback {
    fun onResultCallback(files: Map<FileType, List<Document>>)
}

