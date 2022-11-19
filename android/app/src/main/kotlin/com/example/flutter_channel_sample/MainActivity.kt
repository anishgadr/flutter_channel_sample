package com.example.flutter_channel_sample

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant


class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(FlutterEngine(this))
        getFlutterEngine()?.getDartExecutor()?.let {
            MethodChannel(
                it
                    .getBinaryMessenger(), CHANNEL).setMethodCallHandler { call, result ->
                if (call.method == "helloFromNativeCode") {
                    val greetings = helloFromNativeCode()
                    result.success(greetings)
                }
            }
        }
    }

    private fun helloFromNativeCode(): String {
        return "Hello from Native Android Code"
    }

    companion object {
        private const val CHANNEL = "flutter.native/helper"
    }
}