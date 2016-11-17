package core;
/**
* @see
* @author Al_assad yulinying_1994@outlook.com
* @date 2016��11��17�� ����6:27:59
* @version V1.0  
* Description: ������Ƶ��������ֻ֧��AU��RA��WAV��
* 			   �ڲ�ʹ��JMF������¿���ʵ����Ƶ����
* 
*/
import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer {
	private String musicPath; //��Ƶ�ļ�
	private volatile boolean run = true;  //��¼��Ƶ�Ƿ񲥷�
	private Thread mainThread;   //������Ƶ�������߳�
	
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceDataLine;
	
	public MusicPlayer(String musicPath) {
		this.musicPath = musicPath;
		prefetch();
	}
	
	//����׼��
	private void prefetch(){
		try{
		//��ȡ��Ƶ������
	    audioStream = AudioSystem.getAudioInputStream(new File(musicPath));
		//��ȡ��Ƶ�ı������
		audioFormat = audioStream.getFormat();
		//��װ��Ƶ��Ϣ
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
				audioFormat,AudioSystem.NOT_SPECIFIED);
		//ʹ�ð�װ��Ƶ��Ϣ���Info�ഴ��Դ�����У��䵱��Ƶ����Դ
		sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
		
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();
		
		}catch(UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}catch(LineUnavailableException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	//��������:�ر���Ƶ��ȡ����������
	protected void finalize() throws Throwable{
		super.finalize();
		sourceDataLine.drain();
		sourceDataLine.close();
		audioStream.close();
	}
	
	//������Ƶ:ͨ��loop���������Ƿ�ѭ������
	private void playMusic(boolean loop)throws InterruptedException {
		try{
				if(loop){
					while(true){
						playMusic();
					}
				}else{
					playMusic();
					//��������в��ر�
					sourceDataLine.drain();
					sourceDataLine.close();
					audioStream.close();
				}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		
	}
	private void playMusic(){
		try{
			synchronized(this){
				run = true;
			}
			//ͨ�������ж�ȡ��Ƶ�����������͵�������;
			//������������̣�AudioInputStream -> SourceDataLine;
			audioStream = AudioSystem.getAudioInputStream(new File(musicPath));
			int count;
			byte tempBuff[] = new byte[1024];
			
				while((count = audioStream.read(tempBuff,0,tempBuff.length)) != -1){
					synchronized(this){
					while(!run)
						wait();
					}
					sourceDataLine.write(tempBuff,0,count);
							
			}

		}catch(UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	//��ͣ������Ƶ
	private void stopMusic(){
		synchronized(this){
			run = false;
			notifyAll();
		}
	}
	//������������
	private void continueMusic(){
		synchronized(this){
			 run = true;
			 notifyAll();
		}
	}
	
	
	//�ⲿ���ÿ��Ʒ���:������Ƶ���̣߳�
	public void start(boolean loop){
		mainThread = new Thread(new Runnable(){
			public void run(){
				try {
					playMusic(loop);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		mainThread.start();
	}
	
	//�ⲿ���ÿ��Ʒ�������ͣ��Ƶ�߳�
	public void stop(){
		new Thread(new Runnable(){
			public void run(){
				stopMusic();
				
			}
		}).start();
	}
	//�ⲿ���ÿ��Ʒ�����������Ƶ�߳�
	public void continues(){
		new Thread(new Runnable(){
			public void run(){
				continueMusic();
			}
		}).start();
	}
	

	
}
