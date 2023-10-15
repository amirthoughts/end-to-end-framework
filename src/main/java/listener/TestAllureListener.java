package listener;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import pages.base.BasePage;


public class TestAllureListener implements ITestListener, IHookable {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Stacktrace", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHTML(String html){
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext){
        System.out.println("Inside onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", BasePage.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext){
        System.out.println("Inside onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        System.out.println("Inside onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        System.out.println("Inside onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("Inside onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        System.out.println("Screenshot captured for testcase: " + getTestMethodName(iTestResult));
        if (BasePage.getDriver() != null){
            Allure.addAttachment(getTestMethodName(iTestResult), new ByteArrayInputStream(((TakesScreenshot)BasePage.getDriver()).getScreenshotAs(OutputType.BYTES)));
            saveScreenshotPNG(BasePage.getDriver());
        }
        saveTextLog(iTestResult.getMethod().getConstructorOrMethod().getName() + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        System.out.println("Inside onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }


    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                saveScreenshotPNG(BasePage.getDriver());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
