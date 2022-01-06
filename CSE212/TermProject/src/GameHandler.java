import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GameHandler {
	
	public static int leftGuess = 6;
	public static int winCount = 0;
	public int wordCount=0;
	
	public GUI myWindow = new GUI();
	public Words word;
	
	
	//Puan sistemi kalan sure ile kelime uzunlugunun carpilmasiyla olusturulur.
	//Sure kelime uzunlugunun 5 katina esittir.
	
	public GameHandler() throws IOException {
		
		ArrayList<String> tips = new ArrayList<>();
		ArrayList<String> nisc = new ArrayList<>();
		ArrayList<String> words = new ArrayList<>();
		
		HashMap<String, Integer> scores = new HashMap<>();
		
		BufferedReader csvReader = new BufferedReader(new FileReader("./words.txt"));
		String row;
		while ((row = csvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    tips.add(data[0]);
		    nisc.add(data[1]);
		    words.add(data[2]);
		}
		csvReader.close();
		
		
		JButton[] buttons = {myWindow.btnA, myWindow.btnB, myWindow.btnC, myWindow.btnD, myWindow.btnE, 
					myWindow.btnF, myWindow.btnG, myWindow.btnH, myWindow.btnI, myWindow.btnJ, myWindow.btnK, 
					myWindow.btnL, myWindow.btnM, myWindow.btnN, myWindow.btnO, myWindow.btnP, myWindow.btnQ,
					myWindow.btnR, myWindow.btnS, myWindow.btnT, myWindow.btnU, myWindow.btnV, myWindow.btnW,
					myWindow.btnX, myWindow.btnY, myWindow.btnZ};
		
		JTextField[] txtFields = {myWindow.txtLabel1, myWindow.txtLabel2, myWindow.txtLabel3, myWindow.txtLabel4,
					myWindow.txtLabel5, myWindow.txtLabel6, myWindow.txtLabel7, myWindow.txtLabel8, myWindow.txtLabel9,
					myWindow.txtLabel10};
		
		String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
							"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
							"W", "X", "Y", "Z" };
		
		
		myWindow.setSize(1024,576);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setVisible(true);
		myWindow.setLocationRelativeTo(null);
		myWindow.setResizable(false);
		
		ArrayList<JTextField> labelArray = new ArrayList<JTextField>();
		labelArray.add(myWindow.txtLabel1);
		labelArray.add(myWindow.txtLabel2);
		labelArray.add(myWindow.txtLabel3);
		labelArray.add(myWindow.txtLabel4);
		labelArray.add(myWindow.txtLabel5);
		labelArray.add(myWindow.txtLabel6);
		labelArray.add(myWindow.txtLabel7);
		labelArray.add(myWindow.txtLabel8);
		labelArray.add(myWindow.txtLabel9);
		labelArray.add(myWindow.txtLabel10);
		
		Random rand = new Random();
		wordCount = rand.nextInt(words.size());
		word = new Words(words.get(wordCount), tips.get(wordCount), nisc.get(wordCount));
		
		Timer timer = new Timer();
        
		timer.scheduleAtFixedRate(new TimerTask() {
            int time = (word.getWord().length()*10)/2;

            public void run() {
            	
                myWindow.countdownText.setText("Countdown : " + time);
                time--;
                
                
                if (time < 0) {
                    timer.cancel();
                    myWindow.statusText.setText("Status : YOU LOSE!");
                }
            }
        }, 0, 1000);
		
        for(int i=0; i < word.getWord().length(); i++) {
			txtFields[i].setVisible(true);
		}
		
		for(int i=0; i < buttons.length; i++) {
			JButton clickedBtn = buttons[i];
			String guess = letters[i];
			
			clickedBtn.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	    	boolean findChar = false;
	    	    	for(int j=0; j < word.getWord().length(); j++) {
	    				char ch = word.getWord().charAt(j);
	    				String str = String.valueOf(ch).toUpperCase();
	    				if(str.equals(guess)) {
	    					labelArray.get(j).setText(guess);
	    					findChar = true;
	    					winCount++;
	    				}
	    			}
	    	    	
	    	    	if(!findChar) {
	    	    		try {
							playSound("wrong.wav");
						} catch (MalformedURLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (UnsupportedAudioFileException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
	    	    		leftGuess--;
	    	    		System.out.println(leftGuess);
	    	    		myWindow.hangmanIcon = new ImageIcon("resource/"+ (7-leftGuess) +".png");
	    	    		myWindow.newimg = myWindow.hangmanIcon.getImage().getScaledInstance(300, 500,  java.awt.Image.SCALE_SMOOTH);
	    	    		myWindow.hangmanIcon = new ImageIcon(myWindow.newimg);
	    	    		myWindow.hangmanImg.setIcon(myWindow.hangmanIcon);
	    	    	}
	    	    	
	    	    	else {
	    	    		try {
							playSound("correct.wav");
						} catch (MalformedURLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (UnsupportedAudioFileException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (LineUnavailableException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
	    	    	}
	    	    	
	    	    	clickedBtn.setEnabled(false);
	    	    	
	    	    	if(winCount == word.getWord().length()) {
	    	    		myWindow.statusText.setText("Status : YOU WIN!");
	    	    		
	    	    		String countLeft = myWindow.countdownText.getText();
	                    String username = JOptionPane.showInputDialog(null,"Enter Player Name");
	                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	                    
	                    FileWriter fw;
	                    BufferedWriter bw;
						try {
							fw = new FileWriter("scoreboard.txt", true);
							bw = new BufferedWriter(fw);
							bw.write(username + "," + date.format(timestamp) + "," + word.getWord() + "," + countLeft);
							bw.newLine();
		                    bw.close();
		                    
		                    BufferedReader csvReader = new BufferedReader(new FileReader("./scoreboard.txt"));
		            		String row;
		                    while ((row = csvReader.readLine()) != null) {
		            		    String[] data = row.split(",");
		            		    String player = data[0];
		            		    int wordLength = data[2].length();
		            		    int timeLeft = Integer.parseInt(data[3].substring(12));
		            		    int point = wordLength*timeLeft;
		            		    scores.put(player, point);
		            		}
		            		csvReader.close();
		            		
		            		Map<String, Integer> sortedScores = sortByValue(scores, false);
		            		printMap(sortedScores);
		                    myWindow = null;
						} catch (IOException e1) {
							e1.printStackTrace();
						}    
	    	    	}
	    	    	
	    	    	if(leftGuess == 0) {
	    				myWindow.statusText.setText("Status : YOU LOST!");
	    				myWindow = null;
	    			}
	    	    }
	    	});
		}
		
		myWindow.tipText.setText("Tip : " + word.getHint());
		myWindow.notInStr.setText("Not in string : " + word.getNisc());
	}
	
	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
    {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    private static void printMap(Map<String, Integer> map)
    {	
    	System.out.println("Player\t\tScore");
        map.forEach((key, value) -> System.out.println( key + "\t\t" + value));
    }
	
	public void playSound(String soundFile) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
	    File f = new File("sounds/" + soundFile);
	    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioIn);
	    clip.start();
	}
}