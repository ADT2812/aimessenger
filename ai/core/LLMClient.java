package ai.core;

import ai.config.ModelConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LLMClient {

    public String generateResponse(String prompt) {

        try {

            ProcessBuilder builder = new ProcessBuilder(

                    ModelConfig.EXECUTABLE_PATH,

                    "-m", ModelConfig.MODEL_PATH,

                    "-p", prompt,

                    "-n", "256"

            );

            builder.redirectErrorStream(true);

            Process process = builder.start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(process.getInputStream()));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                response.append(line).append("\n");

            }

            process.waitFor();

            return response.toString();

        } catch (Exception e) {

            return "LLM Error : " + e.getMessage();

        }

    }

}