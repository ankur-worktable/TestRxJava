package info.ankurpandya.testrxjava.utils

import android.util.Log

/**
 * Create by Ankur @ Worktable.sg
 */
class AppLogHelper {
    companion object {
        val TAG = "TestRX"

        var enableLogging = true

        fun log(string: String) {
            if (!enableLogging) return
            Log.d(TAG, string)
        }
    }
}