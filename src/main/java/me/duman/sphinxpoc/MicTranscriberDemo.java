package me.duman.sphinxpoc;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class MicTranscriberDemo {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		
		try {
			LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
			recognizer.startRecognition(true);
			System.out.println("Listening to you...");
			System.out.println("say 'exit' if you want to stop");
	        while (true) {
	        	String utterance = recognizer.getResult().getHypothesis();
	        	if (utterance.contains("exit"))
	        		break;
	            System.out.format("Heard: %s\n", utterance);
	        }
	        recognizer.stopRecognition();
	        System.out.println("Bye bye!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
