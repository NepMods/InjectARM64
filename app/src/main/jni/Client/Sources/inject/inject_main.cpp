/* Copyright (c) 2015, Simone 'evilsocket' Margaritelli
   Copyright (c) 2015, Jorrit 'Chainfire' Jongma
   See LICENSE file for details */

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>

#include <dirent.h>

#include "inject.h"
pid_t getPID(const char* process_name) {
    if (process_name == nullptr) {
        return -1;
    }
    DIR* dir = opendir("/proc");
    if (dir == nullptr) {
        return -1;
    }
    struct dirent* entry;
    while((entry = readdir(dir)) != NULL) {
        size_t pid = atoi(entry->d_name);
        if (pid != 0) {
            char file_name[30];
            snprintf(file_name, 30, "/proc/%zu/cmdline", pid);
            FILE *fp = fopen(file_name, "r");
            char temp_name[50];
            if (fp != nullptr) {
                fgets(temp_name, 50, fp);
                fclose(fp);
                if (strcmp(process_name, temp_name) == 0) {
                    return pid;
                }
            }
        }
    }
    return -1;
}


int main( int argc, char **argv ) {
#ifndef DEBUG
    libinject_log(NULL);
#endif


    pid_t pid     =(getPID(argv[1]));
    char* library = argv[2];

    if (libinject_inject(pid, library) == 0) {
        printf("-ok\n");
        return 0;
    } else {
        printf("-fail\n");
        return 1;
    }
}
