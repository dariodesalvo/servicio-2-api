package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.entities.CalculadorGradoConfianza;
import ar.edu.utn.frba.dds.entities.Inputs;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

public class InputsController implements Handler {
    private CalculadorGradoConfianza calculador;

    public InputsController(CalculadorGradoConfianza calculador) {
        super();
        this.calculador = calculador;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        Inputs inputs = ctx.bodyAsClass(Inputs.class);
        //el metodo bodyasclass es que si el cuerpo del mensaje es un json,
        // crea el objeto Inputs.class con los parametros que le pasamos
       // this.repo.add(inputs);
        ctx.status(HttpStatus.CREATED);
        ctx.result("Datos recibidos correctamente");
        ctx.json(calculador.calcularPuntaje(inputs));
    }
}
