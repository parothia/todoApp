Go to spring Initializer and chose the project as Gradle Project, Language as Java,  and 2.5.4 as springboot version
and add dependencies for thymeleaf, springweb and lombok.

We have 3 layers for our project, Controller, Service and Repository(dao) for interaction with database

Index page contains 2 buttons one for projects and other one is tasks.
Tasks will show all tasks with update and delete corresponding to respective task.

Projects will show all the projects with view project, create task, delete project and Export project Summary as gist file.

On clicking create tasks will redirect to page for creating task.

On Clicking view projects will redirect to view tasks of clicked project.