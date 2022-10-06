package com.skopei.demo.abstraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCtrl<T> {

    private final ICRUD<T> DAO;

    @PostMapping
    @Operation(summary = "create new resource",
            responses = {
                    @ApiResponse(description = "resource successfully created",
                            responseCode = "200")
            })
    public void create(@Valid @RequestBody T t) {
        DAO.create(t);
    }

    @GetMapping
    @Operation(summary = "get resource by id",
            responses = {
                    @ApiResponse(description = "resource successfully retrieved",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    )})
    public T get(@RequestParam int id) {
        return DAO.read(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Operation(summary = "get all of type",
            responses = {
                    @ApiResponse(description = "successfully retrieved list",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    )})
    public List<T> getList() {
        return DAO.readList();
    }

    /***
     * Merges incoming changes with existing entity. Validates before updating
     */
    @PatchMapping(consumes = "application/json-patch+json")
    @Operation(summary = "update resource",
            responses = {
                    @ApiResponse(description = "successfully updated resource", responseCode = "200")
            })
    public void update(@RequestParam int id, @RequestBody ObjectNode patchNode) throws IOException {
        @Valid T t = new ObjectMapper().readerForUpdating(DAO.read(id)).readValue(patchNode);
        DAO.update(t);
    }

    @DeleteMapping
    @Operation(summary = "delete resource",
            responses = {
                    @ApiResponse(description = "successfully deleted resource", responseCode = "200")
            })
    public void delete(@RequestParam int id) {
        DAO.delete(id);
    }
}
