package com.pbo.latres;

import com.pbo.latres.controller.TodoController;
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.repository.MysqlTodoRepository;
import com.pbo.latres.view.TodoView;

public class Latres {

    public static void main(String[] args) {

        TodoRepository repository =
                new MysqlTodoRepository();

        TodoView view =
                new TodoView();

        new TodoController(
                repository,
                view
        );
    }
}