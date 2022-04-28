//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import se.iths.storemanagementsystem.entity.Employee;
//import se.iths.storemanagementsystem.entity.Item;
//import se.iths.storemanagementsystem.service.ItemService;
//import se.iths.storemanagementsystem.utils.JsonFormatter;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Response;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("item")
//public class ItemController {
//
//    ItemService itemService;
//
//    public ItemController(ItemService itemService){ this.itemService = itemService; }
//
//    @Path("")
//    @POST
//    public ResponseEntity<Optional<Item>> createItem(Item item) {
//        try {
//            itemService.addItem(item);
//        } catch (Exception e) {
//            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
//                    (Response.Status.CONFLICT.getStatusCode(), "Item could not be created")).build());
//        }
//        return Response.ok(item).build();
//    }
//
//    @Path("{id}")
//    @GET
//    public ResponseEntity<Optional<Item>> getItemById(@PathParam("id") Long id) {
//        notFoundError(id);
//        Optional<Item> item = itemService.findItemById(id);
//        return Response.ok(item).build();
//    }
//
//    @Path("")
//    @GET
//    public ResponseEntity<Optional<Item>> getAllItems(){
//        List<Item> foundItems = itemService.getAllItems();
//        if(foundItems.isEmpty()) {
//            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no items added yet.")).build();
//        }
//        return Response.ok(foundItems).build();
//    }
//
//    @Path("{id}")
//    @PATCH
//    public ResponseEntity<Optional<Item>> updateItem(@PathParam("id") Long id, Item item){
//        try {
//            notFoundError(id);
//            item = itemService.updateItem(id, item);
//        } catch (Exception e){
//            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(new JsonFormatter(Response.Status.BAD_REQUEST.getStatusCode(), "Failed to update item.")).build());
//        }
//        return Response.ok(item).build();
//    }
//
//    @Path("{id}")
//    @DELETE
//    public ResponseEntity<Optional<Item>> deleteItem(@PathParam("id") Long id){
//        try{
//            itemService.deleteItem(id);
//        } catch (Exception e){
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "No item with ID " + id + " was found.")).build());
//        }
//        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Deleted item with ID "  + id)).build();
//    }
//
//    public void notFoundError(Long id) {
//
//        if (!itemService.findItemById(id).isPresent()) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "There is no item with the id: " + id)).build());
//        }
//    }
//}
