CREATE SCHEMA `students_management` ;

CREATE TABLE `students_management`.`students` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `id_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `students_management`.`new_table` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `id_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `students_management`.`teachers` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `id_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `students_management`.`courses` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `code` VARCHAR(45) NULL,
  `start_date` VARCHAR(45) NULL,
  `end_date` VARCHAR(45) NULL,
  `exam_date` VARCHAR(45) NULL,
  `description` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `students_management`.`enrollments` (
  `id` INT NOT NULL,
  `student_id` INT NULL,
  `course_id` INT NULL,
  `grade` FLOAT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `students_management`.`teaching` (
  `id` INT NOT NULL,
  `teacher_id` INT NULL,
  `course_id` INT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `students_management`.`enrollments` 
ADD INDEX `student_id_idx` (`student_id` ASC),
ADD INDEX `course_id_idx` (`course_id` ASC);
ALTER TABLE `students_management`.`enrollments` 
ADD CONSTRAINT `student_id`
  FOREIGN KEY (`student_id`)
  REFERENCES `students_management`.`students` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `course_id`
  FOREIGN KEY (`course_id`)
  REFERENCES `students_management`.`courses` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `students_management`.`teaching` 
ADD INDEX `teacher_id_idx` (`teacher_id` ASC),
ADD INDEX `course_id_idx` (`course_id` ASC);
ALTER TABLE `students_management`.`teaching` 
ADD CONSTRAINT `teacher_id`
  FOREIGN KEY (`teacher_id`)
  REFERENCES `students_management`.`teachers` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `course_id`
  FOREIGN KEY (`course_id`)
  REFERENCES `students_management`.`courses` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  



