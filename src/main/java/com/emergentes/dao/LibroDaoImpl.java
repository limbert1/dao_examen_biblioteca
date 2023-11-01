package com.emergentes.dao;

import com.emergentes.modelo.Libro;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoImpl extends ConexionDB implements LibroDao {

    @Override
    public void insert(Libro libro) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO libros ( titulo, autor, disponible, categoria) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = this.con.prepareStatement(sql);

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getDisponible());
            ps.setString(4, libro.getCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Libro libro) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE libros SET titulo = ?, autor = ?, disponible = ?, categoria = ? WHERE id = ?";

            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getDisponible());

            ps.setString(4, libro.getCategoria());
            ps.setInt(5, libro.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM libros WHERE id = ?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Libro getById(int id) throws Exception {
        Libro pro = new Libro();
        try {
            this.conectar();
            String sql = "SELECT * FROM libros WHERE id = ?";
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setTitulo(rs.getString("titulo"));
                pro.setAutor(rs.getString("autor"));
                pro.setDisponible(rs.getString("disponible"));
                pro.setCategoria(rs.getString("categoria"));

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Libro> getAll() throws Exception {
       ArrayList<Libro> lista = new ArrayList<Libro>();
        try {

            this.conectar();
            String sql = "SELECT * FROM libros";
            PreparedStatement ps = this.con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Libro pro = new Libro();

                pro.setId(rs.getInt("id"));
                pro.setTitulo(rs.getString("titulo"));
                pro.setAutor(rs.getString("autor"));
                pro.setDisponible(rs.getString("disponible"));
                pro.setCategoria(rs.getString("categoria"));



                lista.add(pro);

            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    

}
