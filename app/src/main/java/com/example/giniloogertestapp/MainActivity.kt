package com.example.giniloogertestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gini_logger.FileLogger
import com.example.gini_logger.logD
import com.example.gini_logger.logE
import com.example.gini_logger.logI
import com.example.giniloogertestapp.ui.theme.GiniLoogerTestAppTheme

class MainActivity : ComponentActivity() {

    private val data = listOf(
        "first",
        "second",
        "third",
        "forth",
        "fifth",
        "sixth",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FileLogger.enableToWriteLogsToFile(filePath = filesDir.path, fileName = "logger")

        setContent {
            GiniLoogerTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        data = data,
                        onDebugClick = { logD(message = it) },
                        onInfoClick = { logI(message = it)},
                        onErrorClick = { logE(message = it) },
                        onMultipleLogClick = { invokeMultipleLog() },
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FileLogger.disableToWriteLogsTofile()
    }

    private fun invokeMultipleLog() {
        logD {
            title("Multiple log")
            data.forEach { message(value = it) }
        }
    }
}