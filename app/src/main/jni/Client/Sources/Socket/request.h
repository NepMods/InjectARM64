//
// Created by arjun on 6/19/24.
//

#ifndef ANDROID_MOD_MENU_3_2_REQUEST_H
#define ANDROID_MOD_MENU_3_2_REQUEST_H

Socket client;

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

bool isSConnected(){
    return client.connected;
}

bool Send(int mode = Mode::HackMode, int value = 0,  bool boolean = false){
    Request request{mode, value, boolean};
    int code = client.send((void*) &request, sizeof(request));
    if(code > 0) {
        Response response{};
        size_t length = client.receive((void*) &response);
        if(length > 0){
            return response.Success;
        }
    }
    return false;
}

int startClient(){
    client = Socket();
    if(!client.Create()){
        return -1;
    }
    if(!client.Connect()){
        return -1;
    }
    if(!Send(Mode::InitMode)){
        return -1;
    }
    return 0;
}
void stopClient() {
    if(client.created && isSConnected()){
        Send(Mode::StopMode);
        client.Close();
    }
}
#endif //ANDROID_MOD_MENU_3_2_REQUEST_H
