package mobile.androidapp.common;

import org.testng.annotations.DataProvider;

public class TestData {

  @DataProvider(name = "InputData")
  public Object[][] getDataForEditField() {
    return new Object[][] {{"hello"}, {"@#$%"}};
  }
}
