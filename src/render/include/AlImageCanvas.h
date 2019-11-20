/*
* Copyright (c) 2018-present, aliminabc@gmail.com.
*
* This source code is licensed under the MIT license found in the
* LICENSE file in the root directory of this source tree.
*/

#ifndef HWVC_ANDROID_ALIMAGECANVAS_H
#define HWVC_ANDROID_ALIMAGECANVAS_H

#include "Object.h"
#include "HwAbsTexture.h"
#include "TextureAllocator.h"

al_class(AlImageCanvas) {
public:
    AlImageCanvas();

    ~AlImageCanvas();

    void release();

    void update(int32_t w, int32_t h, int32_t color, TextureAllocator *texAllocator);

    HwAbsTexture *getOutput();

private:
    AlImageCanvas(const AlImageCanvas &e) : Object() {};
private:
    HwAbsFBObject *fbo = nullptr;
    //Manager by TextureAllocator. DON`T delete here.
    HwAbsTexture *mCanvasTex = nullptr;
};


#endif //HWVC_ANDROID_ALIMAGECANVAS_H
