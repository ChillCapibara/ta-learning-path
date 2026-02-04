package framework.data.providers;

import framework.data.readers.CsvReader;
import framework.data.readers.JsonCaseValidator;
import framework.data.readers.JsonReader;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class LoginDataProviders {

    private LoginDataProviders() {}

    @DataProvider(name = "usersCsv")
    public static Iterator<Object[]> usersCsv() {
        List<String[]> rows = CsvReader.read("testdata/users.csv", true);

        return rows.stream()
                .map(cols -> {
                    if (cols.length != 3) {
                        throw new IllegalArgumentException("Expected 3 columns (username,password,expected) but got " + cols.length);
                    }
                    String username = cols[0];
                    String password = cols[1];
                    String expected = cols[2];

                    return new Object[]{username, password, expected};
                })
                .iterator();
    }

    @DataProvider(name = "loginJson")
    public static Iterator<Object[]> loginJson() {
        List<Map<String, Object>> cases = JsonReader.readListOfMaps("testdata/loginCases.json");

        return cases.stream()
                .map(m -> {
                    String username = JsonCaseValidator.requiredString(m, "username");
                    String password = JsonCaseValidator.requiredString(m, "password");
                    String expected = JsonCaseValidator.requiredString(m, "expected");
                    return new Object[]{username, password, expected};
                })
                .iterator();
    }
}
