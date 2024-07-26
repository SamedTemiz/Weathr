package com.timrashard.weathr.utils

import android.app.Activity
import android.app.AlertDialog

object DialogUtils {

    fun showNetworkUnavailableDialog(activity: Activity) {
        AlertDialog.Builder(activity)
            .setTitle("İnternet Bağlantısı Yok")
            .setMessage("Lütfen internet bağlantınızı kontrol edin ve tekrar deneyin.")
            .setPositiveButton("Tekrar Dene") { dialog, _ ->
                dialog.dismiss()
                activity.recreate()
            }
            .setNegativeButton("Kapat") { dialog, _ ->
                dialog.dismiss()
                activity.finish()
            }
            .create()
            .show()
    }
}