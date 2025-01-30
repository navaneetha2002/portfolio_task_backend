package com.example.demo.service;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service


public class ProjectService {


        @Autowired
        private ProjectRepository projectRepository;

        public List<Project> getAllProjects() {
            return projectRepository.findAll();
        }

        public Project saveProject(Project project) {
            return projectRepository.save(project);
        }

    public Project getProjectById(UUID id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project addProject() throws IOException{
            return addProject(null,null,null);
    }

    public Project addProject(String name, MultipartFile image, String description) throws IOException {
            Project project = new Project();
            project.setName(name);
            project.setDescription(description);

            if(image != null && !image.isEmpty()){
                project.setImage(image.getBytes());
            }
            return projectRepository.save(project);
    }

    public Project updateProject(UUID id, String name, MultipartFile image, String description) throws IOException{
           Project existingProject = getProjectById(id);
           if(existingProject!=null){
               existingProject.setName(name);
               existingProject.setDescription(description);

               if(image!=null && !image.isEmpty()){
                   existingProject.setImage(image.getBytes());
               }

               return projectRepository.save(existingProject);
           }
           return null;
    }

    public boolean deleteProject(UUID id){
            if(projectRepository.existsById(id)){
                projectRepository.deleteById(id);
                return true;
            }
            return false;
    }

}

