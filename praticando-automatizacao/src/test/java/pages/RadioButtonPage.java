package pages;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class RadioButtonPage {

	SelenideElement radiobuttonOption = $(By.xpath("//*[@id=\"item-2\"]/span"));
	SelenideElement yesOption = $(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/div[2]/label"));
	SelenideElement mesageSelectYes = $(By.xpath("//p[@class='mt-3'][text()='You have selected ']"));

	public void clickRadiobuttonOption() throws InterruptedException {
		radiobuttonOption.click();
	}

	public void clickYesOption() throws InterruptedException {
		yesOption.click();
	}

	public void validateYesOption(String mensagem) throws InterruptedException {
		String mesage = mesageSelectYes.getText();
		assertEquals(mesage, mensagem);
	}

}
