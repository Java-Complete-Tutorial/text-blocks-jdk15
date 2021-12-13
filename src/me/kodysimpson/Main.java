package me.kodysimpson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Main {

    public static void main(String[] args) {

        //Give me a paragraph of text multiple lines long

        //Here is one way you may choose to represent multiple lines of text
        //The \n character is a newline character
        String paragraph = "I am a paragraph of text.\n" +
                           "I am a paragraph of text.\n" +
                           "I am a paragraph of text.\n" +
                           "I am a paragraph of text.\n";

        //Using text blocks, you can also represent multiple lines of text in an easy-to-read way
        //They are denoted by the triple-quotes and the string has to begin on the next line after the opening quotes
        paragraph = """
                I am a paragraph of text.
                I am a paragraph of text.
                I am a paragraph of text.
                I am a paragraph of text.
                """;
        //The magic of text blocks is that you can represent them in code how you would want them to actually be read
        System.out.println(paragraph);

        //One of the most cancerous things in Java is having to represent JSON data in a String
        //Here is one way you may choose to represent JSON data
        String json = "{\"name\":\"Kody\",\"age\":\"24\"}";


        //As you can imagine, as the JSON object gets larger, the String representation becomes more and more difficult to read and edit
        //Using text blocks, you can also represent JSON data in an easy-to-read way
        json = """
                {
                    "name": "Kody",
                    "age": "24"
                }
                """;

        validateJSON(json);

        //Let me give you one more example, instead this time with HTML text.
        //Here is one way you may choose to represent HTML data
        String html = "<html>\n" +
                      "    <head>\n" +
                      "        <title>My Title</title>\n" +
                      "    </head>\n" +
                      "    <body>\n" +
                      "        <h1>My Heading</h1>\n" +
                      "        <p>My Paragraph</p>\n" +
                      "    </body>\n" +
                      "</html>";

        //Using text blocks, you can also represent HTML data in an easy-to-read way
        html = """
                <html>
                    <head>
                        <title>My Title</title>
                    </head>
                    <body>
                        <h1>My Heading</h1>
                        <p>My Paragraph</p>
                    </body>
                </html>
                """;

        //Doesn't that just look like HTML? And make you feel good about yourself?

        //////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////

        //You get the idea, you can use text blocks to represent any kind of String data you want.
        //What I want to do now is show you some of the more intricacies of text blocks

        //If you try printing any of the Strings above, you will see that the textblock
        // puts a newline character at the end of the last line

        //To avoid that, put the closing quotes on the same line as the last line of text.
        paragraph = """
                I am a paragraph of text.
                I am a paragraph of text.
                I am a paragraph of text.
                I am a paragraph of text.
                """;
        System.out.println(paragraph);

        ////////////////////////////////////////////////////////////////////////////
        //Another thing is indentation for text blocks.
        //By default, Java is smart enough to know how to indent your Strings based off
        //of the position of the lines of text.

        //To override this, you can use the closing quotes and position it where you want the
        //indentation to begin.
        paragraph = """
                I am a paragraph of text.
                I am a paragraph of text.
                I am a paragraph of text.
                I am a paragraph of text.
          """;
        //If you have IntelliJ IDEA, you can see the indentation in the editor with the line. <3 JetBrains.
        System.out.println(paragraph);

        ////////////////////////////////////////////////////////////////////////////
        // Escape Sequences in Text Blocks
        // Remember having to escape characters in Strings? Yeah, not anymore!

        String text = "I am a \"string\" with a \n newline and a tab.";
        text = """
                I am a "string" with a
                newline and a tab   .
                """;
        System.out.println(text);

        //You do need to escape triple quotes, though. But when are you ever going to be using triple quotes anyway?
        //And whitespace on the end of a line is removed by the Text Block, so you have to escape it.
        text = """
                I am a \"""string\""" with a
                newline and a tab   .
                and some extra whitespace:                 \s
                .""";
        System.out.println(text);

        ////////////////////////////////////////////////////////////////////////////
        // Using printf() in Text Blocks
        // You can use printf() to format Strings in text blocks.
        System.out.printf("""
                I am a %s with a
                newline and a tab   .
                and some extra whitespace:                 \s
                .""", "string");

        //The new String.format() method is another way to do this.
        System.out.println("""
                \nHey, my name is %s and I am %d years old.""".formatted("John", 25));


    }

    public static void validateJSON(String json) {
        //Send the json String as an HTTP url parameter "json" to http://validate.jsontest.com/?json=%7B%22key%22:%22value%22%7D
        //You can use this to validate JSON Strings.

        //make HTTP request
        try {
            URL url = new URL("http://validate.jsontest.com/?json=" + URLEncoder.encode(json, "UTF-8"));
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            //put the response into a single string
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

            }
            in.close();

            //check to see if the JSON text return contains the field "validate": true
            if (response.toString().contains("validate\": true")) {
                System.out.println("JSON is valid!");
            }
            else {
                System.out.println("JSON is invalid!");
            }

            in.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


}
