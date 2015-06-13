import java.io.IOException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Runner {
	
	public static String[] possibleVerbs = {"porto", "doceo", "traho", "capio", "audio", "abduco", "abicio", "abrumpo", "absolvo", "abstineo", "absumo", "accedo", "accipio", "accommodo"};
	
	
	
	
	public static void main(String[] args) throws IOException{
		JOptionPane.showMessageDialog(null, "Synopses V1.0 by Michael Bian", "Synopses", JOptionPane.INFORMATION_MESSAGE);
		showOptions();
	
	
	}
	
	public static void showOptions() throws IOException{
		String[] options = {"Study", "Quiz", "Test", "Exit"};
		int userResponse = JOptionPane.showOptionDialog(null, "What would you like to do?", "Synopses", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(userResponse == 0){
			study();
		}else if(userResponse == 1){
			quiz();
		}else if(userResponse == 2){
			System.out.println("Test");
		}else{
			System.exit(0);
		}
	}
	
	
	public static void test() throws IOException{
		System.out.println("TODO BOYS");
	}
	
	
	public static void study() throws IOException{
		boolean validWord = false;
		Verb current = new Verb("traho");
		while(!validWord){
			String inputVerb = JOptionPane.showInputDialog(null, "What latin word would you like to study?\nPlease enter the 1st principle part", "Study Mode", JOptionPane.WARNING_MESSAGE);
			current = new Verb(inputVerb);
			if(current.getConjugation() == 10){
				JOptionPane.showMessageDialog(null, "That word is not parsable\nPlease try again", "Synopses", JOptionPane.INFORMATION_MESSAGE);
			}else{
				validWord = true;
			}
		}
		
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent event) {
	            frame.dispose();
	            try {
					showOptions();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    });
		String[] columnNames = {"         ", "Indicative Active", "Indicative Passive", "Subjunctive Active", "Subjunctive Passive"};
		String[][] data = {
				{"Present", " ", " ", " ", " "},
				{"1st Singular", current.decline("PRES", "IND", "ACTIVE", "1", "true"), current.decline("PRES", "IND", "PASSIVE", "1", "true"), current.decline("PRES", "SUB", "ACTIVE", "1", "true"), current.decline("PRES","SUB","PASSIVE","1","true")},
				{"2nd Singular", current.decline("PRES", "IND", "ACTIVE", "2", "true"), current.decline("PRES", "IND", "PASSIVE", "2", "true"), current.decline("PRES", "SUB", "ACTIVE", "2", "true"), current.decline("PRES","SUB","PASSIVE","2","true")},
				{"3rd Singular", current.decline("PRES", "IND", "ACTIVE", "3", "true"), current.decline("PRES", "IND", "PASSIVE", "3", "true"), current.decline("PRES", "SUB", "ACTIVE", "3", "true"), current.decline("PRES","SUB","PASSIVE","3","true")},
				{"1st Plural", current.decline("PRES", "IND", "ACTIVE", "1", "false"), current.decline("PRES", "IND", "PASSIVE", "1", "false"), current.decline("PRES", "SUB", "ACTIVE", "1", "false"), current.decline("PRES","SUB","PASSIVE","1","false")},
				{"2nd Plural", current.decline("PRES", "IND", "ACTIVE", "2", "false"), current.decline("PRES", "IND", "PASSIVE", "2", "false"), current.decline("PRES", "SUB", "ACTIVE", "2", "false"), current.decline("PRES","SUB","PASSIVE","2","false")},
				{"3rd Plural", current.decline("PRES", "IND", "ACTIVE", "3", "false"), current.decline("PRES", "IND", "PASSIVE", "3", "false"), current.decline("PRES", "SUB", "ACTIVE", "3", "false"), current.decline("PRES","SUB","PASSIVE","3","false")},
				{"Imperfect", " ", " ", " ", " "},
				{"1st Singular", current.decline("IMP", "IND", "ACTIVE", "1", "true"), current.decline("IMP", "IND", "PASSIVE", "1", "true"), current.decline("IMP", "SUB", "ACTIVE", "1", "true"), current.decline("IMP","SUB","PASSIVE","1","true")},
				{"2nd Singular", current.decline("IMP", "IND", "ACTIVE", "2", "true"), current.decline("IMP", "IND", "PASSIVE", "2", "true"), current.decline("IMP", "SUB", "ACTIVE", "2", "true"), current.decline("IMP","SUB","PASSIVE","2","true")},
				{"3rd Singular", current.decline("IMP", "IND", "ACTIVE", "3", "true"), current.decline("IMP", "IND", "PASSIVE", "3", "true"), current.decline("IMP", "SUB", "ACTIVE", "3", "true"), current.decline("IMP","SUB","PASSIVE","3","true")},
				{"1st Plural", current.decline("IMP", "IND", "ACTIVE", "1", "false"), current.decline("IMP", "IND", "PASSIVE", "1", "false"), current.decline("IMP", "SUB", "ACTIVE", "1", "false"), current.decline("IMP","SUB","PASSIVE","1","false")},
				{"2nd Plural", current.decline("IMP", "IND", "ACTIVE", "2", "false"), current.decline("IMP", "IND", "PASSIVE", "2", "false"), current.decline("IMP", "SUB", "ACTIVE", "2", "false"), current.decline("IMP","SUB","PASSIVE","2","false")},
				{"3rd Plural", current.decline("IMP", "IND", "ACTIVE", "3", "false"), current.decline("IMP", "IND", "PASSIVE", "3", "false"), current.decline("IMP", "SUB", "ACTIVE", "3", "false"), current.decline("IMP","SUB","PASSIVE","3","false")},
				{"Future", " ", " ", " ", " "},
				{"1st Singular", current.decline("FUT", "IND", "ACTIVE", "1", "true"), current.decline("FUT", "IND", "PASSIVE", "1", "true"), "-----", "-----"},
				{"2nd Singular", current.decline("FUT", "IND", "ACTIVE", "2", "true"), current.decline("FUT", "IND", "PASSIVE", "2", "true"), "-----", "-----"},
				{"3rd Singular", current.decline("FUT", "IND", "ACTIVE", "3", "true"), current.decline("FUT", "IND", "PASSIVE", "3", "true"), "-----", "-----"},
				{"1st Plural", current.decline("FUT", "IND", "ACTIVE", "1", "false"), current.decline("FUT", "IND", "PASSIVE", "1", "false"), "-----", "-----"},
				{"2nd Plural", current.decline("FUT", "IND", "ACTIVE", "2", "false"), current.decline("FUT", "IND", "PASSIVE", "2", "false"), "-----", "-----"},
				{"3rd Plural", current.decline("FUT", "IND", "ACTIVE", "3", "false"), current.decline("FUT", "IND", "PASSIVE", "3", "false"), "-----", "-----"},
				{"Perfect", " ", " ", " ", " "},
				{"1st Singular", current.decline("PERF", "IND", "ACTIVE", "1", "true"), current.decline("PERF", "IND", "PASSIVE", "1", "true"), current.decline("PERF", "SUB", "ACTIVE", "1", "true"), current.decline("PERF","SUB","PASSIVE","1","true")},
				{"2nd Singular", current.decline("PERF", "IND", "ACTIVE", "2", "true"), current.decline("PERF", "IND", "PASSIVE", "2", "true"), current.decline("PERF", "SUB", "ACTIVE", "2", "true"), current.decline("PERF","SUB","PASSIVE","2","true")},
				{"3rd Singular", current.decline("PERF", "IND", "ACTIVE", "3", "true"), current.decline("PERF", "IND", "PASSIVE", "3", "true"), current.decline("PERF", "SUB", "ACTIVE", "3", "true"), current.decline("PERF","SUB","PASSIVE","3","true")},
				{"1st Plural", current.decline("PERF", "IND", "ACTIVE", "1", "false"), current.decline("PERF", "IND", "PASSIVE", "1", "false"), current.decline("PERF", "SUB", "ACTIVE", "1", "false"), current.decline("PERF","SUB","PASSIVE","1","false")},
				{"2nd Plural", current.decline("PERF", "IND", "ACTIVE", "2", "false"), current.decline("PERF", "IND", "PASSIVE", "2", "false"), current.decline("PERF", "SUB", "ACTIVE", "2", "false"), current.decline("PERF","SUB","PASSIVE","2","false")},
				{"3rd Plural", current.decline("PERF", "IND", "ACTIVE", "3", "false"), current.decline("PERF", "IND", "PASSIVE", "3", "false"), current.decline("PERF", "SUB", "ACTIVE", "3", "false"), current.decline("PERF","SUB","PASSIVE","3","false")},
				{"Pluperfect", " ", " ", " ", " "},
				{"1st Singular", current.decline("PLUP", "IND", "ACTIVE", "1", "true"), current.decline("PLUP", "IND", "PASSIVE", "1", "true"), current.decline("PLUP", "SUB", "ACTIVE", "1", "true"), current.decline("PLUP","SUB","PASSIVE","1","true")},
				{"2nd Singular", current.decline("PLUP", "IND", "ACTIVE", "2", "true"), current.decline("PLUP", "IND", "PASSIVE", "2", "true"), current.decline("PLUP", "SUB", "ACTIVE", "2", "true"), current.decline("PLUP","SUB","PASSIVE","2","true")},
				{"3rd Singular", current.decline("PLUP", "IND", "ACTIVE", "3", "true"), current.decline("PLUP", "IND", "PASSIVE", "3", "true"), current.decline("PLUP", "SUB", "ACTIVE", "3", "true"), current.decline("PLUP","SUB","PASSIVE","3","true")},
				{"1st Plural", current.decline("PLUP", "IND", "ACTIVE", "1", "false"), current.decline("PLUP", "IND", "PASSIVE", "1", "false"), current.decline("PLUP", "SUB", "ACTIVE", "1", "false"), current.decline("PLUP","SUB","PASSIVE","1","false")},
				{"2nd Plural", current.decline("PLUP", "IND", "ACTIVE", "2", "false"), current.decline("PLUP", "IND", "PASSIVE", "2", "false"), current.decline("PLUP", "SUB", "ACTIVE", "2", "false"), current.decline("PLUP","SUB","PASSIVE","2","false")},
				{"3rd Plural", current.decline("PLUP", "IND", "ACTIVE", "3", "false"), current.decline("PLUP", "IND", "PASSIVE", "3", "false"), current.decline("PLUP", "SUB", "ACTIVE", "3", "false"), current.decline("PLUP","SUB","PASSIVE","3","false")},
				{"Future Perfect", " ", " ", " ", " "},
				{"1st Singular", current.decline("FUTP", "IND", "ACTIVE", "1", "true"), current.decline("FUTP", "IND", "PASSIVE", "1", "true"), "-----", "-----"},
				{"2nd Singular", current.decline("FUTP", "IND", "ACTIVE", "2", "true"), current.decline("FUTP", "IND", "PASSIVE", "2", "true"), "-----", "-----"},
				{"3rd Singular", current.decline("FUTP", "IND", "ACTIVE", "3", "true"), current.decline("FUTP", "IND", "PASSIVE", "3", "true"), "-----", "-----"},
				{"1st Plural", current.decline("FUTP", "IND", "ACTIVE", "1", "false"), current.decline("FUTP", "IND", "PASSIVE", "1", "false"), "-----", "-----"},
				{"2nd Plural", current.decline("FUTP", "IND", "ACTIVE", "2", "false"), current.decline("FUTP", "IND", "PASSIVE", "2", "false"), "-----", "-----"},
				{"3rd Plural", current.decline("FUTP", "IND", "ACTIVE", "3", "false"), current.decline("FUTP", "IND", "PASSIVE", "3", "false"), "-----", "-----"}
		};
		JTable table = new JTable(data,  columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1500, 1000);
		frame.setVisible(true);

	}
	
	
	public static void quiz() throws IOException{
		int difficulty;
		int length = 0;
		int correct = 0;
		int currentNumber = 0;
		
		String[] options = {"Indicative Actives Only", "All Indicatives", "Indicatives and Subjunctives"};
		String initialSelection = "Indicative and Subjunctives";
		String selected = (String) JOptionPane.showInputDialog(null, "What would you like to be quizzed on?", "Quiz Mode", JOptionPane.QUESTION_MESSAGE, null, options, initialSelection);
		if(selected.equals("Indicative Actives Only")){
			difficulty = 0;
		}else if (selected.equals("All Indicatives")){
			difficulty = 1;
		}else{
			difficulty = 2;
		}
		boolean valid = false;
		
		while(!valid){
			String numberOfProblems = JOptionPane.showInputDialog(null, "Number of Problems (1-100)", "Quiz Mode", JOptionPane.WARNING_MESSAGE);
			if(isParsable(numberOfProblems)){
				int requestedLength = Integer.parseInt(numberOfProblems);
				if(requestedLength <=0 || requestedLength >= 101){
					JOptionPane.showMessageDialog(null, "Number is not between 0 and 100. Please try again");
					valid = false;
				}else{
					length = requestedLength;
					valid = true;
				}
			}else{
				JOptionPane.showMessageDialog(null, "This is not a number. Please try again");
				valid = false;
			}
		}
		JOptionPane.showMessageDialog(null, "You are being given " + length + " random problems\nIf there is no answer, type \"none\"");
		for(int i = length; i > 0; i--){
			int numVerbs = possibleVerbs.length;
			Verb randomVerb = new Verb(possibleVerbs[(int) (Math.random()*numVerbs)]);
			int[] currentParams = generateRandomArguments(difficulty);
			String[] readableParams = formatReadableAruments(currentParams);
			String[] formatedParams = formatRandomAruments(currentParams);
			String params ="";
			for(int j = 0; j < readableParams.length; j++){
				params += readableParams[j] + " ";
			}
			String pp = "";
			for(int j = 0; j<randomVerb.getPrincipleParts().length; j++){
				pp += randomVerb.getPrincipleParts()[j] + " ";
			}
		
			String userResponse = JOptionPane.showInputDialog(null,params + "\n" + pp , "Study Mode", JOptionPane.WARNING_MESSAGE);
			String correctResponse = randomVerb.decline(formatedParams[0], formatedParams[1], formatedParams[2], formatedParams[3], formatedParams[4]);
			if(userResponse.equals(correctResponse)){
				correct++;
				currentNumber++;
				JOptionPane.showMessageDialog(null, "Correct!\nYou are " + correct + " for " + currentNumber);
			}else{
				currentNumber++;
				JOptionPane.showMessageDialog(null, "Sorry! The correct response was: " + correctResponse + "\nYou are " + correct + " for " + currentNumber);
			}
		}
		JOptionPane.showMessageDialog(null, "You got " + correct + " out of " + currentNumber + "\n Nice Job! Try your skills on a test next!");
		showOptions();
		
		
	}
	
	public static boolean isParsable(String input){
		boolean parsable = true;
		try{
			Integer.parseInt(input);
		}catch(NumberFormatException e){
			parsable = false;
		}
		return parsable;
	}
	
	public static int[] generateRandomArguments(int difficulty){
		int number = (int) (Math.random()*2 + 1);
		int tense = (int) (Math.random()*6 + 1);
		int mood = (int) (Math.random()*2 + 1);
		int voice = (int) (Math.random()*2 + 1);
		int person = (int) (Math.random()*3 + 1);
		
		if(difficulty == 0){
			int[] returnArray = {tense, 1, 1, person, number};
			return returnArray;
		}else if(difficulty == 1){
			int[] returnArray = {tense, 1, voice, person, number};
			return returnArray;
		}else{
			int[] returnArray = {tense, mood, voice, person, number};
			return returnArray;
		}
	}
	
	public static String[] formatRandomAruments(int[] randomArguments){
		String[] returnArray = new String[5];
		if(randomArguments[0] == 1){
			returnArray[0] = "PRES";
		}else if(randomArguments[0] == 2){
			returnArray[0] = "IMP";
		}else if(randomArguments[0] == 3){
			returnArray[0] = "FUT";
		}else if(randomArguments[0] == 4){
			returnArray[0] = "PERF";
		}else if(randomArguments[0] == 5){
			returnArray[0] = "PLUP";
		}else{
			returnArray[0] = "FUTP";
		}
		
		returnArray[1] = randomArguments[1] == 1 ? "IND" : "SUB";
		returnArray[2] = randomArguments[2] == 1 ? "ACTIVE" : "PASSIVE";
		returnArray[3] = "" + randomArguments[3];
		returnArray[4] = randomArguments[4] == 1 ? "true" : "false";
		return returnArray;
	}
	
	public static String[] formatReadableAruments(int[] randomArguments){
		String[] returnArray = new String[5];
		if(randomArguments[0] == 1){
			returnArray[0] = "Present";
		}else if(randomArguments[0] == 2){
			returnArray[0] = "Imperfect";
		}else if(randomArguments[0] == 3){
			returnArray[0] = "Future";
		}else if(randomArguments[0] == 4){
			returnArray[0] = "Perfect";
		}else if(randomArguments[0] == 5){
			returnArray[0] = "Plueperfect";
		}else{
			returnArray[0] = "Future Perfect";
		}
		
		returnArray[1] = randomArguments[1] == 1 ? "Indicative" : "Subjunctive";
		returnArray[2] = randomArguments[2] == 1 ? "Active" : "Passive";
		returnArray[3] = "" + randomArguments[3] + "-Person";
		returnArray[4] = randomArguments[4] == 1 ? "Singular" : "Plural";
		return returnArray;
	}
	
	
}
