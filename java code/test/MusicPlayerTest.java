package test;

import java.util.concurrent.TimeUnit;

import core.MusicPlayer;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��11��17�� ����10:28:54
* @version V1.0  
* Description: ����MusicPlayer��
*/

public class MusicPlayerTest {
	public static void main(String[] args) throws InterruptedException{

		MusicPlayer player = new MusicPlayer("bgm/1.wav");   //�������ֲ�����
		
		player.start(true);										//�Կ�ʼ��ѭ������ʽ���ţ�player(false)Ϊ��ѭ������
		
		TimeUnit.SECONDS.sleep(5);
		
		player.stop();						//��ͣ������Ƶ
		
		TimeUnit.SECONDS.sleep(4);
		
		player.continues();				//������ʼ������Ƶ
		
	}

}
