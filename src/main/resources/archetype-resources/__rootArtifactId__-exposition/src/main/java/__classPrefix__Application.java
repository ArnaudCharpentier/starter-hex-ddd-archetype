package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${classPrefix}Application {

    /**
     * Main method to launch ${classPrefix} Application
     *
     * @param args
     *     command line parameters
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
         SpringApplication.run(${classPrefix}Application.class, args);
    }
}
