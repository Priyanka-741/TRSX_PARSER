package org.example;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

public class xmlValidator {
    public xmlValidator() {
    }

    public static boolean isXML(String input) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(input));
            builder.parse(inputSource);
            return true;
        } catch (Exception var4) {
            return false;
        }
    }
}




