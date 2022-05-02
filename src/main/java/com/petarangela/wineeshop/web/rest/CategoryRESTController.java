package com.petarangela.wineeshop.web.rest;

import com.petarangela.wineeshop.model.Category;
import com.petarangela.wineeshop.model.Type;
import com.petarangela.wineeshop.model.Wine;
import com.petarangela.wineeshop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRESTController {

   // private final CategoryService categoryService;

   // public CategoryRESTController(CategoryService categoryService) {
   //     this.categoryService = categoryService;
   // }

    /** **********************************************************************************************************
     *
     *                                           GET REQUESTS
     *
     ************************************************************************************************************* */

    /** GET ALL CATEGORIES */
   /* @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = this.categoryService.listAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }*/

    /** GET ALL TYPES THAT BELONG TO THE CATEGORY */
    /*@GetMapping("/types")
    public ResponseEntity<List<Type>> getAllTypesOfCategory(@RequestParam String categoryName){
        List<Type> types = this.categoryService.listAllTypes(categoryName);
        return new ResponseEntity<>(types, HttpStatus.OK);
    }*/

    /** FIND CATEGORY BY ID !*/
   /* @GetMapping("/find/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        Category category = this.categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }*/


    /** FIND CATEGORY BY NAME!*/
    /*@GetMapping("/find/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable("name") String name){
        Category category = this.categoryService.findByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }*/

    /** **********************************************************************************************************
     *
     *                                           POSTS REQUESTS
     *
     ************************************************************************************************************* */




    /** ADD CATEGORY WITH URL PARAMETER*/
   /* @PostMapping("/add")
    public ResponseEntity<Category> save(   @RequestParam String name
                                          //  @RequestParam List<Long> typesId
    ){
            /*return this.categoryService.create(name,types)
                .map(wine -> ResponseEntity.ok().body(wine))
                .orElseGet(() -> ResponseEntity.badRequest().build());

        Category category = this.categoryService.create(name);
        System.out.println(category);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }*/

    /** ADD CATEGORY WITH URL PARAMETER*/
//    @PostMapping("/add")
//    public ResponseEntity<Category> save(@RequestBody Category category){
//            /*return this.categoryService.create(name,types)
//                .map(wine -> ResponseEntity.ok().body(wine))
//                .orElseGet(() -> ResponseEntity.badRequest().build());*/
//
//        Category kategorija = this.categoryService.create(cate);
//        System.out.println(category);
//        return new ResponseEntity<>(category,HttpStatus.CREATED);
//    }


    /** **********************************************************************************************************
     *
     *                                           PUT REQUESTS
     *
     ************************************************************************************************************* */


    /** UPDATE CATEGORY WITH URL PARAMETERS !*/
    /*@PutMapping("/update")
    public ResponseEntity<Category> updateCategory(
            @RequestParam Long id,
            @RequestParam String name
                                           ){

        Category category = this.categoryService.update(id, name);
        System.out.println(category);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }*/


    /** **********************************************************************************************************
     *
     *                                           DELETE REQUESTS
     *
     ************************************************************************************************************* */


    /** DELETE CATEGORY WITH NAME! */
    /*@DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "name") String name){
        this.categoryService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

}
