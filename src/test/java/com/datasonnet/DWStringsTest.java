package com.datasonnet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DWStringsTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String lib = "DW" +".";
    private final String pack = "Strings";

    @Test
    void testDWStrings_appendIfMissing() {
        Mapper mapper = new Mapper(lib + pack + ".appendIfMissing(\"abc\", \"xyz\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("abcxyz", value);

        mapper = new Mapper(lib +  pack + ".appendIfMissing(\"abcxyz\", \"xyz\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("abcxyz", value);

        mapper = new Mapper(lib +  pack + ".appendIfMissing(null, \"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib +  pack + ".appendIfMissing(\"xyza\", \"xyz\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xyzaxyz", value);

        mapper = new Mapper(lib +  pack + ".appendIfMissing(\"\", \"xyz\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xyz", value);
    }

    @Test
    void testDWStrings_camelize() {
        Mapper mapper = new Mapper(lib + pack + ".camelize(\"customer_first_name\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customerFirstName", value);

        mapper = new Mapper(lib + pack + ".camelize(\"_customer_first_name\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customerFirstName", value);

        mapper = new Mapper(lib + pack + ".camelize(\"_______customer_first_name\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customerFirstName", value);

        mapper = new Mapper(lib + pack + ".camelize(null)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);
    }

    @Test
    void testDWStrings_capitalize() {
        Mapper mapper = new Mapper(lib + pack + ".capitalize(\"customer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("Customer", value);

        mapper = new Mapper(lib + pack + ".capitalize(\"customer_first_name\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("Customer First Name", value);

        mapper = new Mapper(lib + pack + ".capitalize(\"customer NAME\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("Customer Name", value);

        mapper = new Mapper(lib + pack + ".capitalize(\"customerName\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("Customer Name", value);

        mapper = new Mapper(lib + pack + ".capitalize(null)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);
    }

    @Test
    void testDWStrings_charCode() {
        Mapper mapper = new Mapper(lib + pack + ".charCode(\"Master\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("77", value);

        mapper = new Mapper(lib + pack + ".charCode(\"M\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("77", value);
    }

    @Test
    void testDWStrings_charCodeAt() {
        Mapper mapper = new Mapper(lib + pack + ".charCodeAt(\"charCodeAt\", 4)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("67", value);

        mapper = new Mapper(lib + pack + ".charCodeAt(\"charCodeAt\", 8)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("65", value);
    }

    @Test
    void testDW_dasherize() {
        Mapper mapper = new Mapper(lib + pack + ".dasherize(\"customer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer", value);

        mapper = new Mapper(lib + pack + ".dasherize(\"customer_first_name\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer-first-name", value);

        mapper = new Mapper(lib + pack + ".dasherize(\"customer NAME\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer-name", value);

        mapper = new Mapper(lib + pack + ".dasherize(\"customerName\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer-name", value);

        mapper = new Mapper(lib + pack + ".dasherize(null)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);
    }

    @Test
    void testDWStrings_fromCharCode() {
        Mapper mapper = new Mapper(lib + pack + ".fromCharCode(67)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("C", value);

        mapper = new Mapper(lib + pack + ".fromCharCode(65)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("A", value);
    }

    @Test
    void testDWStrings_isAlpha() {
        Mapper mapper = new Mapper(lib + pack + ".isAlpha(\"sdfvxer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isAlpha(\"ecvt4\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isAlpha(true)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isAlpha(45)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);
    }

    @Test
    void testDWStrings_isAlphanumeric() {
        Mapper mapper = new Mapper(lib + pack + ".isAlphanumeric(\"sdfvxer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isAlphanumeric(\"ecvt4\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isAlphanumeric(true)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isAlphanumeric(45)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);
    }

    @Test
    void testDWStrings_isLowerCase() {
        Mapper mapper = new Mapper(lib + pack + ".isLowerCase(\"sdfvxer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isLowerCase(\"ecvt4\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isLowerCase(\"eCvt\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isLowerCase(true)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isLowerCase(45)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);
    }

    @Test
    void testDWStrings_isNumeric() {
        Mapper mapper = new Mapper(lib + pack + ".isNumeric(\"sdfvxer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isNumeric(\"5334\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isNumeric(100)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);
    }

    @Test
    void testDW_isUpperCase() {
        Mapper mapper = new Mapper(lib + pack + ".isUpperCase(\"SDFVXER\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isUpperCase(\"ECVT4\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isUpperCase(\"EcVT\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isUpperCase(true)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isUpperCase(45)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);
    }

    @Test
    void testDWStrings_isWhitespace() {
        Mapper mapper = new Mapper(lib + pack + ".isWhitespace(null)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isWhitespace(\"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isWhitespace(\"       \")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true", value);

        mapper = new Mapper(lib + pack + ".isWhitespace(\"   abc    \")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isWhitespace(true)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);

        mapper = new Mapper(lib + pack + ".isWhitespace(45)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("false", value);
    }

    @Test
    void testDWStrings_leftPad() {
        Mapper mapper = new Mapper(lib + pack + ".leftPad(null,3)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".leftPad(\"\",3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("   ", value);

        mapper = new Mapper(lib + pack + ".leftPad(\"bat\",5)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("  bat", value);

        mapper = new Mapper(lib + pack + ".leftPad(\"bat\",3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("bat", value);

        mapper = new Mapper(lib + pack + ".leftPad(\"bat\",-1)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("bat", value);

        mapper = new Mapper(lib + pack + ".leftPad(45,3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals(" 45", value);

        mapper = new Mapper(lib + pack + ".leftPad(true,10)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("      true", value);
    }

    @Test
    void testDWStrings_ordinalize() {
        Mapper mapper = new Mapper(lib + pack + ".ordinalize(1)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("1st", value);

        mapper = new Mapper(lib + pack + ".ordinalize(2)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("2nd", value);

        mapper = new Mapper(lib + pack + ".ordinalize(3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("3rd", value);

        mapper = new Mapper(lib + pack + ".ordinalize(111)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("111th", value);

        mapper = new Mapper(lib + pack + ".ordinalize(22)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("22nd", value);

        mapper = new Mapper(lib + pack + ".ordinalize(null)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);
    }

    @Test
    void testDWStrings_pluralize() {
        Mapper mapper = new Mapper(lib + pack + ".pluralize(null)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".pluralize(\"help\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("helps", value);

        mapper = new Mapper(lib + pack + ".pluralize(\"box\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("boxes", value);

        mapper = new Mapper(lib + pack + ".pluralize(\"monday\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("mondays", value);

        mapper = new Mapper(lib + pack + ".pluralize(\"mondy\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("mondies", value);
    }


    @Test
    void testDWStrings_prependIfMissing() {
        Mapper mapper = new Mapper(lib + pack + ".prependIfMissing(\"abc\", \"xyz\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xyzabc", value);

        mapper = new Mapper(lib +  pack + ".prependIfMissing(\"xyzabc\", \"xyz\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xyzabc", value);

        mapper = new Mapper(lib +  pack + ".prependIfMissing(null, \"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib +  pack + ".prependIfMissing(\"axyz\", \"xyz\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xyzaxyz", value);

        mapper = new Mapper(lib +  pack + ".prependIfMissing(\"\", \"xyz\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xyz", value);
    }

    @Test
    void testDWStrings_repeat() {
        Mapper mapper = new Mapper(lib + pack + ".repeat(\"e\", 0)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".repeat(\"e\", 3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("eee", value);

        mapper = new Mapper(lib + pack + ".repeat(\"e\", -2)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);
    }


    @Test
    void testDWStrings_rightPad() {
        Mapper mapper = new Mapper(lib + pack + ".rightPad(null,3)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".rightPad(\"\",3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("   ", value);

        mapper = new Mapper(lib + pack + ".rightPad(\"bat\",5)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("bat  ", value);

        mapper = new Mapper(lib + pack + ".rightPad(\"bat\",3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("bat", value);

        mapper = new Mapper(lib + pack + ".rightPad(\"bat\",-1)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("bat", value);

        mapper = new Mapper(lib + pack + ".rightPad(45,3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("45 ", value);

        mapper = new Mapper(lib + pack + ".rightPad(true,10)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("true      ", value);
    }


    @Test
    void testDWStrings_singularize() {
        Mapper mapper = new Mapper(lib + pack + ".singularize(null)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".singularize(\"helps\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("help", value);

        mapper = new Mapper(lib + pack + ".singularize(\"boxes\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("box", value);

        mapper = new Mapper(lib + pack + ".singularize(\"mondays\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("monday", value);

        mapper = new Mapper(lib + pack + ".singularize(\"mondies\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("mondy", value);
    }

    @Test
    void testDWStrings_substringAfter() {
        Mapper mapper = new Mapper(lib + pack + ".substringAfter(null, \"'\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".substringAfter(\"\", \"-\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringAfter(\"abc\", \"a\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("bc", value);

        mapper = new Mapper(lib + pack + ".substringAfter(\"abc\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("c", value);

        mapper = new Mapper(lib + pack + ".substringAfter(\"abcba\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("cba", value);

        mapper = new Mapper(lib + pack + ".substringAfter(\"abc\", \"d\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringAfter(\"abc\", \"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("abc", value);

    }

    @Test
    void testDWStrings_substringAfterLast() {
        Mapper mapper = new Mapper(lib + pack + ".substringAfterLast(null, \"'\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".substringAfterLast(\"\", \"-\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringAfterLast(\"abcaxy\", \"a\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("xy", value);

        mapper = new Mapper(lib + pack + ".substringAfterLast(\"abc\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("c", value);

        mapper = new Mapper(lib + pack + ".substringAfterLast(\"abcba\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("a", value);

        mapper = new Mapper(lib + pack + ".substringAfterLast(\"abc\", \"d\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringAfterLast(\"abc\", \"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

    }

    @Test
    void testDWStrings_substringBefore() {
        Mapper mapper = new Mapper(lib + pack + ".substringBefore(null, \"'\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".substringBefore(\"\", \"-\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringBefore(\"abc\", \"a\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value); // possible bug with DW

        mapper = new Mapper(lib + pack + ".substringBefore(\"abc\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("a", value);

        mapper = new Mapper(lib + pack + ".substringBefore(\"abcba\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("a", value);

        mapper = new Mapper(lib + pack + ".substringBefore(\"abc\", \"d\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringBefore(\"abc\", \"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);
    }

    @Test
    void testDWStrings_substringBeforeLast() {
        Mapper mapper = new Mapper(lib + pack + ".substringBeforeLast(null, \"'\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".substringBeforeLast(\"\", \"-\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringBeforeLast(\"abc\", \"a\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value); // possible bug with DW

        mapper = new Mapper(lib + pack + ".substringBeforeLast(\"abc\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("a", value);

        mapper = new Mapper(lib + pack + ".substringBeforeLast(\"abcba\", \"b\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("abc", value);

        mapper = new Mapper(lib + pack + ".substringBeforeLast(\"abc\", \"d\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);

        mapper = new Mapper(lib + pack + ".substringBeforeLast(\"abc\", \"\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("abc", value);
    }

    @Test
    void testDWStrings_underscore() {
        Mapper mapper = new Mapper(lib + pack + ".underscore(\"customer\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer", value);

        mapper = new Mapper(lib + pack + ".underscore(\"customer-first-name\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer_first_name", value);

        mapper = new Mapper(lib + pack + ".underscore(\"customer NAME\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer_name", value);

        mapper = new Mapper(lib + pack + ".underscore(\"customerName\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("customer_name", value);

        mapper = new Mapper(lib + pack + ".underscore(null)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);
    }

    @Test
    void testDWStrings_unwrap() {
        Mapper mapper = new Mapper(lib + pack + ".unwrap(null, \"\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".unwrap(\"'abc'\", \"'\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("abc", value);

        mapper = new Mapper(lib + pack + ".unwrap(\"AABabcBAA\", \"A\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("ABabcBA", value);

        mapper = new Mapper(lib + pack + ".unwrap(\"A\", \"#\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("A", value);

        mapper = new Mapper(lib + pack + ".unwrap(\"A#\", \"#\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("#A", value);
    }

    @Test
    void testDWStrings_withMaxSize() {
        Mapper mapper = new Mapper(lib + pack + ".withMaxSize(null, 10)", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".withMaxSize(\"123\", 10)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("123", value);

        mapper = new Mapper(lib + pack + ".withMaxSize(\"123\", 3)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("123", value);

        mapper = new Mapper(lib + pack + ".withMaxSize(\"123\", 2)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("12", value);

        mapper = new Mapper(lib + pack + ".withMaxSize(\"\", 0)", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("", value);
    }

    @Test
    void testDWStrings_wrapIfMissing() {
        Mapper mapper = new Mapper(lib + pack + ".wrapIfMissing(null, \"'\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".wrapIfMissing(\"abc\", \"'\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("'abc'", value);

        mapper = new Mapper(lib + pack + ".wrapIfMissing(\"'abc'\", \"'\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("'abc'", value);

        mapper = new Mapper(lib + pack + ".wrapIfMissing(\"'abc\", \"'\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("'abc'", value);
    }

    @Test
    void testDWStrings_wrapWith() {
        Mapper mapper = new Mapper(lib + pack + ".wrapWith(null, \"'\")", new ArrayList<>(), true);
        String value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("null", value);

        mapper = new Mapper(lib + pack + ".wrapWith(\"abc\", \"'\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("'abc'", value);

        mapper = new Mapper(lib + pack + ".wrapWith(\"'abc\", \"'\")", new ArrayList<>(), true);
        value = mapper.transform("{}").replaceAll("\"", "");
        assertEquals("''abc'", value);
    }

}