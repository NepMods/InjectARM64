//
// Created by arjun on 6/19/24.
//

#include "Socket.h"

#include <cstddef>
#include <iostream>

Socket::Socket(){
    isCreated = false;
}

bool Socket::Create() {
    isCreated = (listenfd = socket(AF_UNIX, SOCK_STREAM, 0)) >= 0;
    return isCreated;
}

bool Socket::Bind() {
    memset(socket_name, 0, sizeof(socket_name));
    memcpy(&socket_name[0], "\0", 1);
    strcpy(&socket_name[1], SOCKET_NAME);

    memset(&addr_server, 0, sizeof(addr_server));
    addr_server.sun_family = AF_UNIX; // Unix Domain instead of AF_INET IP domain
    strncpy(addr_server.sun_path, socket_name, sizeof(addr_server.sun_path) - 1); // 108 char max

    if (bind(listenfd, (sockaddr *) &addr_server, sizeof(addr_server)) < 0) {
        Close();
        return false;
    }
    return true;
}

bool Socket::Listen() {
    if (listen(listenfd, BACKLOG) < 0) {
        Close();
        return false;
    }
    return true;
}

bool Socket::Accept() {
    if ((acceptfd = accept(listenfd, nullptr, nullptr)) < 0) {
        Close();
        return false;
    }
    return true;
}

int Socket::sendData(void *inData, size_t size) {
    char *buffer = (char *) inData;
    int numSent = 0;

    while (size) {
        do {
            numSent = write(acceptfd, buffer, size);
        } while (numSent == -1 && EINTR == errno);

        if (numSent <= 0) {
            Close();
            break;
        }

        size -= numSent;
        buffer += numSent;
    }
    return numSent;
}

bool Socket::send(void* inData, size_t size) {
    uint32_t length = htonl(size);
    if(sendData(&length, sizeof(uint32_t)) <= 0){
        return false;
    }
    return sendData((void*)inData, size) > 0;
}

int Socket::recvData(void *outData, size_t size) {
    char *buffer = (char *) outData;
    int numReceived = 0;

    while (size) {
        do {
            numReceived = read(acceptfd, buffer, size);
        } while (numReceived == -1 && EINTR == errno);

        if (numReceived <= 0) {
            Close();
            break;
        }

        size -= numReceived;
        buffer += numReceived;
    }
    return numReceived;
}

size_t Socket::receive(void* outData) {
    uint32_t length = 0;
    int code = recvData(&length, sizeof(uint32_t));
    if(code > 0){
        length = ntohl(length);
        recvData(outData, static_cast<size_t>(length));
    }
    return length;
}

void Socket::Close() {
    if (acceptfd > 0)
        close(acceptfd);
    if (listenfd > 0)
        close(listenfd);
}

