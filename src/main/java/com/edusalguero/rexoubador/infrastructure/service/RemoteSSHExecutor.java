package com.edusalguero.rexoubador.infrastructure.service;


import com.edusalguero.rexoubador.domain.service.executor.Connection;
import com.edusalguero.rexoubador.domain.service.executor.ExecutionException;
import com.edusalguero.rexoubador.domain.service.executor.RemoteExecutor;
import com.edusalguero.rexoubador.domain.service.executor.command.CommandInterface;
import com.edusalguero.rexoubador.domain.service.executor.command.response.CommandResponseInterface;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class RemoteSSHExecutor implements RemoteExecutor {

    private final String privateKeyPath;
    private final String passphrase;
    private final JSch sshClient;
    private com.jcraft.jsch.Session session;

    @Autowired
    public RemoteSSHExecutor(@Value("${rexoubador.privateKeyPath}") String privateKeyPath, @Value("${rexoubador.passphrase}") String passphrase) throws JSchException {
        this.privateKeyPath = privateKeyPath;
        this.passphrase = passphrase;
        this.sshClient = new JSch();
        sshClient.addIdentity(this.privateKeyPath, this.passphrase);
    }

    @Override
    public CommandResponseInterface execute(Connection connection, CommandInterface command) throws ExecutionException {
        String result;
        try {
            session = sshClient.getSession(connection.getUsername(), connection.getHost(), connection.getPort());
            session.setConfig("PreferredAuthentications", "publickey");
            session.setTimeout(4000);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            Channel channel;
            channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(command.getCommandString());
            ((ChannelExec) channel).setPty(false);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in;
            in = channel.getInputStream();

            channel.connect();
            result = IOUtils.toString(in, String.valueOf(StandardCharsets.UTF_8));
            channel.disconnect();

            session.disconnect();

        } catch (JSchException | IOException e) {
            throw new ExecutionException("Remote execution exception [" + e.getMessage() + "]!");
        }
        return command.parseResult(result);
    }
}
