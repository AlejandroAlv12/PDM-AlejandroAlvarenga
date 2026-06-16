package com.pdmcourse2026.basictemplate

import android.app.Application
import com.pdmcourse2026.basictemplate.data.AppProvider

class BasicTemplateApplication : Application() {
    val appProvider: AppProvider by lazy {
        AppProvider(this)
    }
}
