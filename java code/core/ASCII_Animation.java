package core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.concurrent.*;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016年11月16日 下午5:27:01
* @version V1.0  
* Description: 字符画动画类，eclipse控制台演示
*/

public class ASCII_Animation {
	
	final int fps;   //帧率

	//构造方法，提供帧数，输入文件路径参数，无bgm
	public ASCII_Animation(int fps,String bitImgPath){	
		this.fps = fps;
		displayAnimation(bitImgPath);
	}
	
	//构造方法，提供帧数，输入文件路基，bgm路径参数
	public ASCII_Animation(int fps,String bitImgPath,String bgmPath){	
		this.fps = fps;
		new Thread(new Runnable(){
			public void run(){
				displayAnimation(bitImgPath);
			}
		}).start();
		new Thread(new Runnable(){
			public void run(){
				new MusicPlayer(bgmPath).start(true);
			}
		}).start();
	
		
	}
	
	//开始播放动画
	private void displayAnimation(String bitImgPath){
		try{
			int size = new File(bitImgPath).listFiles().length;
			for(int i=0;i<size;i++){
				printImg(new File(bitImgPath+"/"+i+".txt"));
				TimeUnit.MILLISECONDS.sleep(1000/fps);
//				clearCMD();   //清空控制台:使用一个宏模仿eclipse控制台的清屏操作，但是效果不是很好
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}

	
	
	//从文件对象中读取字符串，并打印到控制台
	private void printImg(File imgfile){
		if(!imgfile.isFile())
			return;
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(imgfile));
			StringBuffer sb = new StringBuffer();
			String str ="";
			while((str = in.readLine())!= null)			//打印字符画
				sb.append(str+'\n');
			in.close();
			System.out.println(sb);
	
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	

	//清空控制台
	private void clearCMD(){
		try{
			Robot r = new Robot();
			r.mousePress(InputEvent.BUTTON3_MASK);    //按下鼠标右键
			r.mouseRelease(InputEvent.BUTTON3_MASK);   //释放鼠标右键
			r.keyPress(KeyEvent.VK_CONTROL);  		//按下ctrl键
			r.keyPress(KeyEvent.VK_R);			//按下R键
			r.keyRelease(KeyEvent.VK_R);		//释放R键
			r.keyRelease(KeyEvent.VK_CONTROL);		//释放ctrl键
			r.delay(20);
		}catch(AWTException ex){
			System.err.println(ex);
		}
		
	}
	


}
