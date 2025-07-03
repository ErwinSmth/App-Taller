package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.taller.TallerDto;
import com.Innovacion.Taller.domain.dto.taller.TallerResumenDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.ICategoriaRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IOrganizadorRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IProfesorRepository;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.ITallerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TallerService {

    @Autowired
    private ITallerRepository tallerRepo;

    @Autowired
    private ICategoriaRepository categoriaRepo;

    @Autowired
    private IProfesorRepository profesorRepo;

    @Autowired
    private IOrganizadorRepository organizadorRepo;

    private static final Logger logger = LoggerFactory.getLogger(TallerService.class);


    @Transactional
    //crear un taller
    public TallerDto crearTaller(TallerDto tallerDto) {

        logger.info("Intentando crear taller: {}", tallerDto);

        if (tallerDto == null) {
            logger.error("Datos del taller incompletos");
            throw new IllegalArgumentException("Datos del taller incompletos");
        }
        if (tallerDto.getTitulo() == null || tallerDto.getTitulo().isEmpty()) {
            logger.error("El título es obligatorio");
            throw new IllegalArgumentException("El título es obligatorio");
        }
        if (tallerDto.getDescripcion() == null || tallerDto.getDescripcion().isEmpty()) {
            logger.error("La descripción es obligatoria");
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (tallerDto.getPrecio() == null || tallerDto.getPrecio().doubleValue() < 0) {
            logger.error("El precio debe ser mayor o igual a 0");
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
        if (tallerDto.getCapacidad() == null || tallerDto.getCapacidad() <= 0) {
            logger.error("La capacidad debe ser mayor a 0");
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        if (tallerDto.getCategoria() == null || tallerDto.getCategoria().getCategoriaId() == null) {
            logger.error("La categoría es obligatoria");
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (tallerDto.getFechaFinalizacion() == null) {
            throw new IllegalArgumentException("La fecha de finalización es obligatoria");
        }


        //Validar que al menos profesor o organizador esten presentes
        boolean profesorPresente = tallerDto.getProfesor() != null && tallerDto.getProfesor().getProfesorId() != null;
        boolean organizadorPresente = tallerDto.getOrganizador() != null && tallerDto.getOrganizador().getOrganizadorId() != null;

        if (!profesorPresente && !organizadorPresente) {
            if (!profesorPresente && !organizadorPresente) {
                logger.error("Debe especificar al menos un profesor o un organizador para el taller");
                throw new IllegalArgumentException("Debe especificar al menos un profesor o un organizador para el taller");
            }

            throw new IllegalArgumentException("Debe especificar al menos un profesor o un organizador para el taller");
        }

        logger.info("Validaciones básicas pasadas. Continuando con la creación...");

        // Validar existencia de categoría
        if (categoriaRepo.findById(tallerDto.getCategoria().getCategoriaId()).isEmpty())
            throw new IllegalArgumentException("La categoría no existe");

        // Validar existencia de profesor si se especifica
        if (profesorPresente && profesorRepo.findById(tallerDto.getProfesor().getProfesorId()).isEmpty())
            throw new IllegalArgumentException("El profesor no existe");

        // Validar existencia de organizador si se especifica
        if (organizadorPresente && organizadorRepo.findById(tallerDto.getOrganizador().getOrganizadorId()).isEmpty())
            throw new IllegalArgumentException("El organizador no existe");

        //Validar que no exista taller con el mismo titulo
        List<TallerResumenDto> existentes = tallerRepo.findByTituloContaining(tallerDto.getTitulo());
        if (existentes.stream().anyMatch(t -> t.getTitulo().equalsIgnoreCase(tallerDto.getTitulo())))
            throw new IllegalArgumentException("Ya existe un taller con ese titulo");

        return tallerRepo.save(tallerDto);

    }

    //Obtener detalle del taller por el Id
    public Optional<TallerDto> getTallerById(Long id) {
        if (id == null) throw new IllegalArgumentException("ID invalido");
        return tallerRepo.findById(id);
    }

    //Eliminar taller por el id
    public void eliminarTaller(Long id) {
        if (id == null) throw new IllegalArgumentException("ID invalido");
        if (tallerRepo.findById(id).isEmpty())
            throw new IllegalArgumentException("El taller no existe");
        tallerRepo.deteleById(id);
    }

    //Metodos usados para la parte visual
    //Listar todos los talleres
    public List<TallerResumenDto> listarTalleres() {
        return tallerRepo.findAll();
    }

    //Listar talleres por categoria
    public List<TallerResumenDto> listarTalleresPorCategoria(Long categoriaId) {
        if (categoriaId == null) throw new IllegalArgumentException("ID de categoria invalido");
        return tallerRepo.findByCategoriaId(categoriaId);
    }

    // Listar talleres por profesor
    public List<TallerResumenDto> listarTalleresPorProfesor(Long profesorId) {
        if (profesorId == null) throw new IllegalArgumentException("ID de profesor inválido");
        return tallerRepo.findProfesorId(profesorId);
    }

    // Listar talleres por organizador
    public List<TallerResumenDto> listarTalleresPorOrganizador(Long organizadorId) {
        if (organizadorId == null) throw new IllegalArgumentException("ID de organizador inválido");
        return tallerRepo.findByOrganizadorId(organizadorId);
    }

    // Buscar talleres por título
    public List<TallerResumenDto> buscarTalleresPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) throw new IllegalArgumentException("Título inválido");
        return tallerRepo.findByTituloContaining(titulo);

    }

    public TallerDto editarTaller(Long tallerId, TallerDto tallerDto){
        if (tallerId == null || tallerDto == null) throw new IllegalArgumentException("Datos inválidos");

        // Buscar el taller original
        Optional<TallerDto> originalOpt = tallerRepo.findById(tallerId);
        if (originalOpt.isEmpty()) throw new IllegalArgumentException("El taller no existe");

        TallerDto original = originalOpt.get();

        // Validar que el profesor sea el dueño
        if (original.getProfesor() == null || tallerDto.getProfesor() == null ||
                !original.getProfesor().getProfesorId().equals(tallerDto.getProfesor().getProfesorId())) {
            throw new IllegalArgumentException("No tiene permisos para editar este taller");
        }

        // Validar título único (excepto el propio taller)
        List<TallerResumenDto> existentes = tallerRepo.findByTituloContaining(tallerDto.getTitulo());
        for (TallerResumenDto t : existentes) {
            if (!t.getTallerId().equals(tallerId)) {
                throw new IllegalArgumentException("Ya existe un taller con ese título");
            }
        }

        // Actualizar campos permitidos
        original.setTitulo(tallerDto.getTitulo());
        original.setDescripcion(tallerDto.getDescripcion());
        original.setDuracionHoras(tallerDto.getDuracionHoras());
        original.setPrecio(tallerDto.getPrecio());
        original.setCapacidad(tallerDto.getCapacidad());
        original.setCategoria(tallerDto.getCategoria());
        original.setImagenes(tallerDto.getImagenes());
        original.setFechaFinalizacion(tallerDto.getFechaFinalizacion());

        // Guardar y devolver DTO actualizado
        return tallerRepo.save(original);
    }

    public List<TallerResumenDto> listarTalleresPorCategoriaExcluyendoProfesor(Long categoriaId, Long profesorId) {
        if (categoriaId == null) throw new IllegalArgumentException("ID de categoria invalido");
        if (profesorId == null) {
            return tallerRepo.findByCategoriaId(categoriaId);
        }
        return tallerRepo.findByCategoriaIdExcluyendoProfesor(categoriaId, profesorId);
    }
}
