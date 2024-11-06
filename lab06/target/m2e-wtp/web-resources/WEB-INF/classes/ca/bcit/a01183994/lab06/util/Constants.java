package ca.bcit.a01183994.lab06.util;

public interface Constants {
    String DOCTYPE = new StringBuilder()
        .append("<!DOCTYPE html>\n")
        .append("<!--\n")
        .append("    author: Arabela\n")
        .append("    version:    1.0\n")
        .append("    comments:   for COMP 2312 Assignment 08\n")
        .append("-->\n")
        .toString();

    String HTML_OPEN = "<html lang=\"en\">";
    String HTML_CLOSE = "</html>";
    String BODY_OPEN = "<body>";
    String BODY_CLOSE = "</body>";

    String HTML_HEAD = new StringBuilder()
        .append("<head>\n")
        .append("    <meta charset=\"UTF-8\">\n")
        .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
        .append("    <link rel=\"stylesheet\" href=\"/css/styles.css\"/>\n")
        .append("    <title>%s</title>\n")
        .append("</head>")
        .toString();
}