package com.worldfirst.test;

import com.worldfirst.test.domain.OrderBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@SpringBootApplication
public class FxplatformApplication {

	public static void main(String[] args) {

		SpringApplication.run(FxplatformApplication.class, args);

		try {
			Scanner scanner = new Scanner(new File("/Temp/init.properties"));
			String currentPriceString = scanner.nextLine();
			Float currentPrice = Float.valueOf(currentPriceString);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
