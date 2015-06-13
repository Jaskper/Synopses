import java.io.IOException;
import java.util.Arrays;


public class Verb {
	private String[] possibleTense = {"PRES", "IMP", "FUT", "PERF", "PLUP", "FUTP"};
	private String[] possibleMood = {"IND", "SUB", "IMP", "INF", "PPL"};
	private String[] possibleVoice = {"ACTIVE", "PASSIVE"};
	private String[][] indicativeActive = {{"o", "s", "t", "mus", "tis", "nt"},
										   {"bam", "bas", "bat", "bamus", "batis", "bant"},
										   {"bo", "bis", "bit", "bimus", "bitis", "bunt"},
										   {"i", "isti", "it", "imus", "istis", "erunt"},
										   {"eram", "eras", "erat", "eramus", "eratis", "erant"},
										   {"ero", "eris", "erit", "erimus", "eritis", "erint"}};
	private String[][] indicativePassive = {{"r", "ris", "tur", "mur", "mini", "ntur"},
											{"bar", "baris", "batur", "bamur", "bamini", "bantur"},
											{"bor", "beris", "bitur", "bimur", "bimini", "buntur"},
											{"sum", "es", "est", "sumus", "estis", "sunt"},
											{"eram", "eras", "erat", "eramus", "eratis", "erant"},
											{"ero", "eris", "erit", "erimus", "eritis", "erunt"}};
	private String[][] subjunctiveActive = {{"m", "s", "t", "mus", "tis", "nt"},
											{"m", "s", "t", "mus", "tis", "nt"},
											{"-", "-", "-", "-", "-", "-"},
											{"erim", "eris", "erit", "erimus", "eritis", "erint"},
											{"issem", "isses", "isset", "issemus", "issetis", "issent"},
											{"-", "-", "-", "-", "-", "-"}};
	private String[][] subjunctivePassive = {{"r", "ris", "tur", "mur", "mini", "ntur"},
											{"r", "ris", "tur", "mur", "mini", "ntur"},
											{"-", "-", "-", "-", "-", "-"},
											{"sim", "sis", "sit", "simus", "sitis", "sunt"},
											{"essem", "esses", "esset", "essemus", "essetis", "essent"},
											{"-", "-", "-", "-", "-", "-"}};
	private String[][] alternateForms = {{"am", "es", "et", "emus", "etis", "ent"},
										 {"ar", "eris", "etur", "emur", "emini", "entur"}};
	private String[] thematicVowels = {"e", "ea", "a", "ia"};
	
	private int conjugation;
	private String[] principleParts;
	
	public Verb(String inputVerb) throws IOException{
		if(Parser.isVerb(Parser.parseVerb(inputVerb)) == true){
			principleParts = new String[4];
			for(int i=0; i<principleParts.length;i++){
				if(i != 3){
					principleParts[i] = Parser.parseVerb(inputVerb)[i+9].substring(0,Parser.parseVerb(inputVerb)[i+9].length()-1);
				}else{
					principleParts[i] = Parser.parseVerb(inputVerb)[i+9];
				}
			}
			conjugation = Integer.parseInt(Parser.parseVerb(inputVerb)[2]);
		}else{
			conjugation = 10;
		}
	}
	
	public boolean isThirdIo(){
		if(principleParts[0].substring(principleParts.length-1).equals("io") && conjugation == 3){
			return true;
		}else{
			return false;
		}
	}
	
	public int getConjugation(){
		return conjugation;
	}
	
	public String[] getPrincipleParts(){
		return principleParts;
	}
	
	public String getPrinciplePart(int inputPrinciplePart){
		if(inputPrinciplePart <= 0 || inputPrinciplePart >= 5){
			return "INVALID";
		}else{
			return principleParts[inputPrinciplePart];
		}
	}
	
	public String getPerfectBase(){
		return principleParts[2].substring(0, principleParts[2].length()-1);
	}
	
	public String getInfinitiveBase(){
		return principleParts[1].substring(0, principleParts[1].length()-2);
	}
	
	public String changeThematicVowel(){
		return isThirdIo() ? getInfinitiveBase().substring(0,getInfinitiveBase().length() - 1) + thematicVowels[conjugation] : getInfinitiveBase().substring(0,getInfinitiveBase().length() - 1) + thematicVowels[conjugation - 1];
	}
	
