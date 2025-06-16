package com.Innovacion.Taller.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Clase para realizar logs y depurar
public abstract class Loggeable {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
}
