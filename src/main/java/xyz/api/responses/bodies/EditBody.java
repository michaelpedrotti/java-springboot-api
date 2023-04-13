package xyz.api.responses.bodies;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class EditBody implements Serializable {

    public Serializable form;

    public Serializable data;
}