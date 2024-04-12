package com.ejemplo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class MenuTest extends BaseTest {

    @Test(priority = 1)
    public void testContainerItems() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);

            for (int i = 0; i < 3; i++) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".container__cards__item")));

                List<WebElement> cards = driver.findElements(By.cssSelector(".container__cards__item"));
                WebElement titleElement = cards.get(i).findElement(By.cssSelector(".container__cards__item__informacion__title"));
                WebElement linkElement = cards.get(i).findElement(By.cssSelector(".container__cards__item__informacion__action"));

                String titleText = titleElement.getText();
                String linkText = linkElement.getAttribute("href");

                System.out.println("Tarjeta " + (i + 1) + ":");
                System.out.println("Título: " + titleText);
                System.out.println("Enlace: " + linkText);

                linkElement.click();
                Thread.sleep(2000);

                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains(linkText)) {
                    System.out.println("URL verificada correctamente. La URL contiene la palabra clave esperada.");
                } else {
                    System.out.println("Error: La URL no contiene la palabra clave esperada.");
                }

                driver.navigate().back();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Error al verificar las tarjetas: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testMenuItems() {
        try {
            String[] expectedWords = {"home", "customer", "candidate", "interesting", "partners"};

            for (int i = 0; i < 5; i++) {
                List<WebElement> menuItems = driver.findElements(By.cssSelector(".nav__menu__link"));
                menuItems.get(i).click();
                Thread.sleep(2000);

                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains(expectedWords[i])) {
                    System.out.println("Botón " + (i + 1) + " verificado correctamente. Palabra clave encontrada en la URL: " + expectedWords[i]);
                } else {
                    System.out.println("Botón " + (i + 1) + " falló la verificación. La URL no contiene la palabra clave esperada: " + expectedWords[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el menú: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testButtonActions() {
        try {
            String[] expectedKeywords = {"rankcv", "advisory"};

            WebElement buttonsSection = driver.findElement(By.cssSelector(".nav__buttons"));
            List<WebElement> buttons = buttonsSection.findElements(By.tagName("a"));
            String currentWindow = driver.getWindowHandle();

            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).click();
                Thread.sleep(2000);

                if (i == 0) {
                    Set<String> windowHandles = driver.getWindowHandles();
                    for (String window : windowHandles) {
                        if (!window.equals(currentWindow)) {
                            driver.switchTo().window(window);
                            break;
                        }
                    }
                }

                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains(expectedKeywords[i])) {
                    System.out.println("Botón " + (i + 1) + " verificado correctamente. La URL contiene la palabra clave esperada: " + expectedKeywords[i]);
                } else {
                    System.out.println("Botón " + (i + 1) + " falló la verificación. La URL no contiene la palabra clave esperada: " + expectedKeywords[i]);
                }

                if (i == 0) {
                    driver.close();
                    driver.switchTo().window(currentWindow);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al verificar las acciones de los botones: " + e.getMessage());
        }
    }


    @Test(priority = 4)
public void testCandidateLink() throws InterruptedException {
    try {
        
        WebElement candidateLink = driver.findElement(By.cssSelector("u[routerlink='/candidate'][fragment='success-stories']"));

        candidateLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("success"));

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("success")) {
            System.out.println("La URL contiene la palabra 'success'.");
        } else {
            System.out.println("Error: La URL no contiene la palabra 'success'.");
        }
    } catch (Exception e) {

        System.out.println("Error al verificar el enlace del candidato: " + e.getMessage());
    }
}

}
