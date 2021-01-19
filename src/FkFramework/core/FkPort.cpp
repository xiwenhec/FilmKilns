/*
* Copyright (c) 2018-present, aliminabc@gmail.com.
*
* This source code is licensed under the MIT license found in the
* LICENSE file in the root directory of this source tree.
*/

#include "FkPort.h"

FkPort::FkPort(uint32_t port, std::shared_ptr<FkProtocol> protocol, FkPort::PortFunc func)
        : FkObject(), port(port), protocol(protocol), func(func) {

}

FkPort::~FkPort() {
    port = 0;
    protocol = nullptr;
    func = nullptr;
}