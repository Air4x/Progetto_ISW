package org.groupone.utility;

import java.util.Objects;
import java.util.UUID;

/**
 * Classe di utilità che implementa le funzioni per la creazione e
 * gestione dei codici identificativi usati nelle classi di dominio
 *
 * Utilizza {@see java.util.UUID} per la creazione degli UUID veri e
 * propri, in particolare viene utilizzato per la creazione di UUIDv4,
 * di conseguenza la probabilità di una "convergenza" tra due ID è
 * abbastanza bassa da poter essere ignorata. Il tutto rafforzato dal
 * fatto che non ci sono state convergenze documentate utilizzando
 * UUIDv4
 *
 * @author Mario Calcagno
 */
public class ID {
    /**
     * L'id vero e proprio rappresentato come {@see java.util.UUID}
     *
     */
    private UUID id;

    /**
     * Costruttore di ID, permette di creare un ID data la sua
     * rappresentazione come stringa
     *
     */
    public ID(String s){
	id = UUID.fromString(s);
    }

    /**
     * Costruttore di ID, crea un nuovo ID unico e universale
     *
     */
    private ID() {
	this.id = UUID.randomUUID();
    }

    /**
     * Metodo statico, viene utilizzato per generare un nuovo ID da
     * parte dei livello superiori
     *
     */
    public static synchronized ID generate(){
	return new ID();
    }

    /**
     * Semplice getter per ottenere l'id come {@see UUID}
     *
     */
    public UUID getID() {
	return this.id;
    }

    /**
     * Override del metodo toString() di {@link java.util.UUID}
     *
     */
    @Override
    public String toString() {
	return this.id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ID id1 = (ID) o;
        return id.toString().equals(id1.id.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
