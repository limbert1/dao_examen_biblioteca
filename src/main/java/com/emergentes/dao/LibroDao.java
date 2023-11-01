
package com.emergentes.dao;

import com.emergentes.modelo.Libro;
import java.util.List;

/**
 *
 * @author LIMBERT
 */
public interface LibroDao {
    public void insert( Libro libro) throws Exception;
    public void update(Libro libro) throws Exception;
    public void delete(int id) throws Exception;
    public Libro getById(int id) throws Exception;
    public List<Libro> getAll() throws Exception;
    
}
