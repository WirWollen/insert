package com.parse.steam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest
class SteamApplicationTests {

	@Test
	void contextLoads() {
		String input = "MP9 | Rose Iron (Minimal Wear)";
//		String input = "StatTrak™ MAG-7 | Memento (Minimal Wear)";

		// Паттерн для распарсинга строки
		String pattern = "^(StatTrak™)?\\s?(Souvenir)?\\s?(.+?)\\s\\|\\s(.+?)\\s\\((.+?)\\)$";

		Matcher matcher = Pattern.compile(pattern).matcher(input);

		// Проверяем, найдено ли совпадение
		if (matcher.find()) {
			// Получаем разделенные части
			String st = matcher.group(1);
			String souvenir = matcher.group(2);
			String mp9 = matcher.group(3);
			String roseIron = matcher.group(4);
			String minimalWear = matcher.group(5);

			// Выводим результаты
			System.out.println("Souvenir: " + souvenir);
			System.out.println("ST: " + st);
			System.out.println("MP9: " + mp9);
			System.out.println("Rose Iron: " + roseIron);
			System.out.println("Minimal Wear: " + minimalWear);
		}
	}

}
