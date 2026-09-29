package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {
    private int nowCount = 0;

    @Override
    public boolean retry(ITestResult result){
        int maxCount = 1;
        if (nowCount < maxCount){
            nowCount++;
            System.out.println("START " + nowCount + " : " + result.getName());
            return true;
        }
        System.out.println("TEST FAILED: " + result.getName());
        return false;
    }
}
