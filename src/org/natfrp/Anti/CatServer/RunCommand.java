package org.natfrp.Anti.CatServer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunCommand {

    public static String exec(String cmd) throws Exception {
        Process pcs = Runtime.getRuntime().exec(cmd);
        String result = null;
        BufferedInputStream in = new BufferedInputStream(pcs.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String lineStr;
        while ((lineStr = br.readLine()) != null) {
            result = lineStr;
        }
        br.close();
        in.close();
        return result;
    }

}
