package core

import java.util.concurrent.TimeUnit

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/10/12 0:28
 * Description: 
 */
class PrintFrame {
    static void print(def frame,int fps){
        println frame;
        TimeUnit.MILLISECONDS.sleep((Integer)(1000/fps))
        printf "\033c"
    }
}
