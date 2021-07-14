//Copyright © 2021 i18ncraig for jsi18n.com

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.ULocale;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class DTFUnitTestsICU69vsJava16 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/Users/crc/Desktop/JS/DTFUnitTestsJava16vsICU69AllLocales.html"), "UTF-8"));
            createHeader(pw);
            createTests(pw);
            createFooter(pw);
            System.out.println("Writing complete and writer closed");
            pw.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void createHeader(PrintWriter pw) {
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta charset=\"utf-8\">");
        pw.println("<title>Java 16 vs ICU 69 -- java.text.DateTimeFormatter vs com.ibm.icu.text.DateFormat</title>");
        pw.println("<link rel=\"stylesheet\" href=\"https://code.jquery.com/qunit/qunit-2.15.0.css\">");
        pw.println("</head>");
        pw.println("");
        pw.println("<body>");
        pw.println("<div id=\"qunit\"></div>");
        pw.println("<div id=\"qunit-fixture\"></div>");
        pw.println("<script src=\"https://code.jquery.com/qunit/qunit-2.15.0.js\"></script>");
        pw.println("<script>");
        pw.println("  QUnit.test( \"Java vs ICU All Locale Tests\", function( assert ) {");

    }

    public static void createTests(PrintWriter pw) {
        //Date d = new Date(115, 9, 27, 19, 0 ,0);
        Date d = new Date();
        pw.println("    var dated = new Date(Date.UTC(2015, 9, 28, 2, 0, 0));");
        Locale[] locDF = com.ibm.icu.text.DateFormat.getAvailableLocales();
        for (Locale loc: locDF) {
            String tag = loc.toLanguageTag();
            LocalDate date = LocalDate.of( 2021, Month.JUNE, 16 );

            String shortFormatPattern =
                    DateTimeFormatterBuilder.getLocalizedDateTimePattern(
                            FormatStyle.SHORT,
                            null,
                            IsoChronology.INSTANCE,
                            loc);
            //System.out.println(shortFormatPattern);
            DateTimeFormatter shortFormatter = DateTimeFormatter.ofPattern(shortFormatPattern, loc);
            String medFormatPattern =
                    DateTimeFormatterBuilder.getLocalizedDateTimePattern(
                            FormatStyle.MEDIUM,
                            null,
                            IsoChronology.INSTANCE,
                            loc);
            DateTimeFormatter medFormatter = DateTimeFormatter.ofPattern(medFormatPattern, loc);
            String longFormatPattern =
                    DateTimeFormatterBuilder.getLocalizedDateTimePattern(
                            FormatStyle.LONG,
                            null,
                            IsoChronology.INSTANCE,
                            loc);
            DateTimeFormatter longFormatter = DateTimeFormatter.ofPattern(longFormatPattern, loc);
            String fullFormatPattern =
                    DateTimeFormatterBuilder.getLocalizedDateTimePattern(
                            FormatStyle.FULL,
                            null,
                            IsoChronology.INSTANCE,
                            loc);
            DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern(fullFormatPattern, loc);

            //System.out.println(date.format(formatter) + ":" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.SHORT, loc).format(d));
            pw.println("    assert.equal(\"" + date.format(shortFormatter) + "\" , \"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.SHORT, loc).format(d) + "\" , \"Short date expected for " + tag + "\")");
            pw.println("    assert.equal(\"" + date.format(medFormatter) + "\" , \"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.MEDIUM, loc).format(d) + "\" , \"Medium date expected for " + tag + "\")");
            pw.println("    assert.equal(\"" + date.format(longFormatter) + "\" , \"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.LONG, loc).format(d) + "\" , \"Long date expected for " + tag + "\")");
            pw.println("    assert.equal(\"" + date.format(fullFormatter) + "\" , \"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.FULL, loc).format(d) + "\" , \"Full date expected for " + tag + "\")");
        }
    }

    public static void createFooter(PrintWriter pw) {
        pw.println("  });");
        pw.println("</script>");
        pw.println("</body>");
        pw.println("</html>");
    }
}
