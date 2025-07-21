package utils;

import annotations.TestCaseId;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).driver;

        if (driver != null) {
            String testCaseId = "UnknownTestCaseId";

            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            if (method.isAnnotationPresent(TestCaseId.class)) {
                testCaseId = method.getAnnotation(TestCaseId.class).value();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotDir = "target/surefire-reports/screenshots";
            new File(screenshotDir).mkdirs();

            Path destPath = Paths.get(screenshotDir, testCaseId + ".png");

            try {
                Files.copy(srcFile.toPath(), destPath);

                String relativePath = "./screenshots/" + testCaseId + ".png";
                String imgTag = "<br><a href='" + relativePath + "' target='_blank'>" +
                        "<img src='" + relativePath + "' alt='screenshot' height='200'/>" +
                        "</a><br>";

                Reporter.log(imgTag);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

