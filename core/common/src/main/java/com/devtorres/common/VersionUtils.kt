package com.devtorres.common

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object VersionUtils  {

    @ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
    inline fun isSdkIntAtLeast(sdkVersion: Int, action: () -> Unit) {
        if (Build.VERSION.SDK_INT >= sdkVersion) action()
    }

}