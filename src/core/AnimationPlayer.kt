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
        Thread(Runnable { displayAnimation(bitImgPath)}).start()
        Thread(Runnable { MusicPlayer(bgmPath).start(false) }).start()

    }

    //开始播放动画
    private fun displayAnimation(bitImgPath: String) {
        try {
            val size = File(bitImgPath).listFiles()!!.size
            for (i in 0..size - 1) {
                var frame = getAsciiStr(File("$bitImgPath/$i.txt"))

                PrintFrame.print(frame,fps)

               /* TimeUnit.MILLISECONDS.sleep((1000.0/fps).toLong())
                //清空控制台
                PrintFrame.cls()*/
            }
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }

    }

    //从文件对象中读取字符串，并打印到控制台
    private fun getAsciiStr(imgfile: File):String {
        if (!imgfile.isFile)
            return ""
        val sb = StringBuilder("")
        try {
            val input = BufferedReader(FileReader(imgfile))

            var str = input.readLine()
            while (str != null){
                sb.append(str+"\n")
                str = input.readLine()
            }
            //打印字符画
            input.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return sb.toString()
    }


}