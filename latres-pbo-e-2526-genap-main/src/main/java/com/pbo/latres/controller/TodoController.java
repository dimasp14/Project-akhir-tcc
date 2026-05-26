package com.pbo.latres.controller;

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.model.TodoTask;
import com.pbo.latres.view.TodoView;

public class TodoController {

    private final TodoRepository repository;
    private final TodoView view;

    public TodoController(
            TodoRepository repository,
            TodoView view
    ) {

        this.repository = repository;
        this.view = view;

        init();
    }

    private void init() {

        loadTable();

        view.onAdd(e -> addTodo());

        view.onUpdate(e -> updateTodo());

        view.onDelete(e -> deleteTodo());

        view.onClear(e -> view.clearForm());

        view.onTableSelect(e -> {

            int id =
                    view.getSelectedTodoId();

            if (id == -1) {
                return;
            }

            TodoTask task =
                    repository.getById(id);

            if (task != null) {
                view.setForm(task);
            }
        });
    }

    private void loadTable() {

        view.showTodos(
                repository.getAll()
        );
    }

    private void addTodo() {

        InsertTodoDTO dto =
                new InsertTodoDTO(
                        view.getTitleInput(),
                        view.getStatusInput()
                );

        Boolean success =
                repository.insert(dto);

        if (success) {

            view.showMessage(
                    "Todo berhasil ditambahkan"
            );

            loadTable();

            view.clearForm();

        } else {

            view.showMessage(
                    "Gagal menambahkan todo"
            );
        }
    }

    private void updateTodo() {

        int id =
                view.getSelectedTodoId();

        if (id == -1) {

            view.showMessage(
                    "Pilih data terlebih dahulu"
            );

            return;
        }

        TodoTask task =
                new TodoTask(
                        id,
                        view.getTitleInput(),
                        view.getStatusInput()
                );

        Boolean success =
                repository.update(task);

        if (success) {

            view.showMessage(
                    "Todo berhasil diupdate"
            );

            loadTable();

            view.clearForm();

        } else {

            view.showMessage(
                    "Gagal update todo"
            );
        }
    }

    private void deleteTodo() {

        int id =
                view.getSelectedTodoId();

        if (id == -1) {

            view.showMessage(
                    "Pilih data terlebih dahulu"
            );

            return;
        }

        Boolean success =
                repository.deleteById(id);

        if (success) {

            view.showMessage(
                    "Todo berhasil dihapus"
            );

            loadTable();

            view.clearForm();

        } else {

            view.showMessage(
                    "Gagal menghapus todo"
            );
        }
    }
}