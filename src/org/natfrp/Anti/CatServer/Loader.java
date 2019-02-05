package org.natfrp.Anti.CatServer;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class Loader {

    static class TimerTaskImpl extends TimerTask {

        private static final String mainClass = "catserver.server.CatServer";

        @Override
        public void run() {
            try {
                // 检测到 CatServer 主类存在
                if (Class.forName(mainClass) != null) {
                    throw new SecurityException();
                }
            } catch (ClassNotFoundException ignored) {
                // 服务端非 CatServer 或暂时未载入
            } catch (SecurityException se) {
                System.out.println("======================= [ Anti CatServer ] =======================");
                System.out.println("CatServer 服务端已被管理员禁止使用，请更换其他服务端后再尝试启动服务器。");
                System.out.println("因为该服务端存在后门，出于保护您和其他用户数据的安全，我们禁止使用此服务端。");
                System.out.println("如果您多次尝试加载该服务端，或者尝试绕过我们的检测，您的服务器将会被停机。");
                System.out.println("==================================================================");
                System.exit(-1);
                throw se;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private static final Map<String, Runnable> solutions = new HashMap<>();

    static {
        solutions.put("hardcore", () -> new Timer(true).schedule(new TimerTaskImpl(), 0, 1000));
    }

    public static void premain(String agentOps, Instrumentation inst) {
        agentOps = agentOps != null ? (agentOps.isEmpty() ? "hardcore" : agentOps) : "hardcore";
        Runnable solution = solutions.get(agentOps);
        if (solution != null) {
            try {
                solution.run();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
                throw e;
            }
        } else {
            throw new IllegalArgumentException(agentOps);
        }
    }

}
