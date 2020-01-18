package com.sentimenttwitter.www.sentimentanalysis;

import java.io.IOException;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import twitter4j.TwitterException;




@RestController
@RequestMapping("/sentiment/v1")
public class SentimentController {
	
	@RequestMapping("/helloj")
	public String sayHi() {
		return "Hi";
	}
	
	
	@RequestMapping("/sentiment")
	public String senti(@RequestParam String search) throws IOException, TwitterException {
		
		String result;
		
		SentimentAnalysisWithCount se = new SentimentAnalysisWithCount();
		
		se.setSearch(search);
		 result= se.maine();
		
		return result;
		
		
		
	}
	
	@RequestMapping("/testing")
	public String sentitest(@RequestParam String search) throws IOException, TwitterException {
		
		String result="{\"TweetsList\":[{\"Tweet1\":\"RT @PKotekar: The skeleton is falling from the congressi cupboard. Horrific and unbelievable.\\n‘Sweets were distributed in DD News office wh…\",\"Tweet2\":\"‘Sweets were distributed in DD News office when US denied visa to Narendra Modi’ https://t.co/5hAXAMimSd via @opindia_com\",\"Tweet3\":\"RT @ZeeNewsHindi: योगी ने पीएम मोदी को लेकर दिया बड़ा बयान, कहा- अगर मोदी फिर पीएम बने तो...\\n\\nhttps://t.co/3CdOKNvmpY\",\"Tweet4\":\"RT @aajtak: पीएम मोदी ने कहा,भारत 40 साल से आतंकवाद की पीड़ा सह रहा है, लेकिन अब नहीं सहेगा\\nhttps://t.co/OhbfmWBf78\",\"Tweet5\":\"RT @ZeeNewsHindi: आतंकियों को घर में घुसकर मारेंगे, पाताल में छिपे होंगे तब भी नहीं छोडूंगा: PM मोदी\\n\\nhttps://t.co/KFRbUbQd0q\",\"Tweet6\":\"RT @VictoryForNamo: The strong commitment our PM Narendra Modi has to deal with terrorism in his own words https://t.co/tbaeQ2K5J9\",\"Tweet7\":\"RT @AlkaMaverick: Train coach set on fire to recreate Godhra attack for ‘biopic on Modi’ |  https://t.co/pw7BHytTkU\",\"Tweet8\":\"RT @aajtak: पीएम मोदी ने कहा,भारत 40 साल से आतंकवाद की पीड़ा सह रहा है, लेकिन अब नहीं सहेगा\\nhttps://t.co/OhbfmWBf78\",\"Tweet9\":\"Smriti Irani hits back at Rahul Gandhi on Twitter after Cong President tried to accused PM Narendra Modi of lying o… https://t.co/x1NNpiPktP\",\"Tweet10\":\"RT @JhaSanjay: Narendra #Modi is all over billboards, TV, print, digital, everywhere. Remember, he is using our money to mock us all.\",\"Tweet11\":\"RT @DeshGujarat: PM Narendra Modi visits Dholeshwar Mahadev temple in Gandhinagar on the occasion of Maha Shivratri. He also visited mother…\",\"Tweet12\":\"RT @davidfrawleyved: India under Narendra Modi has shown great respect to Lord Shiva, the Ganga, the Kumbh Mela and Shiva's abodes like Var…\",\"Tweet13\":\"पीएम मोदी कल करेंगे इस योजना की शुरुआत, 10 करोड़ मजदूरों को मिलेगा फायदा\\r\\n\\nhttps://t.co/6PE8wzFKBT\\r\\n\\n-Shared via ZeeNews\",\"Tweet14\":\"RT @CSCegov_: VLEs to telecast launch of Pradhan Mantri Shram Yogi Maan-Dhan Yojana by Hon' PM Shri Narendra Modi tomorrow at their CSCs. h…\",\"Tweet15\":\"RT @Republic_Bharat: #ExposePakLovers | पाक में एयर स्ट्राइक के बाद और भी कार्रवाई का संकेत देते हुए पीएम मोदी बोले- ये हमारा सिद्धांत है…\",\"Tweet16\":\"यह न्यू इंडिया है, यह अपने जवानों की शहादत पर चुप नहीं रहता: संकल्प रैली में प्रधानमंत्री मोदी… https://t.co/AZaCoK38NC\",\"Tweet17\":\"@narendramodi @a_sandhan Awesome speech ...Modi, Modi, Modi  !!!\",\"Tweet18\":\"RT @AskAnshul: When Congress & Opposition question IAF Air Strike.\\n\\nLiberals: 🤗🤐😴\\n\\nNow, Modi questioned Why are Congress & its allies demor…\",\"Tweet19\":\"RT @republic: JUST WATCH THIS: \\\"Won't even spare those who have gone to the seventh circle of hell\\\", thunders PM Modi at those wanting to c…\",\"Tweet20\":\"RT @divyaspandana: You know what’s bad? Not dyslexia, people who are insensitive - that’s Modi for you.\",\"Tweet21\":\"RT @indiantweeter: Bhai modi Ji toh aag ugal rahe\",\"Tweet22\":\"RT @rishibagree: Shatrughan is praising BJP Govt\\nFence Sitters are gravitating towards NDA\\nBarkha Dutt is thrashing @shahfaesal \\nKejriwal d…\",\"Tweet23\":\"RT @INCIndia: Follow this simple 3 step guide & soon enough you’ll beat Modi for the title of India’s biggest liar. \\n\\n#ModiLies https://t.c…\",\"Tweet24\":\"RT @republic: LIVE | Will enter your territory and strike terror: PM Modi sends a warning like never before at a rally in Ahmedabad, Gujara…\",\"Tweet25\":\"RT @squintneon: Modi in Ahmedabad rally mistook Cochin as Karachi. May be he already started considering Karachi as part of India xD\\n\\nWhate…\",\"Tweet26\":\"RT @siddaramaiah: Just came across a video of our @narendramodi taking political potshots using the name of dyslexic people.\\n\\nShame on you…\",\"Tweet27\":\"RT @mpparimal: I heartily welcome our PM Shri Narendrabhai Modi to Jamnagar. PM will inaugurate new Guru Gobind Singh Hospital with 700 bed…\",\"Tweet28\":\"RT @drshamamohd: I urge PM #Modi & @AmitShah not to politicise the sacrifice of our soldiers.\\n\\nBJP must take accountability for the intelli…\",\"Tweet29\":\"@hN66531710 See ur jihadi will strike again..modi will come again..and this time u will not be lucky\",\"Tweet30\":\"RT @SunilAmbekarM: Action ke after effects . We will left no place for terrorists, separatist & Maoist in any form. Let’s make a peaceful h…\"}],\"postive_tweets\":23,\"negative_tweets\":7,\"graphPos\":[42,42,7,19,47,13,43],\"graphNeg\":[42,3,1,41,26,7,7]}";
		
		return result;
		
		
		
	}
	
	

	@RequestMapping("/lenovo")
	public String sentione(@RequestParam String name) {
		
		
		String a =name;
		
		return a;
	}
	
	
	
	

}
