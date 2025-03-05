package com.tr.innova.common.utils;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResponseUtil {
    // Methods for wrapping responses in ResponseEntity<AppResponse<T>>

    static <T> ResponseEntity<AppPageResponse<List<T>>> wrapPageResponse(Page<T> page) {
        AppPageResponse<List<T>> response = new AppPageResponse<>(page.getContent(), createPageInfo(page));
        return ResponseEntity.ok().body(response);
    }

    static <T> ResponseEntity<AppPageResponse<List<T>>> wrapListResponse(List<T> list) {
        AppPageResponse<List<T>> response = new AppPageResponse<>(list);
        return ResponseEntity.ok().body(response);
    }

    static <T> ResponseEntity<AppResponse<T>> wrapDataResponse(T data) {
        AppResponse<T> response = new AppResponse<>(data);
        return ResponseEntity.ok(response);
    }

    static <T> ResponseEntity<AppResponse<T>> wrapErrorResponse(int status, String errorMessage) {
        AppResponse<T> response = new AppResponse<>(status, errorMessage);
        return ResponseEntity.status(status).body(response);
    }


    static <X> ResponseEntity<AppResponse<X>> wrapOrNotFound(Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, (HttpHeaders)null);
    }

    static <X> ResponseEntity<AppResponse<X>> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header) {
        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(new AppResponse<>(response)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    static ResponseEntity<AppResponse<Void>> noContentResponse() {
        AppResponse<Void> response = new AppResponse<Void>(null);
        return ResponseEntity.ok(response);
    }

    static <T> ResponseEntity<AppResponse<T>> noContentResponseType() {
        AppResponse<T> response = new AppResponse<>(null);
        return ResponseEntity.ok(response);
    }

    static <T> ResponseEntity<AppResponse<List<T>>> wrapListOnlyResponse(List<T> list) {
        AppResponse<List<T>> response = new AppResponse<>(list);
        return ResponseEntity.ok().body(response);
    }

    private static <T> AppPageResponse.PageInfo createPageInfo(Page<T> page) {
        AppPageResponse.PageInfo pageInfo = new AppPageResponse.PageInfo();
        pageInfo.setCurrentPage(page.getNumber());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setTotalElements(page.getTotalElements());
        return pageInfo;
    }

    static ResponseEntity<Resource> wrapResourceResponse(Resource resource, String fileName) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    static ResponseEntity<Resource> wrapResourceNotFoundResponse() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    static ResponseEntity<Resource> wrapResourceErrorResponse() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    static <T, S> ResponseEntity<AppResponse<Map<T, S>>> wrapMapOnlyResponse(Map<T, S> map) {
        AppResponse<Map<T, S>> response = new AppResponse<>(map);
        return ResponseEntity.ok().body(response);
    }

}
