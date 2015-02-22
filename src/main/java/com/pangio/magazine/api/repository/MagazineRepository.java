package com.pangio.magazine.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pangio.magazine.api.domain.Magazine;

/**
 * {@link MagazineRepository} is where all the {@link Magazine}s will be stored.
 * Also see {@link JpaRepository}
 * 
 * @author pangio
 */
@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

	/**
	 * Finds a {@link Magazine} by id.
	 * @param id of the requested magazine.
	 * @return the magazine.
	 */
	Magazine findById(Long id);

	/**
	 * Finds a {@link Magazine} by name.
	 * @param name of the requested magazine.
	 * @return the magazine.
	 */
	Magazine findByName(String name);

}
