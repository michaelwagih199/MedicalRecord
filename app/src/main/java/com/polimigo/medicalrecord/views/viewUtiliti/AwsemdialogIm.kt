package com.polimigo.babydaycare.view.viewUtiliti

import android.app.Activity
import android.content.Intent
import com.example.awesomedialog.*
import com.polimigo.medicalrecord.R


class AwsemdialogIm {
    /**
     * https://github.com/chnouman/AwesomeDialog
     */
    fun sucssDialog(activity: Activity, i: Intent, title: String, body: String) {
        AwesomeDialog.build(activity)
                .title(title)
                .body(body)
                .icon(R.drawable.ic_congrts)
                .onPositive("goToMyAccount") {
                    activity.startActivity(i)
                    activity.finish()
                }
    }


}