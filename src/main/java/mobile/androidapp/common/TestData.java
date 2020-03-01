package mobile.androidapp.common;

import org.testng.annotations.DataProvider;

public class TestData {

  @DataProvider(name = "InputData")
  public Object[][] getDataforEditField() {
    return new Object[][] {{"hello"}, {"@#$%"}};
  }
}