	public String decline(String tense, String mood, String voice, String inputPerson, String inputSingular){
		int person = Integer.parseInt(inputPerson);
		boolean singular = Boolean.parseBoolean(inputSingular);
		
		if(!(Arrays.asList(possibleTense).contains(tense) &&
		    Arrays.asList(possibleMood).contains(mood) && 
		    Arrays.asList(possibleVoice).contains(voice) &&
		    person >= 1 && person <= 3)){
				System.out.println("not valid configuration");
				return "INVALID REQUEST";
		}else{
			
			if(mood.equals("IND")){
				
				if(voice.equals("ACTIVE")){
					//INDICATIVE ACTIVE VERBS
					if(tense.equals("PRES")){
						if(singular && person == 1){
							return principleParts[0];
						} else if (conjugation == 3){
							if(singular){
								return getInfinitiveBase().substring(0, getInfinitiveBase().length()-1) + "i" + indicativeActive[0][person - 1];
							} else{
								if(person == 3 && isThirdIo()){
									return getInfinitiveBase().substring(0, getInfinitiveBase().length()-1) + "i" + "unt";
								}else if (person == 3 && !isThirdIo()){
									return getInfinitiveBase().substring(0, getInfinitiveBase().length()-1) + "unt";
								}else{
									return getInfinitiveBase().substring(0, getInfinitiveBase().length()-1) + "i" + indicativeActive[0][person + 2];
								}
							}
						} else{
							return singular ? getInfinitiveBase() + indicativeActive[0][person - 1] : getInfinitiveBase() + indicativeActive[0][person+2];
						}
						
					}else if(tense.equals("IMP")){
						if(isThirdIo()){
							return singular ? getInfinitiveBase().substring(0,getInfinitiveBase().length()-1) + "ie" + indicativeActive[1][person - 1] : getInfinitiveBase().substring(0,getInfinitiveBase().length()-1) + "ie" + indicativeActive[1][person + 2];
						}else if(conjugation == 4){
							return singular ? getInfinitiveBase() + "e" + indicativeActive[1][person - 1] : getInfinitiveBase() + "e" + indicativeActive[1][person + 2];
						}else{
							return singular ? getInfinitiveBase() + indicativeActive[1][person-1] : getInfinitiveBase() + indicativeActive[1][person+2];
						}
					}else if(tense.equals("FUT")){
						if(conjugation == 4){
							return singular ? getInfinitiveBase() + "a" + alternateForms[0][person - 1] : getInfinitiveBase() + "a" + alternateForms[0][person + 2];
						}else if(isThirdIo()){
							return singular ? getInfinitiveBase().substring(0,getInfinitiveBase().length() -1) + "i" + alternateForms[0][person - 1] : getInfinitiveBase().substring(0,getInfinitiveBase().length() -1) + "i" + alternateForms[0][person + 2];
						}else if(conjugation == 3){
							return singular ? getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + alternateForms[0][person - 1] : getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + alternateForms[0][person + 2];
						}else{
							return singular ? getInfinitiveBase() + indicativeActive[2][person - 1] : getInfinitiveBase()+indicativeActive[2][person+2];
						}
					}else if(tense.equals("PERF")){
						return singular ? getPerfectBase() + indicativeActive[3][person - 1] : getPerfectBase() + indicativeActive[3][person + 2];
					}else if(tense.equals("PLUP")){
						return singular ? getPerfectBase() + indicativeActive[4][person - 1] : getPerfectBase() + indicativeActive[4][person + 2];
					}else if(tense.equals("FUTP")){
						return singular ? getPerfectBase() + indicativeActive[5][person - 1] : getPerfectBase() + indicativeActive[5][person + 2];
					}

				} else if(voice.equals("PASSIVE")){
				
					//INDICATIVE PASSIVE
					if(tense.equals("PRES")){
						if(singular && person == 1){
							return principleParts[0] + "r";
						}else if(conjugation == 1 || conjugation == 2){
							return singular ? getInfinitiveBase() + indicativePassive[0][person - 1] : getInfinitiveBase() + indicativePassive[0][person + 2];
						}else if(conjugation == 3 && singular && person == 2){
							return getInfinitiveBase() + indicativePassive[0][1];
						}else if (conjugation == 3 && !singular && person == 3){
							if(isThirdIo()){
								return getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "iuntur";
							}else{
								return getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "untur";
							}
						}else if(conjugation == 3){
							return singular ? getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "i" + indicativePassive[0][person - 1] :getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "i" + indicativePassive[0][person + 2];
						}else if(conjugation == 4 && !singular && person == 3){
							return getInfinitiveBase() + "untur";
						}else{
							return singular? getInfinitiveBase() + indicativePassive[0][person - 1] : getInfinitiveBase() + indicativePassive[0][person + 2];
						}
							
					}else if(tense.equals("IMP")){
						if(isThirdIo()){
							return singular ? getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "ie" + indicativePassive[1][person - 1] : getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "ie" + indicativePassive[1][person + 2];
						}else if(conjugation == 4){
							return singular ? getInfinitiveBase() + "e" + indicativePassive[1][person - 1] : getInfinitiveBase() + "e" + indicativePassive[1][person + 2];
						}else{
							return singular ? getInfinitiveBase() + indicativePassive[1][person - 1] : getInfinitiveBase() + indicativePassive[1][person + 2];
						}
 					}else if(tense.equals("FUT")){
 						if(conjugation == 1 || conjugation == 2){
 							return singular ? getInfinitiveBase() + indicativePassive[2][person - 1] : getInfinitiveBase() + indicativePassive[2][person + 2];
 						}else if (conjugation == 3 && !isThirdIo() && singular && person == 1){
 							return getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "ar";
 						}else if (conjugation == 3 && !isThirdIo()){
 							return singular ? getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + alternateForms[1][person - 1] : getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + alternateForms[1][person + 2];
 						}else if(isThirdIo()){
 							return singular ? getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "i" + alternateForms[1][person - 1] : getInfinitiveBase().substring(0, getInfinitiveBase().length() - 1) + "i" + alternateForms[1][person + 2];
 						}else{
 							return singular ? getInfinitiveBase() + alternateForms[1][person - 1] : getInfinitiveBase() + alternateForms[1][person + 2];
 						}
					}else if(tense.equals("PERF")){
						if(singular){
							return principleParts[3] + " " + indicativePassive[3][person - 1];
						}else if(!singular) {
							return principleParts[3].substring(0, principleParts[3].length()-2) + "i " + indicativePassive[3][person + 2];  
						}
					}else if(tense.equals("PLUP")){
						if(singular){
							return principleParts[3] + " " + indicativePassive[4][person - 1];
						}else if(!singular) {
							return principleParts[3].substring(0, principleParts[3].length()-2) + "i " + indicativePassive[4][person + 2];  
						}
					}else if(tense.equals("FUTP")){
						if(singular){
							return principleParts[3] + " " + indicativePassive[5][person - 1];
						}else if(!singular) {
							return principleParts[3].substring(0, principleParts[3].length()-2) + "i " + indicativePassive[5][person + 2];  
						}
					}
				
				}
			
			
			} else if(mood.equals("SUB")){
				if(tense.equals("FUT") || tense.equals("FUTP")){
					return "none";
				}
				if(voice.equals("ACTIVE")){
					//SUBJUNCTIVE ACTIVE VERBS
					if(tense.equals("PRES")){
						return singular ? changeThematicVowel() + subjunctiveActive[0][person - 1] : changeThematicVowel() + subjunctiveActive[0][person + 2];
					}else if(tense.equals("IMP")){
						return singular ? principleParts[1] + subjunctiveActive[1][person - 1] : principleParts[1] + subjunctiveActive[1][person + 2];	
					}else if(tense.equals("PERF")){
						return singular ? getPerfectBase() + subjunctiveActive[3][person - 1] : getPerfectBase() + subjunctiveActive[3][person + 2];
					}else if(tense.equals("PLUP")){
						return singular ? getPerfectBase() + subjunctiveActive[4][person - 1] : getPerfectBase() + subjunctiveActive[4][person + 2];
					}

				} else if(voice.equals("PASSIVE")){
				
					//SUBJUNCTIVE PASSIVE
					if(tense.equals("PRES")){
						return singular ? changeThematicVowel() + subjunctivePassive[0][person - 1] : changeThematicVowel() + subjunctivePassive[0][person + 2];
					}else if(tense.equals("IMP")){
						return singular ? principleParts[1] + subjunctivePassive[1][person - 1] : principleParts[1] + subjunctivePassive[1][person + 2];
					}else if(tense.equals("PERF")){
						if(singular){
							return principleParts[3] + " " + subjunctivePassive[3][person - 1];
						}else{
							return principleParts[3].substring(0, principleParts[3].length() - 2) + "i " + subjunctivePassive[3][person + 2];
						}
					}else if(tense.equals("PLUP")){
						if(singular){
							return principleParts[3] + " " + subjunctivePassive[4][person - 1];
						}else{
							return principleParts[3].substring(0, principleParts[3].length() - 2) + "i " + subjunctivePassive[4][person + 2];
						}
					}
				
				}
				
				
				
			}
				
				
				
				
			}

			
			
			
			
			return "llk";
		}
		
		
}
	
