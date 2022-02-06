package commons;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class BaseTests {

	BrowserProperties properties = new BrowserProperties();

	public void openPage() {
		Configuration.timeout = properties.getTimeout();
		Configuration.browser = properties.getBrowser();
		Configuration.browserSize = properties.getBrowserSize();
		Configuration.browserCapabilities.setCapability("applicationCacheEnabled", false);
		Configuration.headless = properties.isHeadless();
		Selenide.open(properties.getUrl());
	}

}
