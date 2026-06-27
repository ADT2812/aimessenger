package ai.config;

public class ModelConfig {

    // Path to llama executable
    public static final String EXECUTABLE_PATH =
            "ai/runtime/bin/llama-cli";

    // Path to GGUF model
    public static final String MODEL_PATH =
            "ai/runtime/model/Llama-3.2-3B-Instruct-Q4_K_M.gguf";

    public static final int THREADS = 4;

    public static final int CONTEXT = 4096;
}