package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��11��16�� ����2:10:51
* @version V1.0  
* Description: ͼ����������ͼ���ļ�ת��Ϊ�ַ���
* 	
* 			   
*/

public class ImageProcesser  {
	
//	private static char[] charset = {'M','8','V','|','.',' '};   //�ַ����زļ�
	private static char[] charset = {'M','8','V','|',':','.',' '};   //�ַ����زļ�
	private String imgString = "";   //����ת������ַ���
	
	public String getImgString(){
		return imgString;
	}

	/*��ͼ���ļ�ת��Ϊ�ַ����ַ���*/
	public ImageProcesser toBitmapConvert(String imagepath){
		return toBitmapConvert(new File(imagepath));
	}
	public ImageProcesser toBitmapConvert(File imageFile){
		
		StringBuffer sb = new StringBuffer();
		if(!imageFile.exists()){    //����ȡ���ļ�������ʱ����������
			System.out.println("File is not exists!");
			System.exit(1);
		}
		Color color;
		try{
			BufferedImage buff = ImageIO.read(imageFile);   //��ͼƬ�ļ�װ����BufferedImage��
			buff = compressImage(buff);
	
			int bitmapH = buff.getHeight();
			int bitmapW = buff.getWidth();
			
			//����ɨ��ͼ������ص㣬��ȡRGBֵ��ȡ��ƽ��ֵ������charset�л�ȡ��Ӧ���ַ��زģ���װ�ص�sb��
			for(int y=0; y<bitmapH; y++){     		
				for(int x=0; x<bitmapW; x++){
					int rgb = buff.getRGB(x,y);
					color = new Color(rgb);
					
					int cvalue = (color.getRed()+color.getGreen()+color.getBlue()) / 3;
					sb.append(charset[(int)((cvalue * charset.length - 1)/255)]+" ");
				}
				sb.append("\r\n");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		imgString = sb.toString();
		return this;
	}
	
	
	/*ͼ���ļ�Ԥ����:��ͼƬѹ���� ���Ϊ 100px*/
	private  BufferedImage compressImage(BufferedImage srcImg){
		int h =  srcImg.getHeight();
		int w = srcImg.getWidth();
		if(Math.max(h,w)<=100)
			return srcImg;
		int new_H;
		int new_W;
		if(w>h){
			new_W = 100;
			new_H = 100*h/w ;
		}else{
			new_H = 100;
			new_W = 100*w/h;
		}
		BufferedImage smallImg = new BufferedImage(new_W,new_H,srcImg.getType());
		Graphics g = smallImg.getGraphics();
		g.drawImage(srcImg,0,0,new_W,new_H,null);
		g.dispose();
		return smallImg;
	}
	
	/*���ַ�������Ϊ.txt�ļ�*/
	public void saveAsTxt(String fileName){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			for(int i = 0;i<imgString.length();i++){
				out.print(imgString.charAt(i));
			}
			out.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	/*������ͼ���ļ�*/
	public static void batchImgFile(String srcfile, String tragetfile){
		
		File folder = new File(tragetfile);   //����ͼƬ���ļ���
		File srcfolder = new File(srcfile); 
		if(!folder.exists() || !folder.isDirectory())
			folder.mkdirs();
		ImageProcesser processer = new  ImageProcesser();
		File[] filelist = srcfolder.listFiles();
		
		for(int i=0;i<filelist.length;i++){
			if(!filelist[i].isFile())
				continue;
			processer.toBitmapConvert(filelist[i]);
			processer.saveAsTxt(tragetfile+"/"+(i+1)+".txt");
			System.out.println(filelist[i].getName()+" is converted!");
		}
		System.out.println("All img were converted!");
		
	}
	
	
/*	//test
	public static void main(String [] rags){
		batchImgFile("./src img","./bit img");
		
	}*/
	

}
