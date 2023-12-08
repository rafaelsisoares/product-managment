package com.rafaelsisoares.ProductManagment.controllers.dto;

public record ResponseDTO<T>(String message, T data) {

}
