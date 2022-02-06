package utils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.Selenide.sleep;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Conditional;
import com.codeborne.selenide.Selenide;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Utils {
	
	private static Robot robot;
	private static String pathFeatureJson= "src/test/resources/dataTest/cards.json";
	public static String scenarioName;

	public static void capturarScreenshot(Scenario scenario) {
		try {
			byte[] tempShot = screenshot(OutputType.BYTES);
			scenario.attach(tempShot, "image/png", scenario.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void filecreation(String phathFile, String contents) throws IOException {

		OutputStreamWriter bufferOut = new OutputStreamWriter(
				new FileOutputStream(phathFile), "UTF-8");

		bufferOut.write(contents);
		bufferOut.close();
	}

	public static void addEnvironmentAllure(ArrayList<String> infomationAplication, Conditional<WebDriver> driver)
			throws IOException {
		driver = Selenide.webdriver();
		String nameBrowser = driver.driver().browser().name;
		String versionBrowser = driver.driver().getUserAgent();
		String[] versionHandling = versionBrowser.split(" ");
		for (String version : versionHandling) {
			if (version.charAt(0) == nameBrowser.toUpperCase().charAt(0)) {
				versionBrowser = version;
			}
		}

		versionHandling = versionBrowser.split("/");
		versionBrowser = versionHandling[1];

		try {

			File env = new File("allure-results/environment.properties");
			BufferedWriter as = new BufferedWriter(new FileWriter(env));
			as.write("BROWSER = " + nameBrowser.toUpperCase() + " - Version: " + versionBrowser);
			as.newLine();
			as.write("APLICATION = " + infomationAplication.get(0).toUpperCase() + " VERSION: "
					+ infomationAplication.get(1));
			as.newLine();
			as.write("ENVIRONMENT = " + infomationAplication.get(2).toUpperCase());
			as.newLine();
			as.write("OPERATIONAL SYSTEM = " +
					System.getProperty("os.name").toUpperCase());
			as.newLine();
			as.write(("USER RESPONSIBLE FOR THE EXECUTION: = " + System.getProperty("user.name").toUpperCase()));
			as.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void validateMessage(String message) {
		$("body").shouldHave(text(message));
	}

    public static void writeRobotString(String s) throws AWTException {
    	robot=new Robot();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            robot.keyPress(Character.toUpperCase(c));
            robot.keyRelease(Character.toUpperCase(c));

            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
        robot.delay(1);
    }
    
	public static void generatePendingTest(String message) throws Exception {
		Allure.addAttachment("", message);
		Allure.description(message);
		throw new io.cucumber.java.PendingException(message);
	}
	
	public static void setAllureDetailsAboutTest(String message) {
		Allure.addAttachment("", message);
		Allure.description(message);
	}	
	
	public static String generateRadomName() {
		String[] a = { "JRR Tolkien", "Elijah Wood", "John Howe", "Ian McKellen", "Viggo Mortensen", "Ted Nasmith", "Alan Lee", "Christopher Lee" }; 
																									
		List<String> numberList = new ArrayList<String>();
		Random r = new Random();

		int i = r.nextInt(a.length);
		for (int j = 0; j < 50; j++) {
			numberList.add(Integer.toString(j));
		}
		int j = r.nextInt(numberList.size());
		return a[i]+j;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getJsonDataTest(String key) {

		JSONObject jsonValue = null;
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(pathFeatureJson));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray scenarioList = (JSONArray) jsonObject.get(key);

			Iterator<JSONObject> scenarioListIterator = scenarioList.iterator();

			while (scenarioListIterator.hasNext()) {
				jsonValue = scenarioListIterator.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonValue;
	}	
	
	public static String getJsonValueTest(String scenarioId, String inputJson) {
		JSONObject j = (JSONObject) getJsonDataTest(scenarioId);
		return  j.get(inputJson).toString();
	}
	
	public static ArrayList<String> getValueListJson(String list) {
		String[] listArrayJson;
		ArrayList<String> jsonList = new ArrayList<>();
		
		listArrayJson = list.replace("[", "").replace("]", "").replace("\"","").split(",");
		
		for (String values : listArrayJson) {
			jsonList.add(values);
		}
		
		return jsonList;
	}	
	
    public static Keys numpadKeys(String value) {
    	Keys key = null;
    	
    	switch (value) {
    	  case "0":
    	    key = Keys.NUMPAD0;
    	    break;
    	  case "1":
    		  key = Keys.NUMPAD1;
    	    break;
    	  case "2":
    		  key = Keys.NUMPAD2;
    	    break;
    	  case "3":
    		  key = Keys.NUMPAD3;
    	    break;
    	  case "4":
    		  key = Keys.NUMPAD4;
    	    break;
    	  case "5":
    		  key = Keys.NUMPAD5;
    	    break;
    	  case "6":
    		  key = Keys.NUMPAD6;
    		  break;
    	  case "7":
    		  key = Keys.NUMPAD7;
    		  break;
    	  case "8":
    		  key = Keys.NUMPAD8;
    		  break;
    	  case "9":
    		  key = Keys.NUMPAD9;
    		  break;
    	}
		return key;
    }
    
	public static String projectDir() {
		String diretorio = System.getProperty("user.dir");
		return diretorio;
	}
	
	public static String getCurrentData() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}    
    

	public static String readAccount(String file) throws IOException {
		String lastAccount = "";
		try {
			InputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					lastAccount = line;
				}
			}

			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return lastAccount;
	}
	
	
	public static void recordAccountAppend(String text, String file) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file,true));
		buffWrite.append(text + "\n");
		buffWrite.close();
	}	
			
	public static String clearLastLine(String file) throws IOException {
		String filePath = file;
		String account = readAccount(filePath);
		
		File localFile = new File(filePath);
		
		try {
			
			FileReader fr = new FileReader(localFile);
			BufferedReader br = new BufferedReader(fr);
			
			String linha = br.readLine();
			ArrayList<String> sabeList = new ArrayList<String>();
			
			while(linha != null) {

				if(linha.equals(account.toString().trim()) == false ) {
					sabeList.add(linha);
				}
				
				linha = br.readLine();
			}
			
			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(localFile, true);
			fw2.close();
			
			FileWriter fw = new FileWriter(localFile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < sabeList.size(); i++) {
	
				bw.write(sabeList.get(i));
				bw.newLine();
			}
			
			bw.close();
			fw.close();
		
	} catch (IOException ex) {
		// TODO: handle exception
	}
		return account;
	}	
	
    public static void validateMessages(List<String> labels) {
    	sleep(1000);
    	for (int i = 0; i < labels.size(); i++) {  
    		validateMessage(labels.get(i)); 		
		} 
    }	
    
}
