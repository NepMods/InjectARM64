//
// Created by arjun on 6/19/24.
//

#ifndef ANDROID_MOD_MENU_3_2_SOCKET_H
#define ANDROID_MOD_MENU_3_2_SOCKET_H

#include <linux/un.h>
#include <cstddef>
#include <iostream>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <string>
#include <vector>
#include <cerrno>
#include <cstdio>
#include <sys/un.h>

#define SOCKET_NAME "nepmods"

class Socket {
public:
    int sockfd;
    sockaddr_un addr_server;
    char socket_name[108]; // 108 sun_path length max
    bool created,connected;

    Socket();

    bool Create();
    bool Connect();
    void Close();

    int sendData(void *inData, size_t size);
    bool send(void* inData, size_t size);

    int recvData(void *outData, size_t size);
    size_t receive(void* outData);
};


#endif //ANDROID_MOD_MENU_3_2_SOCKET_H
