package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.controllers.InputsController;
import ar.edu.utn.frba.dds.entities.CalculadorGradoConfianza;


import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;


public class Api {
    public static void main(String[] args) {

        CalculadorGradoConfianza calculadorGradoConfianza = new CalculadorGradoConfianza();

        Integer port = Integer.parseInt(
                System.getProperty("port", "8080"));
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);


            SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
            swaggerConfiguration.setDocumentationPath("api.json");
            SwaggerPlugin swaggerPlugin = new SwaggerPlugin(swaggerConfiguration);

            OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
            config.plugins.register(new OpenApiPlugin(openApiConfiguration));
            config.plugins.register(swaggerPlugin);
            config.plugins.register(new ReDocPlugin(new ReDocConfiguration()));

        }).start(port);


        app.post("/api/calcularPuntaje",
                new InputsController(calculadorGradoConfianza));

    }


}