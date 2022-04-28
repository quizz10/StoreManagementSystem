//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import se.iths.storemanagementsystem.entity.Department;
//import se.iths.storemanagementsystem.entity.Item;
//import se.iths.storemanagementsystem.entity.Store;
//import se.iths.storemanagementsystem.service.StoreService;
//import se.iths.storemanagementsystem.utils.JsonFormatter;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("store")
//public class StoreController {
//
//    StoreService storeService;
//
//    public StoreController(StoreService storeService){
//        this.storeService = storeService;
//    }
//
//    @Path("")
//    @POST
//    public ResponseEntity<Optional<Store>> createStore(Store store) {
//        try {
//            storeService.addStore(store);
//        } catch (Exception e) {
//            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
//                    (Response.Status.CONFLICT.getStatusCode(), "Store could not be created")).build());
//        }
//        return Response.ok(store).build();
//    }
//
//    @Path("{id}")
//    @GET
//    public ResponseEntity<Optional<Store>> getStoreById(@PathParam("id") Long id) {
//        notFoundError(id);
//        Store store = storeService.getStoreById(id);
//        return Response.ok(store).build();
//    }
//
//    @Path("")
//    @GET
//    public ResponseEntity<Optional<Store>> getAllStores(){
//        List<Store> foundStores = storeService.getAllStores();
//        if(foundStores.isEmpty()) {
//            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no stores added yet.")).build();
//        }
//        return Response.ok(foundStores).build();
//    }
//
//    @Path("{id}")
//    @PATCH
//    public ResponseEntity<Optional<Store>> updateStore(@PathParam("id") Long id, Store store){
//        try {
//            notFoundError(id);
//            store = storeService.updateStore(id, store);
//        } catch (Exception e){
//            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(new JsonFormatter(Response.Status.BAD_REQUEST.getStatusCode(), "Failed to update store.")).build());
//        }
//        return Response.ok(store).build();
//    }
//
//    @Path("{id}")
//    @DELETE
//    public ResponseEntity<Optional<Store>> deleteStore(@PathParam("id") Long id){
//        try{
//            storeService.deleteStore(id);
//        } catch (Exception e){
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "No customer with ID " + id + " was found.")).build());
//        }
//        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Deleted customer with ID "  + id)).build();
//    }
//
//    @Path("link")
//    @PATCH
//    public ResponseEntity<Optional<Store>> linkStoreToDepartment(@QueryParam("storeId") Long storeId, @QueryParam("departmentId") Long departmentId){
//        Store foundStore = storeService.getStoreById(storeId);
//        Department foundDepartment = storeService.getDepartmentById(departmentId);
//        try {
//            storeService.linkDepartment(storeId, departmentId);
//        } catch (Exception e){
//            String exceptionHelper = null;
//            if (foundStore == null){
//                exceptionHelper = "Store not found.";
//            }
//            if (foundDepartment == null){
//                exceptionHelper = "Department not found.";
//            }
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), exceptionHelper)).build());
//        }
//        return Response.ok().entity(new JsonFormatter(Response.Status.OK.getStatusCode(), "Department with ID " + departmentId + " linked to store with ID " + storeId)).build();
//    }
//
//    @Path("unlink")
//    @PATCH
//    public ResponseEntity<Optional<Store>> unlinkStoreFromDepartment(@QueryParam("storeId") Long storeId, @QueryParam("departmentId") Long departmentId){
//        Store foundStore = storeService.getStoreById(storeId);
//        Department foundDepartment = storeService.getDepartmentById(departmentId);
//        try {
//            storeService.unlinkDepartment(storeId, departmentId);
//        } catch (Exception e){
//            String exceptionHelper = null;
//            if (foundStore == null){
//                exceptionHelper = "Store not found";
//            }
//            if (foundDepartment == null){
//                exceptionHelper = "Department not found";
//            }
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), exceptionHelper)).build());
//        }
//        return Response.ok().entity(new JsonFormatter(Response.Status.OK.getStatusCode(), "Department with ID " + departmentId + " unlinked from store with ID " + storeId)).build();
//    }
//
//    public void notFoundError(Long id) {
//
//        if (storeService.getStoreById(id) == null) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "There is no store with the id: " + id)).build());
//        }
//    }
//}
