/**
 * 
 */
package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.api.models.Contact;

/**
 * @author Clesyo
 *
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
