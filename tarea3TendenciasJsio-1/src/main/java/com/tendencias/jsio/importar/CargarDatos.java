package com.tendencias.jsio.importar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tendencias.jsio.model.Curso;
import com.tendencias.jsio.model.Depto;
import com.tendencias.jsio.model.Profesor;
import com.tendencias.jsio.repository.CursoRepository;
import com.tendencias.jsio.repository.DeptoRepository;
import com.tendencias.jsio.repository.ProfesorRepository;

import java.util.List;

@Component
public class CargarDatos implements CommandLineRunner {

    private  CursoRepository cursoRepository;
    private  DeptoRepository deptoRepository;
    private  ProfesorRepository profesorRepository;

    public CargarDatos(CursoRepository cursoRepository, DeptoRepository deptoRepository, ProfesorRepository profesorRepository) {
        this.cursoRepository = cursoRepository;
        this.deptoRepository = deptoRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando la carga de datos...");

        insertarCursos();
        insertarDepartamentos();
        insertarProfesores();

        System.out.println("Carga de datos finalizada.");
    }

    private void insertarCursos() {
        cursoRepository.saveAll(List.of(
                new Curso("1", "1", "Curso de Matemáticas", "Básico", "Introducción a Matemáticas"),
                new Curso("2", "2", "Curso de Física", "Intermedio", "Física aplicada"),
                new Curso("3", "3", "Curso de Programación", "Avanzado", "Desarrollo de software")
        ));
        System.out.println("Cursos insertados.");
    }

    private void insertarDepartamentos() {
        deptoRepository.saveAll(List.of(
                new Depto("1", "Departamento de Ciencias", "Dr. Juan Pérez", "Área de investigación en ciencias"),
                new Depto("2", "Departamento de Tecnología", "Ing. Ana Gómez", "Innovación tecnológica"),
                new Depto("3", "Departamento de Matemáticas", "Dr. Luis Ortega", "Estudios avanzados de matemáticas")
        ));
        System.out.println("Departamentos insertados.");
    }

    private void insertarProfesores() {
        profesorRepository.saveAll(List.of(
                new Profesor("1", "1", "Pedro Sánchez", "Av. Siempre Viva 123", "0987654321"),
                new Profesor("2", "2", "Marta López", "Calle Luna 456", "0981234567"),
                new Profesor("3", "3", "Luis García", "Plaza Sol 789", "0998765432")
        ));
        System.out.println("Profesores insertados.");
    }
}
