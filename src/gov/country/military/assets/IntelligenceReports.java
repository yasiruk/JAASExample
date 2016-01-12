package gov.country.military.assets;

import java.io.File;
import java.util.IllegalFormatCodePointException;

/**
 * Created by yasiru on 1/12/16.
 */
public class IntelligenceReports {
    private IntelligenceReports() {

    }
    static boolean reportAvailable(String reportname) {
        File report = new File(reportname);
        return report.exists();
    }
}
