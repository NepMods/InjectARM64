//
// Created by arjun on 6/19/24.
//

#ifndef ANDROID_MOD_MENU_3_2_RESPONSE_H
#define ANDROID_MOD_MENU_3_2_RESPONSE_H

Socket server;

enum Mode {
    InitMode = -1,
    HackMode = -2,
    StopMode = -3,
};

struct Request {
    int Mode;
    int value;
    bool boolean;
};

struct Response {
    bool Success;
};

int InitServer() {
    if (!server.Create()) {
        //LOGE("SE:1");
        return -1;
    }

    if (!server.Bind()) {
        //LOGE("SE:2");
        return -1;
    }

    if (!server.Listen()) {
        //LOGE("SE:3");
        return -1;
    }
    return 0;
}
#endif //ANDROID_MOD_MENU_3_2_RESPONSE_H
