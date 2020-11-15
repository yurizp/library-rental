package com.agrines.libraryrental.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    BOOK_NOT_FOUND("BOOK-001", "Livro não localizado.", "O livro informado não foi localizado."),
    UNAVIABLE_RENT_BOOK("BOOK-002", "Livro não localizado ou indisponivel.", "Não foi encontrado livro disponivel para emprestimo."),
    CLIENT_NOT_FOUND("CLIENT-001", "Cliente não localizado.", "O cliente informado não foi localizado.");

    private final String code;
    private final String errorMessage;
    private final String message;

    }
