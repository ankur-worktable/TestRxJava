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
            log(TAG, string)
        }

        fun log(tag: String, string: String) {
            if (!enableLogging) return
            Log.d(tag, string)
        }
    }
}