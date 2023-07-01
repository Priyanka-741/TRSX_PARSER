package org.example;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends xmlValidator {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide input file names as arguments");
        }
        String outputDirectoryName = "output";
        Path outputDirectory = Paths.get(outputDirectoryName);

        if (!Files.exists(outputDirectory)) {
            try {
                Files.createDirectory(outputDirectory);
            } catch (IOException e) {
                System.out.println("Failed to create output directory");
                e.printStackTrace();
                return;
            }
        }

        for (String inputFile : args) {
            Path inputFilePath = Paths.get(inputFile.trim());
            Path outputFilePath = generateOutputFilePath(inputFilePath, outputDirectory);

            try {
                String str = new String(Files.readAllBytes(inputFilePath), "UTF-8");
                String file1 = "";
                String a = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

                String b = "<project xmlns:nuance=\"https://developer.nuance.com/mix/nlu/trsx\" xml:lang=\"eng-USA\" nuance:version=\"2.4\">";

                String f = "<samples>";

                char c = (char)34;
                file1 +=  a + "\n" + b + "\n" + f + "\n";
                String ch = "";
                ch = ch + c;
                str = str.replaceAll("LT ", "<");
                str = str.replaceAll(" EQ", "=");
                str = str.replaceAll("EQ ", ch);
                str = str.replaceAll(" QUOT", ch);
                str = str.replaceAll("=\" ", "=\"");
                str = str.replaceAll(" CL ", ":::");
                str = str.replaceAll(" GT", ">");
                str = str.replaceAll("GT ", ">");
                str = str.replaceAll("BS ", "/");
                file1 += str;
                String d = "</samples>";

                String e = "</project>";

                file1 += d + "\n" + e;
                xmlValidator validator = new xmlValidator();
                boolean isValid = validator.isXML(file1);
                System.out.println(isValid);


                BufferedWriter writer = Files.newBufferedWriter(outputFilePath);
                writer.write(file1);
                writer.close();
                System.out.println("The output file is stored at: " + outputFilePath.toAbsolutePath());

            } catch (Exception e) {
                System.out.println("Error processing file " + inputFile);
                e.printStackTrace();
            }
        }
    }

    private static Path generateOutputFilePath(Path inputFilePath, Path outputDirectory) {
        String inputFileName = inputFilePath.getFileName().toString();
        return outputDirectory.resolve(inputFileName);
    }
}
