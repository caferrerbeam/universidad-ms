package co.edu.eam.ingesoft.products_ms.services;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.eam.ingesoft.products_ms.model.Program;
import co.edu.eam.ingesoft.products_ms.repositories.ProgramRepository;

@Service
public class ProgramService {

	/**
	 * program repository.
	 */
	@Autowired
	private ProgramRepository programRepository;

	/**
	 * Create a programToCreate.
	 *
	 * @param programToCreate program to create.
	 * @return program created
	 */
	public Program create(Program programToCreate) {
		Program program = find(programToCreate.getId());

		if (program != null) {
			throw new EntityExistsException("program already exists");
		}

		return programRepository.save(programToCreate);
	}

	/**
	 * Update a program.
	 *
	 * @param program program to update.
	 * @return program edited
	 */
	public Program update(Program program) {
		Program programToUpdate = find(program.getId());

		if (programToUpdate == null) {
			throw new EntityNotFoundException("program not exists");
		}

		return programRepository.save(program);
	}

	/**
	 * Find a program.
	 *
	 * @param id id to end the program
	 * @return the program found
	 */
	public Program find(Integer id) {
		return programRepository.findById(id).orElse(null);
	}

	/**
	 * Delete a program.
	 *
	 * @param id id to delete
	 * @return program deleted
	 */
	public Program delete(Integer id) {
		Program program = find(id);

		if (program == null) {
			throw new EntityNotFoundException("program not exists");
		}

		programRepository.deleteById(id);

		return program;
	}

	/**
	 * List all programs.
	 *
	 * @return list of all programs
	 */
	public List<Program> listAll() {
		return programRepository.findAll();
	}

	/**
	 * List programs by name.
	 *
	 * @param name name to looking for
	 * @return list program with a name.
	 */
	public List<Program> findByName(String name) {
		return programRepository.findByName(name);
	}

}
