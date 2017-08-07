package com.yangfeiram.calculator;

import com.yangfeiram.calculator.operations.Calculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@SpringBootApplication
@Slf4j
public class CalculatorApplication {

    /**
     * Entrance of the program
     */
    public static void main(String[] args) {
        CalculatorApplication calculatorApplication = new CalculatorApplication();
        calculatorApplication.run();
    }

    /**
     * On running thread
     */
    private void run() {
        Calculator calculator = new Calculator();
        System.out.println("Input your expression or use Ctrl+C to exit");
        while (!Thread.interrupted()) {
//            log.info("Start an new command line input");
            InputStreamReader fileData;
            try {
                fileData = new InputStreamReader(System.in, "utf-8");
                BufferedReader br = new BufferedReader(fileData);
                String read = null;
                String result = null;
                System.out.print("please input: ");
                read = br.readLine();
                result = calculator.getCurrentResultWithExpression(read);
                System.out.println(result);
            } catch (UnsupportedEncodingException e) {
                System.out.println("input encoding is not utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
