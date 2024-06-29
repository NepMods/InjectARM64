LOCAL_PATH := $(call my-dir)


include $(CLEAR_VARS)
LOCAL_MODULE := GlobalInject
LOCAL_SRC_FILES := Client/libs/$(TARGET_ARCH_ABI)/libinject.so
include $(PREBUILT_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := xdl

LOCAL_CXXFLAGS := -std=c++11 -fno-exceptions -fno-rtti

LOCAL_EXPORT_C_INCLUDES:=$(LOCAL_PATH)/nep/include
LOCAL_SRC_FILES := Server/Sources/nep/src/xdl.c \
    Server/Sources/nep/src/xdl_iterate.c \
    Server/Sources/nep/src/xdl_linker.c \
    Server/Sources/nep/src/xdl_lzma.c \
    Server/Sources/nep/src/xdl_util.c
LOCAL_C_INCLUDES := $(LOCAL_PATH)/Server/Sources//nep/include

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := MyLibName
LOCAL_CFLAGS := -w -s -Wno-error=format-security -fvisibility=hidden -fpermissive -fexceptions
LOCAL_CPPFLAGS := -w -s -Wno-error=format-security -fvisibility=hidden -Werror -std=c++17
LOCAL_CPPFLAGS += -Wno-error=c++11-narrowing -fpermissive -Wall -fexceptions
LOCAL_LDFLAGS += -Wl,--gc-sections,--strip-all,-llog
LOCAL_LDLIBS := -llog -landroid -lEGL -lGLESv2
LOCAL_ARM_MODE := arm
LOCAL_C_INCLUDES += $(LOCAL_PATH)

# Here you add the cpp file to compile
LOCAL_SRC_FILES := Client/Client.cpp \
	Client/Sources/Socket/Socket.cpp \

include $(BUILD_SHARED_LIBRARY)
