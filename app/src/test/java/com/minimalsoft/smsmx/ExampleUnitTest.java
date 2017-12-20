package com.minimalsoft.smsmx;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void textCutterTest() throws Exception {

        String message = "Este es un mensaje muy largo. lo escribi para probar como cortar un mensaje muy largo y no pierda el sentido, ya que si se corta a la mitad, puede entenderse mal tambien si se corta en un espacio, se vera mal un mensaje comenzando asi";

        int totalSMS = (message.length() + 154 - 1) / 154;
        String[] messages = new String[totalSMS];
        String[] tokens = message.split(" ");
        int cTokens = 0;
        int cMessage = 0;

        for (int i = 0; i < totalSMS; i++)
            messages[i] = (i + 1) + "/" + totalSMS + " - ";

        while (messages[cMessage].length() < 154) {

            if ((messages[cMessage] + tokens[cTokens] + " ").length() < 154) {
                messages[cMessage] = messages[cMessage] + tokens[cTokens] + " ";
                if (cTokens < tokens.length - 1)
                    cTokens++;
                else {
                    break;
                }
            } else {
                cMessage++;
            }
        }

        for (int i = 0; i < totalSMS; i++)
            System.out.print(messages[i] + "\n");



        /*for(int i=0; i<tokens.length; i++){
            System.out.print(tokens[i] + "\n");
        }*/

        /*int i = 1;
        while (message.length() > 154) {
            messages[i] = message.substring(0, 154);
            System.out.print(i + "/" + totalSMS + " - " +messages[i] + "\n");
            message = message.substring(154);
            i++;
        }*/

    }
}