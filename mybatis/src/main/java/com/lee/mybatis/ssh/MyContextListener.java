package com.lee.mybatis.ssh;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

    private static MySQLSSHConnection mysqlConexionssh;
    private static RedisSSHConnection redisConexionssh;

    public static MySQLSSHConnection getMysqlConexionssh() {
        return mysqlConexionssh;
    }
    public static RedisSSHConnection getRedisConexionssh() {
        return redisConexionssh;
    }

    public MyContextListener() {
        super();
    }
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // 建立连接
        System.out.println("Context initialized ... !");
        try {
            mysqlConexionssh = new MySQLSSHConnection();
            redisConexionssh = new RedisSSHConnection();
            System.out.println("成功建立SSH连接");

            // Session session = conexionssh.getSession();
            // new Thread() {
            //     public void run() {
            //         do {
            //             SSHUtil.sendCmd(session, "pwd");
            //             System.out.println(new Date());
            //             try {
            //                 Thread.sleep(1000 * 60);
            //             } catch (InterruptedException e) {
            //                 e.printStackTrace();
            //             }
            //         } while (true);
            //     }
            // }.start();
        } catch (Throwable e) {
            System.out.println("SSH连接失败！");
            e.printStackTrace(); // error connecting SSH server
        }
    }
    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // 断开连接
        System.out.println("Context destroyed ... !");
        try {
            mysqlConexionssh.closeSSH(); // disconnect
            redisConexionssh.closeSSH();
            System.out.println("成功断开SSH连接!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("断开SSH连接出错！");
        }
    }

}
