//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import se.iths.storemanagementsystem.entity.Item;
//import se.iths.storemanagementsystem.entity.ShoppingCart;
//import se.iths.storemanagementsystem.service.ShoppingCartService;
//import se.iths.storemanagementsystem.utils.JsonFormatter;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Response;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("shoppingcart")
//public class ShoppingCartController {
//    ShoppingCartService shoppingCartService;
//
//    public ShoppingCartController(ShoppingCartService shoppingCartService) {
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    @Path("")
//    @POST
//    public ResponseEntity<Optional<ShoppingCart>> createShoppingCart(ShoppingCart shoppingCart) {
//        try {
//            shoppingCartService.createShoppingCart(shoppingCart);
//        } catch (Exception e) {
//            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter(Response.Status.CONFLICT.getStatusCode(), "Could not create new shopping cart.l")).build());
//        }
//        return Response.ok(shoppingCart).build();
//    }
//
//    @Path("{id}")
//    @GET
//    public ResponseEntity<Optional<Item>> getShoppingCartById(@PathParam("id") Long id) {
//        Optional<ShoppingCart> foundCart;
//        try {
//            foundCart = shoppingCartService.findShoppingCartById(id);
//        } catch (Exception e) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "Could not find a shopping cart with that ID.")).build());
//        }
//        return Response.ok(foundCart).build();
//    }
//
//    @Path("link")
//    @PATCH
//    public ResponseEntity<Optional<Item>> linkItemToShoppingCart(@QueryParam("cartId") Long cartId, @QueryParam("itemId") Long itemId) {
//        Optional<ShoppingCart> foundCart = shoppingCartService.findShoppingCartById(cartId);
//        Optional<Item> foundItem = shoppingCartService.findItemById(itemId);
//
//        try {
//            shoppingCartService.linkItemToShoppingCart(cartId, itemId);
//        } catch (Exception e) {
//            String exceptionHelper = null;
//            if (foundCart == null) {
//                exceptionHelper = "Shopping cart not found";
//            }
//            if (foundItem == null) {
//                exceptionHelper = "Item not found";
//            }
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), exceptionHelper)).build());
//        }
//        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Item with ID " + foundItem.getId() + " added to cart with ID " + foundCart.getId())).build();
//    }
//
//    @Path("unlink")
//    @PATCH
//    public ResponseEntity<Optional<Item>> unlinkItemFromShoppingCart(@QueryParam("cartId") Long cartId, @QueryParam("itemId") Long itemId) {
//        Optional<ShoppingCart> foundCart = shoppingCartService.findShoppingCartById(cartId);
//        Optional<Item> foundItem = shoppingCartService.findItemById(itemId);
//
//        try {
//            shoppingCartService.unlinkItemFromShoppingCart(cartId, itemId);
//        } catch (Exception e) {
//            String exceptionHelper = null;
//            if (foundCart == null) {
//                exceptionHelper = "Shopping cart not found";
//            }
//            if (foundItem == null) {
//                exceptionHelper = "Item not found";
//            }
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), exceptionHelper)).build());
//        }
//        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Item with ID " + foundItem.getId() + " removed from cart with ID " + foundCart.getId())).build();
//    }
//}
