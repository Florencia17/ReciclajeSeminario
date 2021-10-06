package ar.edu.unrn.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.modelo.Direccion;


public interface DireccionDao {


    void create(Direccion direccion);

    void update(Direccion direccion);

    void remove(Long id);

    void remove(Direccion direccion);

    Direccion find(int id);

    List<Direccion> findAll();
}

