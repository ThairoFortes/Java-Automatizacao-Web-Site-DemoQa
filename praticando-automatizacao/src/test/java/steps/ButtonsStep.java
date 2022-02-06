package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.ButtonsPage;

public class ButtonsStep {
	

	ButtonsPage b = new ButtonsPage();

	@Quando("escolher a opção Buttons")
	public void escolherAOpçãoButtons() throws InterruptedException {
		b.clickButtonsOption();
	}

	@E("clicar no botão Double Click Me")
	public void clicarNoBotãoDoubleClickMe() {
		
	}

	@E("a mesangem {string} é apresentada")
	public void aMesangemÉApresentada(String string) {
		
	}

	@E("clicar no botão Right Click Me")
	public void clicarNoBotãoRightClickMe() {
		
	}

	@Então("clicar no botão Click Me")
	public void clicarNoBotãoClickMe() {
		
	}

}
