package com.epam.dataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public Object[][] invalidUsername() {
        return new Object[][] {{"withSpecialSymbols!@#"}, {"with space"}};
    }

}
