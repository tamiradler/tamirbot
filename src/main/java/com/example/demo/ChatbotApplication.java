package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}
	
	static List<String> anwersForEta = new ArrayList<>();
	static {
		anwersForEta.add("im still checking.");
		anwersForEta.add("few min, im investigating.");
		anwersForEta.add("suka blat, i have no idea.");
		anwersForEta.add("WOW, it is look like pizdets.");
		anwersForEta.add("still no ETA.");
	}
	
	@GetMapping("/talk")
  	public Talk hello(@RequestParam(value="talk") String talk)
	{
		String toReturn = "";
		if (talk.equals("hi") || talk.equals("Hi"))
		{
			toReturn = "hi, how are you?";
		}
		
		if (talk.contains("ETA"))
		{
			toReturn = anwersForEta.get(randomWithRange(0,anwersForEta.size()-1));
		}
		
		Talk talkToSend = new Talk();
		talkToSend.setValue(toReturn);
		talkToSend.setIsChatbot(true);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return talkToSend;
	}
	
	
	int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
}
