package com.gao.utils;

import com.jcraft.jsch.*;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.IOException;
import java.io.*;
import java.util.*;


public class Shell {
    private static final Logger lg = LoggerFactory.getLogger(Shell.class);
    private static Session session;

    //连接服务器
    private static void connect(String username, String passwd, String host, int port) {
        try {
            JSch jsch = new JSch();
            //获取sshSession
            session = jsch.getSession(username, host, port);
            //添加密码
            session.setPassword(passwd);
            Properties sshConfig = new Properties();
            //严格主机密钥检查
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            //开启sshSession连接
            session.connect();
            lg.info("Server connection successful.");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    //执行相关命令
    public static String execCmd(String command, String username, String passwd, String host, int port) {

        String resultJson = null;
        ChannelExec channelExec = null;
        if (command != null) {
            try {
                connect(username, passwd, host, port);
                channelExec = (ChannelExec) session.openChannel("exec");
                // 设置需要执行的shell命令
                channelExec.setCommand(command);
                lg.info("linux命令:" + command);
                channelExec.connect();
                channelExec.start();
            } catch (JSchException e) {
                e.printStackTrace();
            } finally {
                if (null != channelExec) {
                    channelExec.disconnect();
                }
            }
        }
        return resultJson;
    }

    // 从 服务器 获取 数据
    private static String getServerData(String host, int port, String username,
                                        String password, String filePath) {

        ChannelSftp sftp = null;
        StringBuffer buffer = new StringBuffer();
        try {

            if (!session.isConnected()) {
                connect(username, password, host, port);
            }

            //获取sftp通道
            Channel channel = session.openChannel("sftp");
            //开启
            channel.connect();
            sftp = (ChannelSftp) channel;
            lg.info("Connected to " + host + ".");
            //获取生成文件流
            InputStream inputStream = sftp.get(filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            //关闭流
            inputStream.close();
            in.close();
            lg.info(" 执行结果为: " + buffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (null != sftp) {
                sftp.quit();
            }
            closeSession();
        }
        return buffer.toString();
    }


    public static void closeSession() {
        if (session != null) {
            session.disconnect();
        }

    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    @Test
    public void test(){
        Shell.execCmd("ls -a > b.txt", "git", "19980913", "47.100.203.79", 22);
    }
}
