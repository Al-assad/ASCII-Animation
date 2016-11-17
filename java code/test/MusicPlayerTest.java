package test;

import java.util.concurrent.TimeUnit;

import core.MusicPlayer;

/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016年11月17日 下午10:28:54
* @version V1.0  
* Description: 测试MusicPlayer类
*/

public class MusicPlayerTest {
	public static void main(String[] args) throws InterruptedException{

		MusicPlayer player = new MusicPlayer("bgm/1.wav");   //创建音乐播放器
		
		player.start(true);										//以开始以循环的形式播放，player(false)为不循环播放
		
		TimeUnit.SECONDS.sleep(5);
		
		player.stop();						//暂停播放音频
		
		TimeUnit.SECONDS.sleep(4);
		
		player.continues();				//继续开始播放音频
		
	}

}
