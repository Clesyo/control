/**
 * 
 */
package com.app.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.models.Contact;

/**
 * @author Clesyo
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
