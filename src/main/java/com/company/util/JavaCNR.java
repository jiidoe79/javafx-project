
package com.company.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JavaCNR {
    public static String compileAndRun(String file) throws IOException {

        String [] run = {"java ", file};
        String data = "";
        var runProcess = Runtime.getRuntime().exec(run);
        int fcs = runProcess.getErrorStream().read();
        if(fcs != -1) {
            data = getOutput(runProcess.getErrorStream());
        } else {
            data = getOutput(runProcess.getInputStream());
        }
        return data;
    }

    public static String getOutput(InputStream stream) throws IOException {
        String result = "";
        try(BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(stream))) {
            result = bufferedReader.lines().collect(Collectors.joining("\n"));
        }
        return result;
    }
}
