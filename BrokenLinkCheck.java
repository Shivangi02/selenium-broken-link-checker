import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinkCheck {

	public static void main(String[] s) throws MalformedURLException, IOException, URISyntaxException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		String base = driver.getCurrentUrl(); // Get current base URL

		WebElement footer = driver.findElement(By.className("footer_top_agile_w3ls"));
		List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
		for (WebElement link : footerLinks) {

			String href = link.getDomAttribute("href");
//
			URI baseUri = new URI(base);
			URI resolvedUri = baseUri.resolve(href);
			URL url = resolvedUri.toURL();
//			URI uri = new URI(href);       // href is already absolute
//			URL url = uri.toURL();  

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode >= 400) {
				System.out.println("Fail" + "URL :" + url + "Response Code: " + responseCode);
			} else

				System.out.println("Pass" + "URL :" + url + "Response Code: " + responseCode);

		}

	}

}
