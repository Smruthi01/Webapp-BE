package com.webapp.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.webapp.model.Tutorial;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.webapp.spring.datajpa.model.Tutorial;
public interface TutorialRepository extends JpaRepository<com.webapp.webapp.model.Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
  List<Tutorial> findByTitleContaining(String title);
    
}
