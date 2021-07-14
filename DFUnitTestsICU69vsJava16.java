import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.ULocale;

import java.io.*;
import java.util.Date;
import java.util.Locale;

public class DFUnitTestsICU69vsJava16 {

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            PrintWriter pw;
            try {
                pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/Users/crc/Desktop/JS/DFUnitTestsICU69vsJava16AllLocales.html"), "UTF-8"));
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
            pw.println("<title>Java 16 vs ICU 69 -- com.ibm.icu.text.DateFormat vs java.text.DateFormat</title>");
            pw.println("<link rel=\"stylesheet\" href=\"https://code.jquery.com/qunit/qunit-2.15.0.css\">");
            pw.println("</head>");
            pw.println("");
            pw.println("<body>");
            pw.println("<div id=\"qunit\"></div>");
            pw.println("<div id=\"qunit-fixture\"></div>");
            pw.println("<script src=\"https://code.jquery.com/qunit/qunit-2.15.0.js\"></script>");
            pw.println("<script>");
            pw.println("  QUnit.test( \"Java vs ICU Date-only All Locale Tests\", function( assert ) {");

        }

        public static void createTests(PrintWriter pw) {
            Date d = new Date(115, 9, 27, 19, 0 ,0);
            pw.println("    var dated = new Date(Date.UTC(2015, 9, 28, 2, 0, 0));");
            Locale[] locDF = com.ibm.icu.text.DateFormat.getAvailableLocales();
            for (Locale loc: locDF) {
                String tag = loc.toLanguageTag();
                pw.println("    assert.equal(\"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.SHORT, loc).format(d) + "\" , \"" + java.text.DateFormat.getDateInstance(DateFormat.SHORT, loc).format(d) + "\" , \"Short date expected for " + tag + "\")");
                pw.println("    assert.equal(\"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.MEDIUM, loc).format(d) + "\" , \"" + java.text.DateFormat.getDateInstance(DateFormat.MEDIUM, loc).format(d) + "\" , \"Medium date expected for " + tag + "\")");
                pw.println("    assert.equal(\"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.LONG, loc).format(d) + "\" , \"" + java.text.DateFormat.getDateInstance(DateFormat.LONG, loc).format(d) + "\" , \"Long date expected for " + tag + "\")");
                pw.println("    assert.equal(\"" + com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.FULL, loc).format(d) + "\" , \"" + java.text.DateFormat.getDateInstance(DateFormat.FULL, loc).format(d) + "\" , \"Full date expected for " + tag + "\")");
            }
        }

        public static void createFooter(PrintWriter pw) {
            pw.println("  });");
            pw.println("</script>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }
