#include "Game.h"
#include <cstdlib>

JNIEXPORT jint JNICALL Java_Game_nextStep
  (JNIEnv *env, jobject obj, jintArray arr){
    const jsize length = env->GetArrayLength(arr);
    jint inbuffer[length];
    env->GetIntArrayRegion(arr, 0, length, inbuffer);
    int solution;
    int value = rand()%24;
    while(inbuffer[value] != 0){
        value = rand()%24;
    }
    if(inbuffer[value]  == 0){
        solution = value;
    }

    return (jint)solution;
  }