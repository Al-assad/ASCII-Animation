package main

import core.MusicPlayer

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/10/11 23:45
 * Description:
 */
fun main(args: Array<String>) {
    Thread(Runnable { MusicPlayer("./bgm/AIaho.wav").start(true) }).start()
    Thread.sleep(50000)
}