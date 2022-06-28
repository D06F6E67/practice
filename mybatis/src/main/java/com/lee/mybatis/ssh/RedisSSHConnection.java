package com.lee.mybatis.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Redis ssh连接
 */
public class RedisSSHConnection {

    // 需要了可以打开
    //private final static String S_PATH_FILE_PRIVATE_KEY = "/Users/xxx/.ssh/id_rsa";
    // 需要了可以打开
    //private final static String S_PATH_FILE_KNOWN_HOSTS = "/Users/xxx/.ssh/id_rsa/.ssh/known_hosts";

    // 自定义的中转接口，需要和数据源接口设置一样 这个是本地的端口,选取一个没有占用的port即可
    private final static int LOCAl_PORT = 6379;
    // 服务端的Redis端口
    private final static int REMOTE_PORT = 6379;

    // 连接到哪个服务端的SSH
    private final static String SSH_REMOTE_SERVER = "服务器IP";
    // 服务器端SSH端口 默认是22
    private final static int SSH_REMOTE_PORT = 22;
    // SSH用户名
    private final static String SSH_USER = "用户名";
    // SSH使用密码
    private final static String SSH_PASSWORD = "密码";
    // 服务端的本地mysql服务
    private final static String MYSQL_REMOTE_SERVER = "redis-ljd";

    private Session session; //represents each ssh session

    public Session getSession() {
        return session;
    }

    // 测试连接
    // public static void main(String[] args) throws Throwable {
    //    System.out.println(new SSHConnection());
    // }

    public RedisSSHConnection() throws Throwable {

        JSch jsch = new JSch();
        // 需要用到了开启
        // jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
        // jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY);

        session = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
        session.setPassword(SSH_PASSWORD);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setTimeout(1000);
        // 去连接
        session.connect(); //ssh connection established!
        // 设置转发
        session.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);

        System.out.println("Redis SSHConnection--运行OK");
    }

    /**
     * 断开SSH连接
     */
    public void closeSSH() {
        session.disconnect();
    }

}
