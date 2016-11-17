package test;

import core.ASCII_Animation;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016年11月17日 下午10:53:09
* @version V1.0  
* Description:测试字符画动画类
*/

public class ASCII_AnimationTest {

	public static void main(String[] args){
		
		/*无bgm动画 ASCII_Animation(int fps,String imgpath);
		 * fps:每秒帧数；
		 * imgpath：字符画文件目录
		*/
//		new ASCII_Animation(15,"./bit img");
		
		/*无bgm动画 ASCII_Animation(int fps,String imgpath，String bgmPath);
		 * fps:每秒帧数；
		 * imgpath：字符画文件目录
		 * bgmPath：背景音乐路径
		*/
		new ASCII_Animation(15,"./bit img","./bgm/极乐净土.wav");
		
	}

}
