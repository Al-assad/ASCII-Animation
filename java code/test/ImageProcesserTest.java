package test;

import core.ImageProcesser;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��11��17�� ����10:40:29
* @version V1.0  
* Description: ����ImageProcesser�ַ���ת����;
* 			   ͼƬĿǰֻ����.jpg��ʽ��ÿ�Ŵ�С�����ƣ����Զ���С�����100*60�ĳߴ磩
*/

public class ImageProcesserTest {
	public static void main(String [] rags){
		
	
		
		//Ĭ���ַ�����ʽ�����ַ�ת��������
		ImageProcesser processer = new ImageProcesser();
		//ImageProcesser(char[] charset);  ָ���ַ�����������
		
	
		//����ͼƬװ��Ϊ�ַ�����������
		String srcpath = "1.jpg";
		String savepath = "1.txt";
		processer.toBitmapConvert(srcpath).saveAsTxt(savepath);
		 
		
		//��������src img���ļ����е�ͼƬתΪ�ַ���������.txt�ĸ�ʽ�����ڡ�bit img�ļ����С���
		processer.batchImgFile("./src img","./bit img");   
		//Ҳ����ʹ�þ�̬���ø÷��� ImageProcesser.batchImgFile(srcfilesPath��savefilesPath);   
		

		
	}

}

