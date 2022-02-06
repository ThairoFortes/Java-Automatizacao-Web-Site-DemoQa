package commons;

import java.util.Properties;

public class BrowserProperties {
	
	protected String browser ;
	protected boolean headless;
	protected String browserSize;
	protected String url;
	protected Long timeout;

	private Properties loadProperties() {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prop;
	}
	
	public String getBrowser() {
		return this.browser = loadProperties().getProperty("browser");
	}
	
	public boolean isHeadless() {
		return this.headless = Boolean.parseBoolean(loadProperties().getProperty("headless"));
	}

	public String getBrowserSize() {
		return this.browserSize = loadProperties().getProperty("browserSize");
	}

	public String getUrl() {
		return this.url = loadProperties().getProperty("url");
	}

	public Long getTimeout() {
		return this.timeout = Long.parseLong(loadProperties().getProperty("timeout"));
	}

}
