import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Classs that will play music files at given paths
 * Student Name: Michias Shiferaw
 * Student Number: 300188168
 * 
 * @author Jesus Molina & Michias Shiferaw
 * @version 2.3
 * @since 11/16/21
 */
public class FilePlayer {
	List<String> allchords = Arrays.asList("re", "fa", "la","do", "mi", "sol", "si", "do-octave");
	static Queue<String> queue1 = new LinkedList<String>();// All performance with a LinkedQueue is O(1)
	static FilePlayer fp = new FilePlayer();
	/**
	 * Plays an audio clip located at the given path
	 * 
	 * @param filePath
	 *            the path to the audio clip that should be played
	 */
	public void play(String filePath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			System.out.println("Error with playing sound.");
			e.printStackTrace();
		}
	}
    public void tune(){
		Scanner sc= new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		String request;
		while (true){
			System.out.println("Type in [A] for the first multi thread\nType in [B] for the Bonus {Twinkle Twinkle Litter Star}\nType in [C] for the beginning of 'Someone like you' by Adele\nType in [D] to input your own music");
			String input = sc.next();
			request=input.toUpperCase();
			if (((request.equals("A"))|| (request.equals("B"))|| (request.equals("C")) || (request.equals("D")))){break;}
		}
		switch(request){
		case (("A")):queue1 = new LinkedList<String>(Arrays.asList("do","re","mi","fa","sol","la","si","do-octave"));break;
		case(("B")):
		queue1 = new LinkedList<String>(Arrays.asList("do", "do", "sol", "sol", "la","la","sol","fa","fa","mi","mi","re","re","do","sol","sol","fa","fa","mi","mi","re","sol","sol","fa","fa","mi","mi","re","do","do","sol","sol","la","la","sol","fa","fa","mi","mi","re","re", "do"));
		break;
		case(("C")):
			// Below is 'Someone like you' by Adele
			queue1 = new LinkedList<String>(Arrays.asList("la", "do", "mi", "do", "la", "do", "mi", "do", "la", "do","mi", "do", "la", "do", "mi", "do", "sol", "do", "mi", "do", "sol", "do", "mi", "do", "sol", "do","mi", "do", "sol", "do", "mi", "do", "fa", "do", "fa", "do"));
					break;
		case(("D")):
			System.out.println("The chords available are;\ndo, mi, sol, si, do-octave, re, fa, la\nNOTE. Have each sound separated by a space.");
			String userInput []= sc1.nextLine().split(" ");queue1= new LinkedList<String>(Arrays.asList(userInput));break;
		}
		Thread1 t1 = fp.new Thread1();t1.start();Thread2 t2 = fp.new Thread2();t2.start();
		sc.close();
		sc1.close();
    }
	public class Thread1 extends Thread{
		@Override
		public void run(){
			List<String> chords = Arrays.asList("do", "mi", "sol", "si", "do-octave");
			try {
				while (queue1.size()>0){// run O(n)
					if (!allchords.contains(queue1.peek())){
						queue1.poll();
					}else{
						if (chords.contains(queue1.peek())){
							Thread1.sleep(250);
							if (queue1.peek()=="do-octave"){duet();
							} else {	load();}
						}else{
							Thread1.sleep(250);
							Thread2.currentThread();}	
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	public class Thread2 extends Thread {
		//private Thread2 thread2;
    	@Override
    	public void run() {
			List<String> chords = Arrays.asList("re", "fa", "la", "do-octave");
        	try {
				while (queue1.size() > 0) { //Run O(n)
					if (!allchords.contains(queue1.peek())){
						queue1.poll();
					}else{
						Thread2.sleep(250);
						if (chords.contains(queue1.peek())) {
							if (queue1.peek() == "do-octave") {duet();
							}else{load();}
						}else{
							Thread2.sleep(250);
							Thread1.currentThread();}
						}}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized void duet(){
		fp.play("Sounds/do-octave.wav");
		//fp.play("Sounds/do-octave.wav");
		//System.out.println("Playing " + queue1.peek());
		System.out.println("Playing do-octave");
		try {
			Thread1.sleep(250);
			Thread2.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queue1.poll();
	}
	public synchronized void load(){
		System.out.println("Playing " + queue1.peek());
		fp.play("Sounds/"+queue1.poll() + ".wav");
	}
}


