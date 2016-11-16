package demo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.concurrent.*;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��11��16�� ����5:27:01
* @version V1.0  
* Description: �ַ��������࣬eclipse����̨��ʾ
*/

public class ASCII_Animation {
	
	final int fps;   //֡��
	public ASCII_Animation(){
		this(15,"./bit img");
	}
	//���췽����ͬʱ���ƴ�ӡ֡��
	public ASCII_Animation(int fps,String bitImgPath){	
		this.fps = fps;
		try{
			int size = new File(bitImgPath).listFiles().length;
			for(int i=0;i<size;i++){
				printImg(new File(bitImgPath+"/"+i+".txt"));
				TimeUnit.MILLISECONDS.sleep(1000/fps);
//				clearCMD();   //��տ���̨:ʹ��һ����ģ��eclipse����̨����������������Ч�����Ǻܺ�
			}
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}
	
	//���ļ������ж�ȡ�ַ���������ӡ������̨
	private void printImg(File imgfile){
		if(!imgfile.isFile())
			return;
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(imgfile));
			StringBuffer sb = new StringBuffer();
			String str ="";
			while((str = in.readLine())!= null)			//��ӡ�ַ���
				sb.append(str+'\n');
			in.close();
			System.out.println(sb);
			System.out.println("\n\n\n");
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	

	//��տ���̨
	private void clearCMD(){
		try{
			Robot r = new Robot();
			r.mousePress(InputEvent.BUTTON3_MASK);    //��������Ҽ�
			r.mouseRelease(InputEvent.BUTTON3_MASK);   //�ͷ�����Ҽ�
			r.keyPress(KeyEvent.VK_CONTROL);  		//����ctrl��
			r.keyPress(KeyEvent.VK_R);			//����R��
			r.keyRelease(KeyEvent.VK_R);		//�ͷ�R��
			r.keyRelease(KeyEvent.VK_CONTROL);		//�ͷ�ctrl��
			r.delay(20);
		}catch(AWTException ex){
			System.err.println(ex);
		}
		
	}
	
/*	//Test
	public static void main(String[] args){
		new ASCII_Animation(15,"./bit img");
	}*/

}
