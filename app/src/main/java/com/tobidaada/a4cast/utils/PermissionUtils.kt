package com.tobidaada.a4cast.utils

import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

fun AppCompatActivity.requestUserPermission(
    permissions: List<String>,
    onGranted: () -> Unit
) {
    Dexter.withContext(this)
        .withPermissions(permissions)
        .withListener(object: MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                val areAllPermissionsGranted = report?.areAllPermissionsGranted() ?: false
                if (areAllPermissionsGranted) onGranted()
            }

            override fun onPermissionRationaleShouldBeShown(
                list: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }
        })
        .onSameThread()
        .check()
}