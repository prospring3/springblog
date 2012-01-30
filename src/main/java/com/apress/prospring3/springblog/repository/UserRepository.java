/**
 * Created on Dec 20, 2011
 */
package com.apress.prospring3.springblog.repository;

import org.springframework.data.repository.CrudRepository;

import com.apress.prospring3.springblog.domain.AppUser;

/**
 * @author Clarence
 *
 */
public interface UserRepository extends CrudRepository<AppUser, String> {

}
