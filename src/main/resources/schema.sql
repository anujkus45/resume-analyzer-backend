-- Resume Analyzer Database Schema
-- Created: Day 3

CREATE DATABASE IF NOT EXISTS resume_analyzer_db;
USE resume_analyzer_db;

-- Table 1: Users
CREATE TABLE IF NOT EXISTS users (
                                     id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name          VARCHAR(100) NOT NULL,
    email         VARCHAR(150) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    role          ENUM('STUDENT','RECRUITER','ADMIN') NOT NULL,
    is_verified   BOOLEAN DEFAULT FALSE,
    is_active     BOOLEAN DEFAULT TRUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP
    );

-- Table 2: Companies
CREATE TABLE IF NOT EXISTS companies (
                                         id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         name         VARCHAR(200) NOT NULL,
    description  TEXT,
    website      VARCHAR(255),
    logo_url     VARCHAR(500),
    location     VARCHAR(200),
    industry     VARCHAR(100),
    size         ENUM('STARTUP','SMALL','MEDIUM','LARGE','ENTERPRISE'),
    recruiter_id BIGINT NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (recruiter_id) REFERENCES users(id)
    );

-- Table 3: Jobs
CREATE TABLE IF NOT EXISTS jobs (
                                    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title            VARCHAR(200) NOT NULL,
    description      TEXT NOT NULL,
    requirements     TEXT,
    location         VARCHAR(200),
    job_type         ENUM('FULL_TIME','PART_TIME','CONTRACT',
                          'INTERNSHIP','REMOTE') NOT NULL,
    experience_level ENUM('FRESHER','JUNIOR','MID','SENIOR','LEAD'),
    salary_min       DECIMAL(10,2),
    salary_max       DECIMAL(10,2),
    skills_required  TEXT,
    is_active        BOOLEAN DEFAULT TRUE,
    deadline         DATE,
    company_id       BIGINT NOT NULL,
    posted_by        BIGINT NOT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES companies(id),
    FOREIGN KEY (posted_by)  REFERENCES users(id)
    );

-- Table 4: Resumes
CREATE TABLE IF NOT EXISTS resumes (
                                       id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       user_id     BIGINT NOT NULL,
                                       file_name   VARCHAR(255) NOT NULL,
    file_url    VARCHAR(500) NOT NULL,
    file_size   BIGINT,
    score       DECIMAL(5,2),
    feedback    TEXT,
    is_active   BOOLEAN DEFAULT TRUE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

-- Table 5: Job Applications
CREATE TABLE IF NOT EXISTS job_applications (
                                                id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                job_id       BIGINT NOT NULL,
                                                applicant_id BIGINT NOT NULL,
                                                resume_id    BIGINT NOT NULL,
                                                status       ENUM('APPLIED','UNDER_REVIEW','SHORTLISTED',
                                                'REJECTED','HIRED') DEFAULT 'APPLIED',
    cover_letter TEXT,
    applied_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (job_id)       REFERENCES jobs(id),
    FOREIGN KEY (applicant_id) REFERENCES users(id),
    FOREIGN KEY (resume_id)    REFERENCES resumes(id),
    UNIQUE KEY unique_application (job_id, applicant_id)
    );

-- Table 6: Saved Jobs
CREATE TABLE IF NOT EXISTS saved_jobs (
                                          id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          user_id  BIGINT NOT NULL,
                                          job_id   BIGINT NOT NULL,
                                          saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (job_id)  REFERENCES jobs(id),
    UNIQUE KEY unique_saved (user_id, job_id)
    );

-- Table 7: Refresh Tokens
CREATE TABLE IF NOT EXISTS refresh_tokens (
                                              id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              user_id     BIGINT NOT NULL,
                                              token       VARCHAR(500) NOT NULL UNIQUE,
    expiry_date TIMESTAMP NOT NULL,
    is_revoked  BOOLEAN DEFAULT FALSE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );