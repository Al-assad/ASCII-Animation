package test;

import core.ASCII_Animation;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��11��17�� ����10:53:09
* @version V1.0  
* Description:�����ַ���������
*/

public class ASCII_AnimationTest {

	public static void main(String[] args){
		
		/*��bgm���� ASCII_Animation(int fps,String imgpath);
		 * fps:ÿ��֡����
		 * imgpath���ַ����ļ�Ŀ¼
		*/
//		new ASCII_Animation(15,"./bit img");
		
		/*��bgm���� ASCII_Animation(int fps,String imgpath��String bgmPath);
		 * fps:ÿ��֡����
		 * imgpath���ַ����ļ�Ŀ¼
		 * bgmPath����������·��
		*/
		new ASCII_Animation(15,"./bit img","./bgm/���־���.wav");
		
	}

}
