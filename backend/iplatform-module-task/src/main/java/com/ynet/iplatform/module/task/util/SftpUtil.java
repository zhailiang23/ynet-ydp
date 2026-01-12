package com.ynet.iplatform.module.task.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * SFTP 工具类
 *
 * @author iplatform
 */
@Slf4j
public class SftpUtil {

    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public SftpUtil(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * 从 SFTP 服务器下载文件内容
     *
     * @param remoteFilePath 远程文件路径
     * @return 文件内容（UTF-8 编码）
     * @throws Exception 下载失败时抛出异常
     */
    public String downloadFileContent(String remoteFilePath) throws Exception {
        Session session = null;
        ChannelSftp channelSftp = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        try {
            log.info("开始连接 SFTP 服务器: {}:{}", host, port);

            // 创建 JSch 对象
            JSch jsch = new JSch();

            // 获取 session
            session = jsch.getSession(username, host, port);
            session.setPassword(password);

            // 设置 session 配置
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no"); // 不验证主机密钥
            session.setConfig(config);

            // 连接
            session.connect(30000); // 30秒超时
            log.info("SFTP 会话连接成功");

            // 打开 SFTP 通道
            Channel channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            log.info("SFTP 通道打开成功");

            // 下载文件
            log.info("开始下载文件: {}", remoteFilePath);
            inputStream = channelSftp.get(remoteFilePath);

            // 读取文件内容
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            String content = outputStream.toString(StandardCharsets.UTF_8);
            log.info("文件下载成功，大小: {} 字节", content.length());

            return content;

        } catch (JSchException e) {
            log.error("SFTP 连接失败", e);
            throw new Exception("SFTP 连接失败: " + e.getMessage(), e);
        } catch (SftpException e) {
            log.error("SFTP 文件下载失败: {}", remoteFilePath, e);
            throw new Exception("SFTP 文件下载失败: " + e.getMessage(), e);
        } finally {
            // 关闭资源
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (channelSftp != null && channelSftp.isConnected()) {
                    channelSftp.disconnect();
                    log.info("SFTP 通道已关闭");
                }
                if (session != null && session.isConnected()) {
                    session.disconnect();
                    log.info("SFTP 会话已关闭");
                }
            } catch (Exception e) {
                log.error("关闭 SFTP 资源时发生错误", e);
            }
        }
    }
}
