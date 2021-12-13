package com.lee.mybatis;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class SSHTest {

    public static void main(String[] arg) {
        try{
            JSch jsch=new JSch();

            // System.out.print("host:user:password are " + arg[0] + arg[1] + arg[2]);

            String host="101.35.246.235";
            String user="ljd-docker-server";

            Session session=jsch.getSession(user, host, 22);

            session.setPassword("123456");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(60 * 1000);

            String command="pwd";
            new Thread() {
                public void run() {
                    for (int i = 0; i < 50; i++) {

                        sendCmd(session, command);
                        if (i == 49)
                            session.disconnect();
                        try {
                            Thread.sleep(1000 * 10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void sendCmd(Session session, String command) {
        try {
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);

            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
