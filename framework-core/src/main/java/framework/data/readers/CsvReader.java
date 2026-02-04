package framework.data.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class CsvReader {

    private CsvReader() {}

    public static List<String[]> read(String resourcePath, boolean skipHeader) {
        InputStream is = ResourceReader.open(resourcePath);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            List<String[]> rows = new ArrayList<>();

            String line;
            int lineNo = 0;
            boolean headerSkipped = false;
            Integer expectedColumns = null;

            while ((line = br.readLine()) != null) {
                lineNo++;

                if (line.isBlank()) {
                    continue;
                }

                if (skipHeader && !headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] rawCols = line.split(",", -1); // -1 keeps trailing empties
                String[] cols = new String[rawCols.length];

                for (int i = 0; i < rawCols.length; i++) {
                    cols[i] = rawCols[i].trim();
                }

                if (expectedColumns == null) {
                    expectedColumns = cols.length;
                } else if (cols.length != expectedColumns) {
                    throw new IllegalArgumentException(
                            "CSV malformed at line " + lineNo +
                                    " (expected " + expectedColumns + " columns, got " + cols.length + "): " + line
                    );
                }

                rows.add(cols);
            }

            if (rows.isEmpty()) {
                throw new IllegalArgumentException("CSV has no data rows: " + resourcePath);
            }

            return rows;

        } catch (IOException e) {
            throw new IllegalStateException("Failed reading CSV: " + resourcePath, e);
        }
    }
}
