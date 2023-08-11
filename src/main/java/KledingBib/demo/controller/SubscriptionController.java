package KledingBib.demo.controller;


import KledingBib.demo.dto.SubscriptionDto;

import KledingBib.demo.service.AccountService;
import KledingBib.demo.service.SubscriptionService;


import KledingBib.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final AccountService AccountService;
    private final UserService UserService;


    public SubscriptionController(SubscriptionService subscriptionService, UserService userService, AccountController AccountController, AccountService AccountService) {
        this.subscriptionService = subscriptionService;
        this.AccountService = AccountService;
        this.UserService = userService;
    }


    @GetMapping("/subscriptions")


    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/subscriptions/{id}")

    public ResponseEntity<SubscriptionDto> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<?> createSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto, BindingResult br) {
        if (br.hasErrors()) {
            String errorString = getErrorString(br);

            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = subscriptionService.createSubscription(subscriptionDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/subscriptions/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body(createdId);
        }
    }

    private String getErrorString(BindingResult br) {
        return null;
    }

    @PutMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionDto subscriptionDto1 = subscriptionService.putSubscription(id, subscriptionDto);
        return ResponseEntity.ok().body(subscriptionDto1);
    }

    @PatchMapping("/subscriptions/{id}")
    public ResponseEntity<SubscriptionDto> updatePartOfSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionDto subscriptionDto1 = subscriptionService.patchSubscription(id, subscriptionDto);
        return ResponseEntity.ok().body(subscriptionDto1);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


/////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////

