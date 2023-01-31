package com.pi4j.autoconfigure;

import com.pi4j.context.Context;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnClass( InfoContributor.class )
@ConditionalOnBean( Context.class )
public class Pi4jActuatorConfiguration implements InfoContributor {
    
    private Context context;
    
    public Pi4jActuatorConfiguration(Context context){ // This is the PI4J context
        this.context = context;
    }
    

    @Override
    public void contribute( Info.Builder builder ) {
        var osVersion = new HashMap<>();
        osVersion.put( "name", System.getProperty( "os.name" ) );
        osVersion.put( "version", System.getProperty( "os.version" ) );
        osVersion.put( "architecture", System.getProperty( "os.arch" ) );
        
        var boardVersion = new HashMap<>();
        boardVersion.put( "board", getCommandOutput( "cat /proc/device-tree/model" ) );
        boardVersion.put( "boardVersionCode", getCommandOutput( "cat /proc/cpuinfo | grep 'Revision' | awk '{print $3}'" ) );

        builder.withDetail( "os", osVersion );
        builder.withDetail( "board", boardVersion );
        builder.withDetail( "pi4j", context.registry().all());
        
        // The following should be part of the pi4j-board-info library
        // and return the above as a single result
        // var boardType = PiBoardInfo.getBoardType();
    }

    private String getCommandOutput(String command) {
        ExecUtil execUtil = new ExecUtil(command);
        if (!execUtil.isFinished() || !execUtil.getErrorMessage().isEmpty()) {
            //logger.error("Could not execute '{}': {}", command, execUtil.getErrorMessage());
            return "";
        }
        return execUtil.getOutputMessage();
    }
    
    class ExecUtil {

        private boolean finished = false;
        private String outputMessage = "";
        private String errorMessage = "";

        public ExecUtil(String command) {
            execute(command);
        }

        public boolean isFinished() {
            return finished;
        }

        public String getOutputMessage() {
            return outputMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        private void execute(String command) {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("sh", "-c", command);

            try {
                Process process = builder.start();

                OutputStream outputStream = process.getOutputStream();
                InputStream inputStream = process.getInputStream();
                InputStream errorStream = process.getErrorStream();

                outputMessage = readStream(inputStream);
                errorMessage = readStream(errorStream);

                finished = process.waitFor(30, TimeUnit.SECONDS);
                outputStream.flush();
                outputStream.close();

                if (!finished) {
                    process.destroyForcibly();
                }
            } catch (IOException ex) {
                errorMessage = "IOException: " + ex.getMessage();
            } catch (InterruptedException ex) {
                errorMessage = "InterruptedException: " + ex.getMessage();
            }
        }

        private String readStream(InputStream inputStream) {
            StringBuilder rt = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    rt.append(line);
                }
            } catch (Exception ex) {
                rt.append("ERROR: ").append(ex.getMessage());
            }
            return rt.toString();
        }
    }

}
