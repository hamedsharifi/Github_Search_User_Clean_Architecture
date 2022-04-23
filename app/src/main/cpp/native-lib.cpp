//
// Created by handi on 12/27/19.
//

#include <jni.h>
#include <string>
#include <iostream>


//TODO more complex strings with XOR algorithms
extern "C" JNIEXPORT jstring JNICALL
Java_ir_moonify_android_githubusers_framework_network_getSearchUrl(JNIEnv *env, jclass clazz) {
    std::string codedUrl = "search/users";
    return env->NewStringUTF(codedUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_ir_moonify_android_githubusers_framework_network_getUserUrl(JNIEnv *env, jclass clazz) {
    std::string codedUrl = "users/";
    return env->NewStringUTF(codedUrl.c_str());
}
