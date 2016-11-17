package test;

import core.ImageProcesser;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016年11月17日 下午10:40:29
* @version V1.0  
* Description: 测试ImageProcesser字符画转化类;
* 			   图片目前只允许.jpg格式，每张大小不限制（会自动缩小到大概100*60的尺寸）
*/

public class ImageProcesserTest {
	public static void main(String [] rags){
		
	
		
		//默认字符集方式创建字符转换器对象
		ImageProcesser processer = new ImageProcesser();
		//ImageProcesser(char[] charset);  指定字符集创建对象
		
	
		//单张图片装换为字符画，并保存
		String srcpath = "1.jpg";
		String savepath = "1.txt";
		processer.toBitmapConvert(srcpath).saveAsTxt(savepath);
		 
		
		//批量将“src img”文件夹中的图片转为字符画，并以.txt的格式保存在‘bit img文件夹中’；
		processer.batchImgFile("./src img","./bit img");   
		//也可以使用静态调用该方法 ImageProcesser.batchImgFile(srcfilesPath，savefilesPath);   
		

		
	}

}

