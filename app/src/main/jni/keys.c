#include <jni.h>


JNIEXPORT jstring JNICALL
Java_com_example_syscostarwars_services_network_api_NetworkModule_getBaseURL(JNIEnv *env,jobject thiz) {
    return (*env)->NewStringUTF(env, "https://swapi.dev/api/");
}