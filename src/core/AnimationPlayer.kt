package core

import java.util.concurrent.TimeUnit
import java.io.File
import java.io.IOException
import java.io.FileReader
import java.io.BufferedReader





/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/10/11 22:51
 * Description:
 */
class AnimationPlayer {

    private var fps = 15;
    constructor(fps:Int,bitImgPath:String){
        this.fps = fps
        displayAnimation(bitImgPath)

    }
    constructor(fps:Int,bitImgPath:String,bgmPath:String){
        this.fps = fps
        Thread(Runnable { MusicPlayer(bgmPath).start(false) }).start()
        Thread(Runnable { displayAnimation(bitImgPath)}).start()

    }

    //开始播放动画
    private fun displayAnimation(bitImgPath: String) {
        try {
            val size = File(bitImgPath).listFiles()!!.size
            for (i in 0..size - 1) {
                printImg(File("$bitImgPath/$i.txt"))
                TimeUnit.MILLISECONDS.sleep((1000.0/fps).toLong())
            }
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }

    }

    //从文件对象中读取字符串，并打印到控制台
    private fun printImg(imgfile: File) {
        if (!imgfile.isFile)
            return

        try {
            val input = BufferedReader(FileReader(imgfile))
            val sb = StringBuilder()
            var str = input.readLine();
            while (str != null){
                sb.append(str+"\n")
                str = input.readLine()
            }
            //打印字符画
            println(sb)
            input.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }


}