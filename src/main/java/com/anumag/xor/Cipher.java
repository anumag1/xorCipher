package com.anumag.xor;

import java.util.Locale;

public class Cipher {
    String alphabet = "абвгдеєжзиійклмнопрстуфхцчшщьюя";//АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ ґ ї
    private String text;
    int numberA;
    int numberC;
    int[] numberT;

    static final int numberB = 5;

    String[] gammaKey;
    int[] lettersPosition;
    StringBuilder encryptedText;
    String[] encryptedBinary;

    public Cipher(String textNew, int T0, int A, int C) {
        this.text = textNew;
        text = text.replaceAll("\\s", "");
        text = text.toLowerCase(Locale.ROOT);
        encryptedText = new StringBuilder();
        numberT = new int[text.length() + 1];
        int numberM;
        numberT[0] = T0;
        numberA = A;
        numberC = C;
        numberM = (int) Math.pow(2, numberB);

        //Знаходимо номери літер в алфавіті
        lettersPosition = new int[text.length()];
        String[] binaryLettersPosition = new String[text.length()];
        gammaKey = new String[text.length()];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (text.charAt(i) == alphabet.charAt(j)) {
                    lettersPosition[i] = j + 1;

                    binaryLettersPosition[i] = Integer.toBinaryString(lettersPosition[i]);
                    while (true) {
                        if (binaryLettersPosition[i].length() < numberB) {
                            StringBuilder tempPosition = new StringBuilder(binaryLettersPosition[i]);
                            tempPosition.insert(0, "0");
                            binaryLettersPosition[i] = tempPosition.toString();
                        } else {
                            break;
                        }
                    }

                    numberT[i + 1] = (numberA * numberT[i] + numberC) % numberM;
                    StringBuilder binaryNumber = new StringBuilder(Integer.toBinaryString(numberT[i + 1]));
                    while (true) {
                        if (binaryNumber.length() < numberB) {
                            binaryNumber.insert(0, "0");
                        } else {
                            break;
                        }
                    }
                    gammaKey[i] = binaryNumber.toString();//результати, отримані за допомогою генератору псевдовипадкових чисел у двійковій системі
                }
            }
        }

        encryptedBinary = new String[text.length()];
        for (int i = 0; i < text.length(); i++) {
            StringBuilder tempEncrypted = new StringBuilder();
            for (int j = 0; j < gammaKey[i].length(); j++) {
                tempEncrypted.append(binaryLettersPosition[i].charAt(j) ^ gammaKey[i].charAt(j));
            }
            encryptedBinary[i] = tempEncrypted.toString();
        }

        for (int i = 0; i < text.length(); i++) {
            int position = Integer.parseInt(encryptedBinary[i], 2) - 1;
            if (position < 0) {
                position += 31;
            }
            if (position > 31) {
                position -= 31;
            }
            encryptedText.append(alphabet.charAt(position));
        }
    }

    public String getEncryptedText() {
        return encryptedText.toString();
    }

    public String getLettersPosition() {
        StringBuilder textDecimal = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            textDecimal.append(lettersPosition[i]).append(" ");
        }
        return textDecimal.toString();
    }

    public String generatedGamma() {
        StringBuilder gamma = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            gamma.append(Integer.parseInt(gammaKey[i], 2)).append(" ");
        }
        return gamma.toString();
    }

    public String getEncryptedDecimal() {
        StringBuilder gmKey = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            gmKey.append(Integer.parseInt(encryptedBinary[i], 2)).append(" ");
        }
        return gmKey.toString();
    }
}
