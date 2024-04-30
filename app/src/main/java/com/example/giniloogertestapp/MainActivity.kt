package com.example.giniloogertestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gini_logger.data.core.GiniLogger
import com.example.gini_logger.data.core.log
import com.example.gini_logger.data.core.logD
import com.example.gini_logger.data.core.logE
import com.example.gini_logger.data.core.logI
import com.example.gini_logger.data.core.logV
import com.example.gini_logger.data.core.logW
import com.example.gini_logger.data.default_implementation.DefaultWritingMode
import com.example.gini_logger.domain.LogBuilder
import com.example.gini_logger.domain.Logger
import com.example.gini_logger.domain.WritingMode
import com.example.gini_logger.domain.model.Level
import com.example.giniloogertestapp.ui.theme.GiniLoogerTestAppTheme

enum class CustomWritingMode : WritingMode { Local, Remote }


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

        GiniLogger.initializeDefault(
            /** uncomment this to check logging to file */
//            writingMode = DefaultWritingMode.File(filePath = filesDir.path)
            /** uncomment this to check logging to file and console */
//            writingMode = DefaultWritingMode.ConsoleAndFile(filePath = filesDir.path)
        )

        /** customising default implementation */
//        GiniLogger.initializeDefault(
//            minLevel = Level.Debug,
//            writingMode = DefaultWritingMode.Console,
//            formatter = { message -> "return formatted message: $message" },
//            tag = "custom tag",
//            loggerProvider = { mode: DefaultWritingMode ->
//                when (mode) {
//                    is DefaultWritingMode.File -> {
//                        /** your logic */
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//
//                    DefaultWritingMode.Console -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//
//                    is DefaultWritingMode.ConsoleAndFile -> {
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//                }
//            },
//            logBuilderProvider = {
//                /** provide your custom LogBuilder */
//                object : LogBuilder {
//
//                    override fun build(): String {
//                        return "build your string"
//                    }
//
//                    override fun message(value: Any) {
//                        /** use this block to build multiple log */
//                    }
//                }
//            }
//        )

        /** custom implementation */
//        GiniLogger.initialize(
//            minLevel = Level.Debug,
//            writingMode = CustomWritingMode.Remote,
//            formatter = { message -> "return formatted message: $message" },
//            tag = "custom tag",
//            loggerProvider = { mode: CustomWritingMode ->
//                when (mode) {
//                    CustomWritingMode.Local -> {
//                        /** your logic */
//                        /** your logic */
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//
//                    CustomWritingMode.Remote -> {
//                        /** your logic */
//                        Logger { level: Level, tag: String, message: String -> /** your logic */ }
//                    }
//                }
//            },
//            logBuilderProvider = {
//
//                /** provide your custom LogBuilder */
//                object : LogBuilder {
//
//                    override fun build(): String {
//                        return "build your string"
//                    }
//
//                    override fun message(value: Any) {
//                        /** use this block to build multiple log */
//                    }
//                }
//            }
//        )

        setContent {
            GiniLoogerTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        data = data,
                        onVerboseClick = { logV(message = it) },
                        onDebugClick = { logD(message = it) },
                        onInfoClick = { logI(message = it) },
                        onWarnClick = { logW(message = it) },
                        onErrorClick = { logE(message = it) },
                        onMultipleVerboseLogClick = { invokeMultipleLog(level = Level.Verbose) },
                        onMultipleDebugLogClick = { invokeMultipleLog(level = Level.Debug) },
                        onMultipleInfoLogClick = { invokeMultipleLog(level = Level.Info) },
                        onMultipleWarnLogClick = { invokeMultipleLog(level = Level.Warn) },
                        onMultipleErrorLogClick = { invokeMultipleLog(level = Level.Error) },
                    )
                }
            }
        }
    }

    private fun invokeMultipleLog(level: Level) {
        log(level = level) {
            message("Multiple log")
            data.forEach { message(value = it) }
        }
    }
}