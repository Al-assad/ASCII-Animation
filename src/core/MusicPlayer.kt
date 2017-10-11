package core

import javax.sound.sampled.*
import java.io.File
import java.io.IOException

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/10/11 22:17
 * Description:  音乐播放组件
 */
class MusicPlayer(private val musicPath: String ) {  //音频文件
    @Volatile private var run = true  //记录音频是否播放
    private var mainThread: Thread? = null   //播放音频的任务线程

    private var audioStream: AudioInputStream? = null
    private var audioFormat: AudioFormat? = null
    private var sourceDataLine: SourceDataLine? = null

    init {
        prefetch()
    }

    //数据准备
    private fun prefetch() {
        try {
            //获取音频输入流
            audioStream = AudioSystem.getAudioInputStream(File(musicPath))
            //获取音频的编码对象
            audioFormat = audioStream!!.format
            //包装音频信息
            val dataLineInfo = DataLine.Info(SourceDataLine::class.java,
                    audioFormat, AudioSystem.NOT_SPECIFIED)
            //使用包装音频信息后的Info类创建源数据行，充当混频器的源
            sourceDataLine = AudioSystem.getLine(dataLineInfo) as SourceDataLine

            sourceDataLine!!.open(audioFormat)
            sourceDataLine!!.start()

        } catch (ex: UnsupportedAudioFileException) {
            ex.printStackTrace()
        } catch (ex: LineUnavailableException) {
            ex.printStackTrace()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

    //析构函数:关闭音频读取流和数据行
    @Throws(Throwable::class)
    protected fun finalize(){
        if(sourceDataLine != null){
            sourceDataLine!!.drain()
            sourceDataLine!!.close()
        }
         if(audioStream != null)
            audioStream!!.close()
    }

    //播放音频:通过loop参数设置是否循环播放
    @Throws(InterruptedException::class)
    private fun playMusic(loop: Boolean) {
        try {
            if (loop) {
                while (true) {
                    playMusic()
                }
            } else {
                playMusic()
                //清空数据行并关闭
                sourceDataLine!!.drain()
                sourceDataLine!!.close()
                audioStream!!.close()
            }

        } catch (ex: IOException) {
            ex.printStackTrace()
        }


    }

    private fun playMusic() {
        try {
            synchronized(this) {
                run = true
            }
            //通过数据行读取音频数据流，发送到混音器;
            //数据流传输过程：AudioInputStream -> SourceDataLine;
            audioStream = AudioSystem.getAudioInputStream(File(musicPath))
            var count: Int
            val tempBuff = ByteArray(1024)

            count = audioStream!!.read(tempBuff, 0, tempBuff.size)
            while ( count!= -1) {
                synchronized(this) {
                    while (!run)
                        (this as Object).wait()
                }
                sourceDataLine!!.write(tempBuff, 0, count)
                count = audioStream!!.read(tempBuff, 0, tempBuff.size)

            }

        } catch (ex: UnsupportedAudioFileException) {
            ex.printStackTrace()
        } catch (ex: IOException) {
            ex.printStackTrace()
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }

    }


    //暂停播放音频
    private fun stopMusic() {
        synchronized(this) {
            run = false
            (this as Object).notifyAll()
        }
    }

    //继续播放音乐
    private fun continueMusic() {
        synchronized(this) {
            run = true
            (this as Object).notifyAll()
        }
    }


    //外部调用控制方法:生成音频主线程；
    fun start(loop: Boolean) {
        mainThread = Thread(Runnable {
            try {
                playMusic(loop)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })
        mainThread!!.start()
    }

    //外部调用控制方法：暂停音频线程
    fun stop() {
        Thread(Runnable { stopMusic() }).start()
    }

    //外部调用控制方法：继续音频线程
    fun continues() {
        Thread(Runnable { continueMusic() }).start()
    }

}
