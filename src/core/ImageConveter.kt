package core

import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.Color;
import java.io.FileWriter
import java.io.BufferedWriter
import java.io.PrintWriter




/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2017/10/11 20:08
 * Description: 图像转化为字符画字符串文件
 */
class ImageConveter() {
    var charset: CharArray = charArrayOf('M', '8', 'V', '|', ':', '.', ' ')   //字符画素材集
    var imgString = ""   //储存转化后的字符串
        private set

    init {
    }

    constructor(charset: CharArray) : this() {
        this.charset = charset
    }

    /*将图形文件转化为字符画字符串*/
    fun toBitmapConvert(imagepath: String): ImageConveter {
        return toBitmapConvert(File(imagepath))
    }

    fun toBitmapConvert(imageFile: File): ImageConveter {
        val sb = StringBuffer()
        if (!imageFile.exists()) {
            println("File is not exists!")
            System.exit(1)
        }
        var color: Color
        try {
            var buff = ImageIO.read(imageFile)
            buff = compressImage(buff)

            val bitmapH = buff.height
            val bitmapW = buff.width

            //逐行扫描图像的像素点，取RGB的加权灰度值，并将其传输
            for (y in 0..bitmapH - 1) {
                for (x in 0..bitmapW - 1) {
                    val rgb = buff.getRGB(x, y)
                    color = Color(rgb)
                    val cvalue =(color.getRed()*0.3 + color.getGreen()*0.59 + color.getBlue()*0.11).toInt()
                    sb.append(charset[(cvalue * charset.size - 1) / 255] + " ")  //
                }
                sb.append("\r\n")
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        imgString = sb.toString()
        return this
    }

    /*图像文件预处理:将图片压缩到 最长边为 100px*/
    private fun compressImage(srcImg: BufferedImage): BufferedImage {
        val limit = 100
        val h = srcImg.height
        val w = srcImg.width
        if (Math.max(h, w) <= limit)
            return srcImg
        val new_H: Int
        val new_W: Int
        if (w > h) {
            new_W = limit
            new_H = limit * h / w
        } else {
            new_H = limit
            new_W = limit * w / h
        }
        val smallImg = BufferedImage(new_W, new_H, srcImg.type)
        val g = smallImg.graphics
        g.drawImage(srcImg, 0, 0, new_W, new_H, null)
        g.dispose()
        return smallImg
    }

    /*将字符串保存为.txt文件*/
    fun saveAsTxt(fileName: String) {
        try {
            val out = PrintWriter(BufferedWriter(FileWriter(fileName)))
            for (i in 0..imgString.length - 1) {
                out.print(imgString[i])
            }
            out.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    /*批处理图像文件*/
    companion object {
        fun batchImgFile(srcfile: String, tragetfile: String) {

            val folder = File(tragetfile)   //生成图片的文件夹
            val srcfolder = File(srcfile)
            if (!folder.exists() || !folder.isDirectory)
                folder.mkdirs()
            val processer = ImageConveter()
            val filelist = srcfolder.listFiles()
            filelist.sort()

            for (i in filelist!!.indices) {
                if (!filelist[i].isFile)
                    continue
                processer.toBitmapConvert(filelist[i])
                processer.saveAsTxt(tragetfile + "/" + (i + 1) + ".txt")
                println(filelist[i].name + " is converted!")
            }
            println("All img were converted!")

        }
    }



}