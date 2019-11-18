package com.lmy.hwvcnative.processor

import android.view.Surface
import com.lmy.hwvcnative.CPPObject

class AlImageProcessor private constructor() : CPPObject() {
    init {
        handler = create()
    }

    fun release() {
        if (!isNativeNull()) {
            release(handler)
        }
        handler = 0L
    }

    fun prepare(surface: Surface) {
        if (!isNativeNull()) {
            prepare(handler, surface)
        }
    }

    fun updateWindow(surface: Surface) {
        if (!isNativeNull()) {
            updateWindow(handler, surface)
        }
    }

    fun setCanvas(w: Int, h: Int, color: Int = 0) {
        if (!isNativeNull()) {
            setCanvas(handler, w, h, color)
        }
    }

    fun addLayer(path: String) {
        if (!isNativeNull()) {
            val ret = addLayer(handler, path)
        }
    }

    private external fun create(): Long
    private external fun release(handler: Long)
    private external fun prepare(handler: Long, surface: Surface)
    private external fun updateWindow(handler: Long, surface: Surface)
    private external fun setCanvas(handler: Long, w: Int, h: Int, color: Int)
    private external fun addLayer(handler: Long, path: String): Int

    companion object {
        fun create(): AlImageProcessor = AlImageProcessor()
    }
}