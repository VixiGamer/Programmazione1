package cronologia;

public class Main {

	public static void main(String[] args) {
		
		Browser browser = new Browser();
		
		browser.visitPage("www.apple.it");
		browser.visitPage("www.steam.it");
		browser.visitPage("www.github.it");
		
		browser.printHistory();
		
		browser.goBack();
		browser.goBack();
		
		browser.printHistory();


	}

}
